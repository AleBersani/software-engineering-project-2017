package it.polimi.ingsw.server.gamecontroller.helpers.requirements;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.board.*;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.BoardActionRequirements;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.SpaceActionRequirements;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.TowerActionRequirements;
import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import it.polimi.ingsw.shared.model.GeneralColor;
import it.polimi.ingsw.shared.model.PawnColor;
import it.polimi.ingsw.shared.model.actionsdescription.BasicAction;
import it.polimi.ingsw.shared.model.actionsdescription.BoardAction;

import java.util.Optional;

/**
 * Constructs the requirements for each available action basing on the space the action is performed on
 */

public class RequirementsGenerator {
    private static final Goods OCCUPIED_TOWER_COST = new Goods(new Resources(0, 0, 0, 3));

    private Board board;
    private Player player;

    private BoardAction boardAction;
    private ActionType actionType;
    private BoardIdentifier boardIdentifier;

    public RequirementsGenerator(Board board, Player player) {
        this.board = board;
        this.player = player;
    }

    public void addAttributesToCalculateRequirements(BoardAction boardAction) {
        this.boardAction = boardAction;
        actionType = boardAction.getBasicAction().getActionType();
        boardIdentifier = boardAction.getBasicAction().getBoardIdentifier();
    }

    public RequirementsSupport generateRequirements() {
        SpaceActionRequirements spaceActionRequirements = createSpaceActionRequirements();

        if ('T' == boardIdentifier.toString().charAt(0)) {
            return new RequirementsSupport(generateTowerActionRequirements(spaceActionRequirements));
        } else {
            return new RequirementsSupport(generateBoardActionRequirements(spaceActionRequirements));
        }
    }

    public SpaceActionRequirements createSpaceActionRequirements() {
        BasicAction basicAction = boardAction.getBasicAction();

        return new SpaceActionRequirements(actionType, boardAction.getPawnColor(), getRequestedValue(),
                basicAction.getActionValue(), boardAction.getNumberOfServants(), getIsOccupied());
    }

    public int getRequestedValue() {
        Optional<Space> optionalSpace = searchSpace();
        if (optionalSpace.isPresent()) {
            Space space = optionalSpace.get();
            return space.getRequestedValue();
        }
        return 0;
    }

    public Optional<Space> searchSpace() {
        for (Tower tower : board.getTowers()) {
            for (TowerSlot towerSlot : tower.getTowerSlots()) {
                if (towerSlot.getSpace().getBoardIdentifier() == boardIdentifier) {
                    return Optional.of(towerSlot.getSpace());
                }
            }
        }

        for (ProductionHarvestSpace productionHarvestSpace : board.getBoardActionSpaces().getProductionArea()) {
            if (productionHarvestSpace.getSpace().getBoardIdentifier() == boardIdentifier) {
                return Optional.of(productionHarvestSpace.getSpace());
            }
        }

        for (ProductionHarvestSpace productionHarvestSpace : board.getBoardActionSpaces().getHarvestArea()) {
            if (productionHarvestSpace.getSpace().getBoardIdentifier() == boardIdentifier) {
                return Optional.of(productionHarvestSpace.getSpace());
            }
        }

        for (MarketSpace marketSpace : board.getBoardActionSpaces().getMarketArea()) {
            if (marketSpace.getSpace().getBoardIdentifier() == boardIdentifier) {
                return Optional.of(marketSpace.getSpace());
            }
        }

        return Optional.empty();
    }

    public boolean getIsOccupied() {
        if (BoardIdentifier.PRODUCTION_2 == boardIdentifier || BoardIdentifier.HARVEST_2 == boardIdentifier) {
            return true;
        }
        Optional<Space> optionalSpace = searchSpace();
        if (optionalSpace.isPresent()) {
            Space space = optionalSpace.get();
            return space.isAlreadyTaken();
        }
        return true;
    }

