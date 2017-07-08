package it.polimi.ingsw.server.gamecontroller.helpers.rewards;

import it.polimi.ingsw.server.gameelements.AdditionalInfoMaps;
import it.polimi.ingsw.server.gamelogic.basics.CouncilPrivilege;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.board.Board;
import it.polimi.ingsw.server.gamelogic.board.PlayerPawn;
import it.polimi.ingsw.server.gamelogic.board.Tower;
import it.polimi.ingsw.server.gamelogic.board.TowerSlot;
import it.polimi.ingsw.server.gamelogic.cards.CardVisitor;
import it.polimi.ingsw.server.gamelogic.cards.additionalinfo.AdditionalCardInfo;
import it.polimi.ingsw.server.gamelogic.cards.development.*;
import it.polimi.ingsw.server.gamelogic.cards.development.Character;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.BasicRewards;
import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import it.polimi.ingsw.shared.model.actionsdescription.BoardAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
                    getAndRunAdditionalCardInfo(player, boardAction, towerSlot.getDevelopmentCard());
                    player.getPlayerBoard().getDeck().getTerritories().add((Territory)towerSlot.getDevelopmentCard());
                    player.getPlayerGoods().subtractAll(towerSlot.getDevelopmentCard().getCosts().get(0));
                    towerSlot.setDevelopmentCard(null);
                    towerSlot.getSpace().setAlreadyTaken(true);
                    towerSlot.getSpace().setPlayerPawn(new PlayerPawn(player.getPlayerDetails(), boardAction.getPawnColor()));
                }
                LOGGER.info("Action type: green tower, rewards given to player!");
                break;
            }
            case YELLOW_TOWER: {
                optionalTowerSlot = generateBasicRewardForTowerAction(boardAction, basicRewardsList);
                if (optionalTowerSlot.isPresent()) {
                    TowerSlot towerSlot = optionalTowerSlot.get();
                    getAndRunAdditionalCardInfo(player, boardAction, towerSlot.getDevelopmentCard());
                    player.getPlayerBoard().getDeck().getBuildings().add((Building)towerSlot.getDevelopmentCard());
                    player.getPlayerGoods().subtractAll(towerSlot.getDevelopmentCard().getCosts().get(0));
                    towerSlot.setDevelopmentCard(null);
                    towerSlot.getSpace().setAlreadyTaken(true);
                    towerSlot.getSpace().setPlayerPawn(new PlayerPawn(player.getPlayerDetails(), boardAction.getPawnColor()));
                }
                LOGGER.info("Action type: yellow tower, rewards given to player!");
                break;
            }
            case BLUE_TOWER: {
                optionalTowerSlot = generateBasicRewardForTowerAction(boardAction, basicRewardsList);
                if (optionalTowerSlot.isPresent()) {
                    TowerSlot towerSlot = optionalTowerSlot.get();
                    getAndRunAdditionalCardInfo(player, boardAction, towerSlot.getDevelopmentCard());
                    player.getPlayerBoard().getDeck().getCharacters().add((Character)towerSlot.getDevelopmentCard());
                    player.getPlayerGoods().subtractAll(towerSlot.getDevelopmentCard().getCosts().get(0));
                    towerSlot.setDevelopmentCard(null);
                    towerSlot.getSpace().setAlreadyTaken(true);
                    towerSlot.getSpace().setPlayerPawn(new PlayerPawn(player.getPlayerDetails(), boardAction.getPawnColor()));
                }
                LOGGER.info("Action type: blue tower, rewards given to player!");
                break;
            }
            case PURPLE_TOWER: {
                optionalTowerSlot = generateBasicRewardForTowerAction(boardAction, basicRewardsList);
                if (optionalTowerSlot.isPresent()) {
                    TowerSlot towerSlot = optionalTowerSlot.get();
                    getAndRunAdditionalCardInfo(player, boardAction, towerSlot.getDevelopmentCard());
                    player.getPlayerBoard().getDeck().getVentures().add((Venture)towerSlot.getDevelopmentCard());
                    player.getPlayerGoods().subtractAll(towerSlot.getDevelopmentCard().getCosts().get(0));
                    towerSlot.setDevelopmentCard(null);
                    towerSlot.getSpace().setAlreadyTaken(true);
                    towerSlot.getSpace().setPlayerPawn(new PlayerPawn(player.getPlayerDetails(), boardAction.getPawnColor()));
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

    private void getAndRunAdditionalCardInfo(Player player, BoardAction boardAction, DevelopmentCard developmentCard) {
        runAdditionalCardInfo(player, boardAction, developmentCard.getCardInformation().getName(),
                AdditionalInfoMaps.getFlashEffectsOnChoice());
        runAdditionalCardInfo(player, boardAction, developmentCard.getCardInformation().getName(),
                AdditionalInfoMaps.getFlashEffectsNotSelectable());
        runAdditionalCardInfo(player, boardAction, developmentCard.getCardInformation().getName(),
                AdditionalInfoMaps.getPermanentEffectsOnChoice());
        runAdditionalCardInfo(player, boardAction, developmentCard.getCardInformation().getName(),
                AdditionalInfoMaps.getPermanentEffectsNotSelectable());
    }

    private void runAdditionalCardInfo(Player player, BoardAction boardAction, String cardName, Map<String, List<AdditionalCardInfo>> effectsForCards) {
        System.out.println("Cerco additional info per: " + cardName);
        for (Map.Entry<String, List<AdditionalCardInfo>> entry : effectsForCards.entrySet()) {
            System.out.println(entry.getKey());
            if (entry.getKey().equals(cardName)) {
                System.out.println("Additional info trovata per: " + entry.getKey());
                for (AdditionalCardInfo additionalCardInfo : entry.getValue()) {
                    CardVisitor cardVisitor = new CardVisitorHandler(player, boardAction);
                    additionalCardInfo.acceptCardVisitor(cardVisitor);
                }
            }
        }
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
