package it.polimi.ingsw.server.gamecontroller.helpers.rewards;

import it.polimi.ingsw.server.gamecontroller.helpers.Sender;
import it.polimi.ingsw.server.gameelements.AdditionalInfoMaps;
import it.polimi.ingsw.server.gamelogic.basics.CouncilPrivilege;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.board.*;
import it.polimi.ingsw.server.gamelogic.cards.additionalinfo.AdditionalCardInfo;
import it.polimi.ingsw.server.gamelogic.cards.development.*;
import it.polimi.ingsw.server.gamelogic.cards.development.Character;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.BoardActionRequirements;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.Requirements;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.SpaceActionRequirements;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.BasicRewards;
import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import it.polimi.ingsw.shared.model.actionsdescription.BoardAction;
import it.polimi.ingsw.shared.model.actionsdescription.LeaderAction;
import it.polimi.ingsw.shared.requests.serverclient.CouncilPrivilegeChoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class BasicRewardsGenerator {
    private final static Logger LOGGER = Logger.getLogger(BasicRewardsGenerator.class.getName());

    private Board board;
    private Player player;
    private Sender sender;
    private List<BasicRewards> basicRewards;

    private BoardAction boardAction;
    private LeaderAction leaderAction;
    private ActionType actionType;
    private BoardIdentifier boardIdentifier;

    public BasicRewardsGenerator(Board board, Player player, Sender sender) {
        this.board = board;
        this.player = player;
        this.sender = sender;
        basicRewards = new ArrayList<>();
    }

    public void addAttributesToCalculateLeaderRewards(LeaderAction leaderAction) {
        this.leaderAction = leaderAction;
        actionType = leaderAction.getActionType();
    }

    public void generateRewardsForLeaderAction() {
        switch (actionType) {
            case LEADER_PLACEMENT: {
                player.getLeaderCards().forEach(leaderCard -> {
                    if (leaderCard.getLeaderName().equals(leaderAction.getLeaderName())) {
                        leaderCard.setPlacedOnBoard(true);
                        board.getLeaderInformationList().add(leaderCard.getLeaderInformation());
                        getAndRunLeaderAdditionalCardInfoPermanent();
                    }
                });
                break;
            }
            case LEADER_ACTIVATION: {
                player.getLeaderCards().forEach(leaderCard -> {
                    if (leaderCard.getLeaderName().equals(leaderAction.getLeaderName())) {
                        getAndRunLeaderAdditionalCardInfoConsumable();
                    }
                });
                break;
            }
            case LEARD_DISCARD: {
                player.getLeaderCards().forEach(leaderCard -> {
                    if (leaderCard.getLeaderName().equals(leaderAction.getLeaderName())) {
                        if (!leaderCard.isPlacedOnBoard()) {
                            player.getLeaderCards().remove(leaderCard);
                            sendCouncilPrivilegeChoice(1);
                        }
                    }
                });
                break;
            }
        }
        giveRewardsToPlayer();
    }

    public void getAndRunLeaderAdditionalCardInfoPermanent() {
        for (Map.Entry<String, List<AdditionalCardInfo>> entry : AdditionalInfoMaps.getFlashEffectsNotSelectable().entrySet()) {
            if (entry.getKey().equals(leaderAction.getLeaderName())) {
                for (AdditionalCardInfo additionalCardInfo : entry.getValue()) {
                    CardVisitorHandler cardVisitorHandler = new CardVisitorHandler(player, sender);
                    cardVisitorHandler.addAdditionalAttributesForLeaderAction(leaderAction, basicRewards);
                    additionalCardInfo.acceptCardVisitor(cardVisitorHandler);
                }
            }
        }
    }

    public void getAndRunLeaderAdditionalCardInfoConsumable() {
        for (Map.Entry<String, List<AdditionalCardInfo>> entry : AdditionalInfoMaps.getPermanentEffectsOnChoice().entrySet()) {
            if (entry.getKey().equals(leaderAction.getLeaderName())) {
                for (AdditionalCardInfo additionalCardInfo : entry.getValue()) {
                    player.getLeaderCards().forEach(leaderCard -> {
                        if (leaderCard.isPlayable()) {
                            CardVisitorHandler cardVisitorHandler = new CardVisitorHandler(player, sender);
                            cardVisitorHandler.addAdditionalAttributesForLeaderAction(leaderAction, basicRewards);
                            additionalCardInfo.acceptCardVisitor(cardVisitorHandler);
                            leaderCard.setPlayable(false);
                        }
                    });
                }
            }
        }
    }

    public void addAttributesToCalculateBoardRewards(BoardAction boardAction) {
        this.boardAction = boardAction;
        actionType = boardAction.getBasicAction().getActionType();
        boardIdentifier = boardAction.getBasicAction().getBoardIdentifier();
    }
    public void generateRewardsForBoardAction() {
        switch (actionType) {
            case GREEN_TOWER: {
                greenTowerCase();
                break;
            }
            case YELLOW_TOWER: {
                yellowTowerCase();
                break;
            }
            case BLUE_TOWER: {
                blueTowerCase();
                break;
            }
            case PURPLE_TOWER: {
                purpleTowerCase();
                break;
            }
            case COUNCIL_PALACE: {
                councilPalaceCase();
                break;
            }
            case PRODUCTION: {
                productionCase();
                break;
            }
            case HARVEST: {
                harvestCase();
                break;
            }
            case MARKET: {
                marketCase();
                break;
            }
        }
        int newServant = player.getPlayerGoods().getResources().getServants() - boardAction.getNumberOfServants();
        player.getPlayerGoods().getResources().setServants(newServant);
        giveRewardsToPlayer();
    }


    public void greenTowerCase() {
        LOGGER.info("Action type: green tower");
        Optional<TowerSlot> optionalTowerSlot = generateBasicRewardForTowerAction();
        if (optionalTowerSlot.isPresent()) {
            TowerSlot towerSlot = optionalTowerSlot.get();
            player.getPlayerBoard().getDeck().getTerritories().add((Territory)towerSlot.getDevelopmentCard());
            towerCaseGeneralOperations(towerSlot);
        }
    }

    public Optional<TowerSlot> generateBasicRewardForTowerAction() {
        Optional<TowerSlot> optionalTowerSlot = findTowerSlotByBoardIdentifier();
        if (optionalTowerSlot.isPresent()) {
            TowerSlot towerSlot = optionalTowerSlot.get();
            basicRewards.add(new BasicRewards(actionType, towerSlot.getInstantGoods()));
        }
        return optionalTowerSlot;
    }

    public Optional<TowerSlot> findTowerSlotByBoardIdentifier() {
        for (Tower tower : board.getTowers()) {
            for (TowerSlot towerSlot : tower.getTowerSlots()) {
                if (towerSlot.getSpace().getBoardIdentifier() == boardIdentifier) {
                    return Optional.of(towerSlot);
                }
            }
        }
        return Optional.empty();
    }

    public void towerCaseGeneralOperations(TowerSlot towerSlot) {
        getAndRunAdditionalCardInfoConsumable(towerSlot.getDevelopmentCard());
        player.getPlayerGoods().subtractAll(towerSlot.getDevelopmentCard().getCosts().get(0));
        towerSlot.setDevelopmentCard(null);
        towerSlot.getSpace().setAlreadyTaken(true);
        towerSlot.getSpace().setPlayerPawn(new PlayerPawn(player.getPlayerDetails(), boardAction.getPawnColor()));
        basicRewards.add(generateBasicRewardsWithActualActionDescriptionGivenGoods(towerSlot.getInstantGoods()));
        setPlayerPawnAsPlacedOnBoard();
    }

    public BasicRewards generateBasicRewardsWithActualActionDescriptionGivenGoods(Goods goods) {
        return new BasicRewards(actionType, goods);
    }

    public void setPlayerPawnAsPlacedOnBoard() {
        if (player.getPawnGivenColor(boardAction.getPawnColor()).isPresent()) {
            player.getPawnGivenColor(boardAction.getPawnColor()).get().setPlacedOnBoard(true);
        }
    }

    public void getAndRunAdditionalCardInfoConsumable(DevelopmentCard developmentCard) {
        runAdditionalCardInfoWithBoardAction(developmentCard.getCardInformation().getName(),
                AdditionalInfoMaps.getFlashEffectsOnChoice());
        runAdditionalCardInfoWithBoardAction(developmentCard.getCardInformation().getName(),
                AdditionalInfoMaps.getFlashEffectsNotSelectable());
    }

    public void runAdditionalCardInfoWithBoardAction(String cardName, Map<String, List<AdditionalCardInfo>> effectsForCards) {
        for (Map.Entry<String, List<AdditionalCardInfo>> entry : effectsForCards.entrySet()) {
            if (entry.getKey().equals(cardName)) {
                for (AdditionalCardInfo additionalCardInfo : entry.getValue()) {
                    CardVisitorHandler cardVisitorHandler = new CardVisitorHandler(player, sender);
                    cardVisitorHandler.addAdditionalAttributesForBoardAction(boardAction, basicRewards);
                    additionalCardInfo.acceptCardVisitor(cardVisitorHandler);
                }
            }
        }
    }

    public void yellowTowerCase() {
        LOGGER.info("Action type: yellow tower");
        Optional<TowerSlot> optionalTowerSlot = generateBasicRewardForTowerAction();
        if (optionalTowerSlot.isPresent()) {
            TowerSlot towerSlot = optionalTowerSlot.get();
            player.getPlayerBoard().getDeck().getBuildings().add((Building)towerSlot.getDevelopmentCard());
            towerCaseGeneralOperations(towerSlot);
        }
    }

    public void blueTowerCase() {
        LOGGER.info("Action type: blue tower");
        Optional<TowerSlot> optionalTowerSlot = generateBasicRewardForTowerAction();
        if (optionalTowerSlot.isPresent()) {
            TowerSlot towerSlot = optionalTowerSlot.get();
            player.getPlayerBoard().getDeck().getCharacters().add((Character)towerSlot.getDevelopmentCard());
            towerCaseGeneralOperations(towerSlot);
        }
    }

    public void purpleTowerCase() {
        LOGGER.info("Action type: purple tower");
        Optional<TowerSlot> optionalTowerSlot = generateBasicRewardForTowerAction();
        if (optionalTowerSlot.isPresent()) {
            TowerSlot towerSlot = optionalTowerSlot.get();
            player.getPlayerBoard().getDeck().getVentures().add((Venture)towerSlot.getDevelopmentCard());
            towerCaseGeneralOperations(towerSlot);
        }
    }

    public void councilPalaceCase() {
        board.getCouncilPalace().getPlayerPawnList().add(new PlayerPawn(player.getPlayerDetails(), boardAction.getPawnColor()));
        Goods goods = board.getCouncilPalace().getInstantGoods().getGoods();
        basicRewards.add(generateBasicRewardsWithActualActionDescriptionGivenGoods(goods));
        Goods chosenGoods = CouncilPrivilege.getPossibleChoices().get(boardAction.getPositionExchangingGoodsChosen().get(0));
        basicRewards.add(generateBasicRewardsWithActualActionDescriptionGivenGoods(chosenGoods));
    }

    public void productionCase() {
        int numberOfCouncilPrivilege = 0;
        for (Building building : player.getPlayerBoard().getDeck().getBuildings()) {
            if (building.getProductionResult().getGoods().isEmpty()) {
                numberOfCouncilPrivilege += building.getProductionResult().getNumberOfCouncilPrivilege();
                CardVisitorHandler cardVisitorHandler = new CardVisitorHandler(player, sender);
                cardVisitorHandler.addAdditionalAttributesForBoardAction(boardAction, basicRewards);
                getAndRunAdditionalCardInfoPermanent(building);
            } else {
                if (checkIfBuildingHasConditionalProduction(building)) {
                    getAndRunAdditionalCardInfoPermanent(building);
                } else {
                    SpaceActionRequirements spaceActionRequirements = new SpaceActionRequirements(actionType,
                            boardAction.getPawnColor(), building.getProductionActionValueRequired(),
                            boardAction.getBasicAction().getActionValue(), boardAction.getNumberOfServants(), false);
                    Requirements requirements = new BoardActionRequirements(spaceActionRequirements, getProductionSpaceMalus());
                    if (requirements.hasRequirements(player)) {
                        Goods goods = building.getProductionResult().getGoods();
                        basicRewards.add(generateBasicRewardsWithActualActionDescriptionGivenGoods(goods));
                        numberOfCouncilPrivilege += building.getProductionResult().getNumberOfCouncilPrivilege();
                    }
                }
            }
        }
        sendCouncilPrivilegeChoice(numberOfCouncilPrivilege);
    }

    public void getAndRunAdditionalCardInfoPermanent(DevelopmentCard developmentCard) {
        runAdditionalCardInfoWithBoardAction(developmentCard.getCardInformation().getName(),
                AdditionalInfoMaps.getPermanentEffectsNotSelectable());
        runAdditionalCardInfoWithBoardAction(developmentCard.getCardInformation().getName(),
                AdditionalInfoMaps.getPermanentEffectsOnChoice());
    }

    public boolean checkIfBuildingHasConditionalProduction(DevelopmentCard developmentCard) {
        for (Map.Entry<String, List<AdditionalCardInfo>> entry : AdditionalInfoMaps.getPermanentEffectsOnChoice().entrySet()) {
            if (entry.getKey().equals(developmentCard.getCardInformation().getName())) {
                return true;
            }
        }
        return false;
    }

    public int getProductionSpaceMalus() {
        for (ProductionHarvestSpace productionHarvestSpace : board.getBoardActionSpaces().getProductionArea()) {
            if (productionHarvestSpace.getSpace().getBoardIdentifier() == boardAction.getBasicAction().getBoardIdentifier()) {
                return productionHarvestSpace.getMalusValue();
            }
        }
        return 0;
    }

    public void sendCouncilPrivilegeChoice(int numberOfCouncilPrivilege) {
        if (numberOfCouncilPrivilege > 0) {
            CouncilPrivilegeChoice councilPrivilegeChoice = new CouncilPrivilegeChoice(numberOfCouncilPrivilege);
            sender.sendTo(player.getPlayerDetails().getPlayerName(), councilPrivilegeChoice);
        }
    }

    public void harvestCase() {
        int numberOfCouncilPrivilege = 0;
        for (Territory territory : player.getPlayerBoard().getDeck().getTerritories()) {
            SpaceActionRequirements spaceActionRequirements = new SpaceActionRequirements(actionType,
                    boardAction.getPawnColor(), territory.getHarvestActionValueRequired(),
                    boardAction.getBasicAction().getActionValue(), boardAction.getNumberOfServants(), false);
            Requirements requirements = new BoardActionRequirements(spaceActionRequirements, getHarvestSpaceMalus());
            if (requirements.hasRequirements(player)) {
                Goods goods = territory.getHarvestResult().getGoods();
                basicRewards.add(generateBasicRewardsWithActualActionDescriptionGivenGoods(goods));
                numberOfCouncilPrivilege += territory.getHarvestResult().getNumberOfCouncilPrivilege();
            }
        }
        sendCouncilPrivilegeChoice(numberOfCouncilPrivilege);
    }

    public int getHarvestSpaceMalus() {
        for (ProductionHarvestSpace productionHarvestSpace : board.getBoardActionSpaces().getHarvestArea()) {
            if (productionHarvestSpace.getSpace().getBoardIdentifier() == boardAction.getBasicAction().getBoardIdentifier()) {
                return productionHarvestSpace.getMalusValue();
            }
        }
        return 0;
    }

    public void marketCase() {
        for (MarketSpace marketSpace : board.getBoardActionSpaces().getMarketArea()) {
            if (marketSpace.getSpace().getBoardIdentifier() == boardAction.getBasicAction().getBoardIdentifier()) {
                Goods goods = marketSpace.getExchangingGoods().getGoods();
                basicRewards.add(generateBasicRewardsWithActualActionDescriptionGivenGoods(goods));
                marketSpace.getSpace().setAlreadyTaken(true);
                marketSpace.getSpace().setPlayerPawn(new PlayerPawn(player.getPlayerDetails(), boardAction.getPawnColor()));
                int numberOfCouncilPrivilege = marketSpace.getExchangingGoods().getNumberOfCouncilPrivilege();
                sendCouncilPrivilegeChoice(numberOfCouncilPrivilege);
                break;
            }
        }
    }

    public void giveRewardsToPlayer() {
        for (BasicRewards basicReward : basicRewards) {
            player.getRewardsModifiers().forEach(m -> m.modifyRewards(basicReward));
            player.getPlayerGoods().addAll(basicReward.calculateFinalRewards());
        }
    }

    /*
        GETTERS AND SETTERS
     */

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

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public List<BasicRewards> getBasicRewards() {
        return basicRewards;
    }

    public void setBasicRewards(List<BasicRewards> basicRewards) {
        this.basicRewards = basicRewards;
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
