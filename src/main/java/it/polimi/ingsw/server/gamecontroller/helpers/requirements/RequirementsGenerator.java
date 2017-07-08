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

public class RequirementsGenerator {
    private static final Goods OCCUPIED_TOWER_COST = new Goods(new Resources(0, 0, 0, 3));

    private Board board;
    private Player player;
    private BoardIdentifier boardIdentifier;

    public RequirementsGenerator(Board board, Player player, BoardIdentifier boardIdentifier) {
        this.board = board;
        this.player = player;
        this.boardIdentifier = boardIdentifier;
    }

    public RequirementsSupport generateRequirements(BoardAction boardAction) {
        BasicAction basicAction = boardAction.getBasicAction();
        SpaceActionRequirements spaceActionRequirements = new SpaceActionRequirements(
                basicAction.getActionType(), boardAction.getPawnColor(), getRequiredValue(),
                basicAction.getActionValue(), boardAction.getNumberOfServants(), getIsOccupied());
        if ('T' == boardIdentifier.toString().charAt(0)) {
            return new RequirementsSupport(generateTowerActionRequirements(boardAction, spaceActionRequirements));
        } else {
            return new RequirementsSupport(generateBoardActionRequirements(boardAction, spaceActionRequirements));
        }
    }

    private int getRequiredValue() {
        Optional<Space> optionalSpace = searchSpace();
        if (optionalSpace.isPresent()) {
            Space space = optionalSpace.get();
            return space.getRequestedValue();
        }
        return 0;
    }

    private boolean getIsOccupied() {
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

    private Optional<Space> searchSpace() {
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

    private TowerActionRequirements generateTowerActionRequirements(BoardAction boardAction,
                                                         SpaceActionRequirements spaceActionRequirements) {
        Optional<TowerSlot> optionalTowerSlot = searchTowerSlot();
        if (optionalTowerSlot.isPresent()) {
            TowerSlot towerSlot = optionalTowerSlot.get();
            Goods requiredGoods = towerSlot.getDevelopmentCard()
                    .getCosts()
                    .get(boardAction.getPositionMultipleCostChosen());
            Goods bonusGoods = towerSlot.getInstantGoods();
            return new TowerActionRequirements(spaceActionRequirements, requiredGoods, bonusGoods,
                    OCCUPIED_TOWER_COST, isOccupiedTower(boardAction.getBasicAction().getActionType()),
                    isOccupiedTowerByMyColouredPawn(boardAction.getBasicAction().getActionType()));
        }
        return null;
    }

    private Optional<TowerSlot> searchTowerSlot() {
        for (Tower tower : board.getTowers()) {
            for (TowerSlot towerSlot : tower.getTowerSlots()) {
                if (towerSlot.getSpace().getBoardIdentifier() == boardIdentifier) {
                    return Optional.of(towerSlot);
                }
            }
        }
        return Optional.empty();
    }

    private boolean isOccupiedTower(ActionType actionType) {
        Tower tower = getTowerBasedOnActionType(actionType);
        if (tower != null) {
            for (TowerSlot towerSlot : tower.getTowerSlots()) {
                if (towerSlot.getSpace().isAlreadyTaken()) {
                    return true;
                }
            }
        }
        return false;
    }

    private Tower getTowerBasedOnActionType(ActionType actionType) {
        switch (actionType) {
            case GREEN_TOWER:
                for (Tower tower : board.getTowers()) {
                    if (GeneralColor.GREEN == tower.getColor()) {
                        return tower;
                    }
                }
                break;
            case YELLOW_TOWER:
                for (Tower tower : board.getTowers()) {
                    if (GeneralColor.YELLOW == tower.getColor()) {
                        return tower;
                    }
                }
                break;
            case BLUE_TOWER:
                for (Tower tower : board.getTowers()) {
                    if (GeneralColor.BLUE == tower.getColor()) {
                        return tower;
                    }
                }
                break;
            case PURPLE_TOWER:
                for (Tower tower : board.getTowers()) {
                    if (GeneralColor.PURPLE == tower.getColor()) {
                        return tower;
                    }
                }
                break;
        }
        return null;
    }

    private boolean isOccupiedTowerByMyColouredPawn(ActionType actionType) {
        Tower tower = getTowerBasedOnActionType(actionType);
        if (tower != null) {
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

    private BoardActionRequirements generateBoardActionRequirements(BoardAction boardAction,
                                                                   SpaceActionRequirements spaceActionRequirements) {
        return new BoardActionRequirements(spaceActionRequirements, searchMalusValueOnBoardActionSpace());
    }

    private int searchMalusValueOnBoardActionSpace() {
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

    public BoardIdentifier getBoardIdentifier() {
        return boardIdentifier;
    }

    public void setBoardIdentifier(BoardIdentifier boardIdentifier) {
        this.boardIdentifier = boardIdentifier;
    }
}
