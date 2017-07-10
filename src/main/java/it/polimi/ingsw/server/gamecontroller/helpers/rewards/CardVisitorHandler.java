package it.polimi.ingsw.server.gamecontroller.helpers.rewards;

import it.polimi.ingsw.server.gamecontroller.helpers.Sender;
import it.polimi.ingsw.server.gameelements.BoardInformation;
import it.polimi.ingsw.server.gameelements.Cards;
import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.board.ProductionHarvestSpace;
import it.polimi.ingsw.server.gamelogic.cards.CardVisitor;
import it.polimi.ingsw.server.gamelogic.cards.additionalinfo.*;
import it.polimi.ingsw.server.gamelogic.cards.development.Building;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.BoardActionRequirements;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.SpaceActionRequirements;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers.RequirementsModifier;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.BasicRewards;
import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.shared.model.actionsdescription.BoardAction;
import it.polimi.ingsw.shared.model.actionsdescription.LeaderAction;
import it.polimi.ingsw.shared.requests.serverclient.CouncilPrivilegeChoice;
import it.polimi.ingsw.shared.requests.serverclient.LorenzoRequest;

import java.util.List;

public class CardVisitorHandler implements CardVisitor {
    private Player player;
    private Sender sender;

    private BoardAction boardAction;
    private LeaderAction leaderAction;
    private List<BasicRewards> basicRewardsList;

    public CardVisitorHandler(Player player, Sender sender) {
        this.player = player;
        this.sender = sender;
    }

    public void addAdditionalAttributesForBoardAction(BoardAction boardAction, List<BasicRewards> basicRewards) {
        this.boardAction = boardAction;
        this.basicRewardsList = basicRewards;
    }

    public void addAdditionalAttributesForLeaderAction(LeaderAction leaderAction, List<BasicRewards> basicRewards) {
        this.leaderAction = leaderAction;
        this.basicRewardsList = basicRewards;
    }

    @Override
    public void visitAdditionalCardInfo(CardFlashAction cardFlashAction) {

    }

    @Override
    public void visitAdditionalCardInfo(CardFlashExchangingGoods cardFlashExchangingGoods) {
        BasicRewards basicRewards = new BasicRewards(boardAction.getBasicAction().getActionType(),
                cardFlashExchangingGoods.getExchangingGoods().getGoods());
        basicRewardsList.add(basicRewards);
        sendCouncilPrivilegeChoice(cardFlashExchangingGoods.getExchangingGoods().getNumberOfCouncilPrivilege());
    }

    public void sendCouncilPrivilegeChoice(int numberOfCouncilPrivilege) {
        if (numberOfCouncilPrivilege > 0) {
            CouncilPrivilegeChoice councilPrivilegeChoice = new CouncilPrivilegeChoice(numberOfCouncilPrivilege);
            sender.sendTo(player.getPlayerDetails().getPlayerName(), councilPrivilegeChoice);
        }
    }

    @Override
    public void visitAdditionalCardInfo(ChurchSustainBonus churchSustainBonus) {
        player.getPlayerCardsEffects().setChurchSustainBonus(churchSustainBonus.getBonus());
    }

    @Override
    public void visitAdditionalCardInfo(ConditionalProduction conditionalProduction) {
        String cardName = conditionalProduction.getName();
        for (Building building : Cards.getBuildings()) {
            if (building.getCardInformation().getName().equals(cardName)) {
                SpaceActionRequirements spaceActionRequirements = new SpaceActionRequirements(
                        boardAction.getBasicAction().getActionType(), boardAction.getPawnColor(),
                        building.getProductionActionValueRequired(), boardAction.getBasicAction().getActionValue(),
                        boardAction.getNumberOfServants(), false);
                BoardActionRequirements boardActionRequirements =
                        new BoardActionRequirements(spaceActionRequirements, getProductionMalus());

                player.getRequirementsModifiers().forEach(m -> m.modifyRequirements(boardActionRequirements));

                if (boardActionRequirements.hasRequirements(player)) {
                    BasicRewards basicRewards = new BasicRewards(boardAction.getBasicAction().getActionType(),
                            building.getProductionResult().getGoods());
                    basicRewardsList.add(basicRewards);
                    sendCouncilPrivilegeChoice(building.getProductionResult().getNumberOfCouncilPrivilege());
                }
                break;
            }
        }
    }

    public int getProductionMalus() {
        for (ProductionHarvestSpace productionHarvestSpace : BoardInformation.getHarvestArea()) {
            if (productionHarvestSpace.getSpace().getBoardIdentifier() ==
                    boardAction.getBasicAction().getBoardIdentifier()) {
                return productionHarvestSpace.getMalusValue();
            }
        }
        return 0;
    }

    @Override
    public void visitAdditionalCardInfo(GoodsBasedOnPossessions goodsBasedOnPossessions) {
        int count = player.countGivenIdentifier(goodsBasedOnPossessions.getTypeOfObjectRequired());
        int numberOfRewards = count % goodsBasedOnPossessions.getNumberOfObjectRequired();
        Goods result = new Goods();
        for (int i = 0; i < numberOfRewards; i++) {
            result.addAll(goodsBasedOnPossessions.getRewardForPossession());
        }
        BasicRewards basicRewards = new BasicRewards(boardAction.getBasicAction().getActionType(), result);
        basicRewardsList.add(basicRewards);
    }

    @Override
    public void visitAdditionalCardInfo(Lorenzo lorenzo) {
        sender.sendTo(player.getPlayerDetails().getPlayerName(), new LorenzoRequest());
    }

    @Override
    public void visitAdditionalCardInfo(MultipleProduction multipleProduction) {
        Goods requiredGood = multipleProduction.getCosts().get(0);
        ExchangingGoods resultGoods = multipleProduction.getResult().get(0);
        BasicRewards basicRewards = new BasicRewards(boardAction.getBasicAction().getActionType(), resultGoods.getGoods());
        if (requiredGood.getPoints().isEmpty()) {
            if (requiredGood.hasLessResourcesThan(player.getPlayerGoods())) {
                player.getPlayerGoods().subtractAll(requiredGood);
                basicRewardsList.add(basicRewards);
            }
        } else if (requiredGood.getResources().isEmpty()) {
            if (requiredGood.hasLessPointsThan(player.getPlayerGoods())) {
                player.getPlayerGoods().subtractAll(requiredGood);
                basicRewardsList.add(basicRewards);
            }
        }
        sendCouncilPrivilegeChoice(resultGoods.getNumberOfCouncilPrivilege());
    }

    @Override
    public void visitAdditionalCardInfo(RequirementsOnCard requirementsOnCard) {
        for (RequirementsModifier requirementsModifier : requirementsOnCard.getRequirementsModifiers()) {
            player.getPlayerCardsEffects().addRequirementsModifier(requirementsModifier);
        }
    }

    @Override
    public void visitAdditionalCardInfo(RewardsOnCard rewardsOnCard) {
        player.getPlayerCardsEffects().addRewardsModifier(rewardsOnCard.getRewardsModifier());
    }

    @Override
    public void visitAdditionalCardInfo(PlayerOrderWeight playerOrderWeight) {
        player.getPlayerCardsEffects().setPlayerOrderWeight(playerOrderWeight.getWeight());
    }
}
