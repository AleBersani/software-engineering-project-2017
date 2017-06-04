package it.polimi.ingsw.gamelogic.cards;

import it.polimi.ingsw.gamelogic.cards.additionalinfo.*;

/**
 * Interface that defines methods that are implemented by the classes that want to call the Visitors
 */
public interface CardVisitor {
    void visitAdditionalCardInfo(CardFlashAction cardFlashAction);
    void visitAdditionalCardInfo(CardFlashExchangingGoods cardFlashExchangingGoods);
    void visitAdditionalCardInfo(ConditionalProduction conditionalProduction);
    void visitAdditionalCardInfo(GoodsBasedOnPossessions goodsBasedOnPossessions);
    void visitAdditionalCardInfo(MinRequiredOnCost minRequiredOnCost);
    void visitAdditionalCardInfo(MultipleProduction multipleProduction);
    void visitAdditionalCardInfo(RequirementsOnCard requirementsOnCard);
    void visitAdditionalCardInfo(RewardsOnCard rewardsOnCard);
}
