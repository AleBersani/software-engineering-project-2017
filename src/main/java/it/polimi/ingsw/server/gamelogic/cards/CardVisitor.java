package it.polimi.ingsw.server.gamelogic.cards;

import it.polimi.ingsw.server.gamelogic.cards.additionalinfo.*;

/**
 * Interface that defines methods that are implemented by the classes that want to call the Visitors
 */
public interface CardVisitor {
    void visitAdditionalCardInfo(CardFlashAction cardFlashAction);
    void visitAdditionalCardInfo(CardFlashExchangingGoods cardFlashExchangingGoods);
    void visitAdditionalCardInfo(ChurchSustainBonus churchSustainBonus);
    void visitAdditionalCardInfo(ConditionalProduction conditionalProduction);
    void visitAdditionalCardInfo(GoodsBasedOnPossessions goodsBasedOnPossessions);
    void visitAdditionalCardInfo(Lorenzo lorenzo);
    void visitAdditionalCardInfo(MultipleProduction multipleProduction);
    void visitAdditionalCardInfo(RequirementsOnCard requirementsOnCard);
    void visitAdditionalCardInfo(RewardsOnCard rewardsOnCard);
    void visitAdditionalCardInfo(PlayerOrderWeight playerOrderWeight);
}