    public TowerActionRequirements generateTowerActionRequirements(SpaceActionRequirements spaceActionRequirements) {
        Optional<TowerSlot> optionalTowerSlot = searchTowerSlot();
        if (optionalTowerSlot.isPresent()) {
            TowerSlot towerSlot = optionalTowerSlot.get();
            Goods requiredGoods = towerSlot.getDevelopmentCard().getCosts()
                    .get(boardAction.getPositionMultipleCostChosen());
            Goods bonusGoods = towerSlot.getInstantGoods();
            return new TowerActionRequirements(spaceActionRequirements, requiredGoods, bonusGoods,
                    OCCUPIED_TOWER_COST, isOccupiedTower(), isOccupiedTowerByMyColouredPawn());
        }
        return null;
    }

    public Optional<TowerSlot> searchTowerSlot() {
        for (Tower tower : board.getTowers()) {
            for (TowerSlot towerSlot : tower.getTowerSlots()) {
                if (towerSlot.getSpace().getBoardIdentifier() == boardIdentifier) {
                    return Optional.of(towerSlot);
                }
            }
        }
        return Optional.empty();
    }

    public boolean isOccupiedTower() {
        Optional<Tower> optionalTower = getTowerBasedOnActionType();
        if (optionalTower.isPresent()) {
            Tower tower = optionalTower.get();
            for (TowerSlot towerSlot : tower.getTowerSlots()) {
                if (towerSlot.getSpace().isAlreadyTaken()) {
                    return true;
                }
            }
        }
        return false;
    }

    public Optional<Tower> getTowerBasedOnActionType() {
        switch (actionType) {
            case GREEN_TOWER:
                for (Tower tower : board.getTowers()) {
                    if (GeneralColor.GREEN == tower.getColor()) {
                        return Optional.of(tower);
                    }
                }
                break;
            case YELLOW_TOWER:
                for (Tower tower : board.getTowers()) {
                    if (GeneralColor.YELLOW == tower.getColor()) {
                        return Optional.of(tower);
                    }
                }
                break;
            case BLUE_TOWER:
                for (Tower tower : board.getTowers()) {
                    if (GeneralColor.BLUE == tower.getColor()) {
                        return Optional.of(tower);
                    }
                }
                break;
            case PURPLE_TOWER:
                for (Tower tower : board.getTowers()) {
                    if (GeneralColor.PURPLE == tower.getColor()) {
                        return Optional.of(tower);
                    }
                }
                break;
        }
        return Optional.empty();
    }

    public boolean isOccupiedTowerByMyColouredPawn() {
        Optional<Tower> optionalTower = getTowerBasedOnActionType();
        if (optionalTower.isPresent()) {
            Tower tower = optionalTower.get();
            for (TowerSlot towerSlot : tower.getTowerSlots()) {
                PlayerPawn playerPawn = towerSlot.getSpace().getPlayerPawn();
                if (playerPawn.getPlayerDetails().getPlayerName().equals(player.getPlayerDetails().getPlayerName()) &&
                        PawnColor.NEUTRAL != playerPawn.getPawnColor()) {
                    return true;
                }
            }
        }
        return false;
    }

    public BoardActionRequirements generateBoardActionRequirements(SpaceActionRequirements spaceActionRequirements) {
        return new BoardActionRequirements(spaceActionRequirements, searchMalusValueOnBoardActionSpace());
    }

    public int searchMalusValueOnBoardActionSpace() {
        for (ProductionHarvestSpace productionHarvestSpace : board.getBoardActionSpaces().getProductionArea()) {
            if (productionHarvestSpace.getSpace().getBoardIdentifier() == boardIdentifier) {
                return productionHarvestSpace.getMalusValue();
            }
        }

        for (ProductionHarvestSpace productionHarvestSpace : board.getBoardActionSpaces().getHarvestArea()) {
            if (productionHarvestSpace.getSpace().getBoardIdentifier() == boardIdentifier) {
                return productionHarvestSpace.getMalusValue();
            }
        }

        return 0;
    }

    /*
        GETTERS AND SETTERS
     */

    public static Goods getOccupiedTowerCost() {
        return OCCUPIED_TOWER_COST;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public BoardAction getBoardAction() {
        return boardAction;
    }

    public void setBoardAction(BoardAction boardAction) {
        this.boardAction = boardAction;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public BoardIdentifier getBoardIdentifier() {
        return boardIdentifier;
    }

    public void setBoardIdentifier(BoardIdentifier boardIdentifier) {
        this.boardIdentifier = boardIdentifier;
    }
}
