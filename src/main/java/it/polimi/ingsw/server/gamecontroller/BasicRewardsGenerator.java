package it.polimi.ingsw.server.gamecontroller;

import it.polimi.ingsw.server.gamelogic.basics.CouncilPrivilege;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.board.Board;
import it.polimi.ingsw.server.gamelogic.board.Tower;
import it.polimi.ingsw.server.gamelogic.board.TowerSlot;
import it.polimi.ingsw.server.gamelogic.cards.development.Building;
import it.polimi.ingsw.server.gamelogic.cards.development.Character;
import it.polimi.ingsw.server.gamelogic.cards.development.Territory;
import it.polimi.ingsw.server.gamelogic.cards.development.Venture;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.BasicRewards;
import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import it.polimi.ingsw.shared.model.actionsdescription.BoardAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class BasicRewardsGenerator {
    private final static Logger LOGGER = Logger.getLogger(BasicRewardsGenerator.class.getName());

    private Board board;

    public BasicRewardsGenerator(Board board) {
        this.board = board;
    }

    public void generateRewards(Player player, BoardAction boardAction) {
        List<BasicRewards> basicRewardsList = new ArrayList<>();
        Optional<TowerSlot> optionalTowerSlot;
        switch (boardAction.getBasicAction().getActionType()) {
            case GREEN_TOWER: {
                optionalTowerSlot = generateBasicRewardForTowerAction(boardAction, basicRewardsList);
                if (optionalTowerSlot.isPresent()) {
                    TowerSlot towerSlot = optionalTowerSlot.get();
                    player.getPlayerBoard().getDeck().getTerritories().add((Territory)towerSlot.getDevelopmentCard());
                    towerSlot.setDevelopmentCard(null);
                }
                LOGGER.info("Action type: green tower, rewards given to player!");
                break;
            }
            case YELLOW_TOWER: {
                optionalTowerSlot = generateBasicRewardForTowerAction(boardAction, basicRewardsList);
                if (optionalTowerSlot.isPresent()) {
                    TowerSlot towerSlot = optionalTowerSlot.get();
                    player.getPlayerBoard().getDeck().getBuildings().add((Building)towerSlot.getDevelopmentCard());
                    towerSlot.setDevelopmentCard(null);
                }
                LOGGER.info("Action type: yellow tower, rewards given to player!");
                break;
            }
            case BLUE_TOWER: {
                optionalTowerSlot = generateBasicRewardForTowerAction(boardAction, basicRewardsList);
                if (optionalTowerSlot.isPresent()) {
                    TowerSlot towerSlot = optionalTowerSlot.get();
                    player.getPlayerBoard().getDeck().getCharacters().add((Character)towerSlot.getDevelopmentCard());
                    towerSlot.setDevelopmentCard(null);
                }
                LOGGER.info("Action type: blue tower, rewards given to player!");
                break;
            }
            case PURPLE_TOWER: {
                optionalTowerSlot = generateBasicRewardForTowerAction(boardAction, basicRewardsList);
                if (optionalTowerSlot.isPresent()) {
                    TowerSlot towerSlot = optionalTowerSlot.get();
                    player.getPlayerBoard().getDeck().getVentures().add((Venture)towerSlot.getDevelopmentCard());
                    towerSlot.setDevelopmentCard(null);
                }
                LOGGER.info("Action type: purple tower, rewards given to player!");
                break;
            }
            case COUNCIL_PALACE: {
                for (int i = 0; i < board.getCouncilPalace().getInstantGoods().getNumberOfCouncilPrivilege(); i++) {
                    Goods goods = CouncilPrivilege.getPossibleChoices().get(i);
                    goods.addAll(board.getCouncilPalace().getInstantGoods().getGoods());
                    basicRewardsList.add(new BasicRewards(boardAction.getBasicAction().getActionType(), goods));
                }
                break;
            }
            case PRODUCTION: {

                break;
            }
            case HARVEST: {

                break;
            }
            case MARKET: {
                board.getBoardActionSpaces().getMarketArea().forEach(m -> {
                    if (m.getSpace().getBoardIdentifier() == boardAction.getBasicAction().getBoardIdentifier()) {
                        Goods goods = m.getExchangingGoods().getGoods();
                        for (int i = 0; i < m.getExchangingGoods().getNumberOfCouncilPrivilege(); i++) {
                            goods.addAll(CouncilPrivilege.getPossibleChoices()
                                    .get(boardAction.getPositionExchangingGoodsChosen().get(i)));
                        }
                        basicRewardsList.add(new BasicRewards(boardAction.getBasicAction().getActionType(), goods));
                    }
                });
                break;
            }
        }
        giveRewardsToPlayer(basicRewardsList, player);
    }

    private Optional<TowerSlot> generateBasicRewardForTowerAction(BoardAction boardAction, List<BasicRewards> basicRewardsList) {
        Optional<TowerSlot> optionalTowerSlot =
                findTowerSlotByBoardIdentifier(boardAction.getBasicAction().getBoardIdentifier());
        if (optionalTowerSlot.isPresent()) {
            TowerSlot towerSlot = optionalTowerSlot.get();
            basicRewardsList.add(new BasicRewards(boardAction.getBasicAction().getActionType(),
                    towerSlot.getInstantGoods()));
        }
        return optionalTowerSlot;
    }

    private Optional<TowerSlot> findTowerSlotByBoardIdentifier(BoardIdentifier boardIdentifier) {
        for (Tower tower : board.getTowers()) {
            for (TowerSlot towerSlot : tower.getTowerSlots()) {
                if (towerSlot.getSpace().getBoardIdentifier() == boardIdentifier) {
                    return Optional.of(towerSlot);
                }
            }
        }
        return Optional.empty();
    }

    public void giveRewardsToPlayer(List<BasicRewards> basicRewardsList, Player player) {
        basicRewardsList.forEach(b -> player.getRewardsModifiers().forEach(m -> m.modifyRewards(b)));

    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
