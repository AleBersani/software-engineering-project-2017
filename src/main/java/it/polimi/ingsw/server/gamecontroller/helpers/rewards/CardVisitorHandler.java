package it.polimi.ingsw.server.gamecontroller.helpers.rewards;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.cards.CardVisitor;
import it.polimi.ingsw.server.gamelogic.cards.additionalinfo.*;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers.RequirementsModifier;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.BasicRewards;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.modifiers.RewardsModifier;
import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.shared.model.actionsdescription.BoardAction;

public class CardVisitorHandler implements CardVisitor {
    private Player player;
    private BoardAction boardAction;

    public CardVisitorHandler(Player player, BoardAction boardAction) {
        this.player = player;
        this.boardAction = boardAction;
    }

    @Override
    public void visitAdditionalCardInfo(CardFlashAction cardFlashAction) {

    }

    @Override
    public void visitAdditionalCardInfo(CardFlashExchangingGoods cardFlashExchangingGoods) {
        BasicRewards basicRewards = new BasicRewards(boardAction.getBasicAction().getActionType(),
                cardFlashExchangingGoods.getExchangingGoods().getGoods());
        for (RewardsModifier rewardsModifier : player.getRewardsModifiers()) {
            rewardsModifier.modifyRewards(basicRewards);
        }
        player.getPlayerGoods().addAll(basicRewards.calculateFinalRewards());
        System.out.println("Ho dato a " + player.getPlayerDetails().getPlayerName() + ": " + cardFlashExchangingGoods.getExchangingGoods().getGoods());
    }

    @Override
    public void visitAdditionalCardInfo(ChurchSustainBonus churchSustainBonus) {
        player.getPlayerCardsEffects().setChurchSustainBonus(churchSustainBonus.getBonus());
    }

    @Override
    public void visitAdditionalCardInfo(ConditionalProduction conditionalProduction) {

    }

    @Override
    public void visitAdditionalCardInfo(GoodsBasedOnPossessions goodsBasedOnPossessions) {

    }

    @Override
    public void visitAdditionalCardInfo(MultipleProduction multipleProduction) {

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
