package it.polimi.ingsw.server.gamelogic.cards;

import it.polimi.ingsw.server.gamelogic.cards.additionalinfo.*;
import it.polimi.ingsw.server.gamelogic.player.Player;

public class CardVisitorHandler implements CardVisitor {
    private Player player;

    public CardVisitorHandler(Player player) {
        this.player = player;
    }

    @Override
    public void visitAdditionalCardInfo(CardFlashAction cardFlashAction) {

    }

    @Override
    public void visitAdditionalCardInfo(CardFlashExchangingGoods cardFlashExchangingGoods) {

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

    }

    @Override
    public void visitAdditionalCardInfo(RewardsOnCard rewardsOnCard) {

    }

    @Override
    public void visitAdditionalCardInfo(PlayerOrderWeight playerOrderWeight) {

    }
}
