package it.polimi.ingsw.gamelogic.cards;

import it.polimi.ingsw.gamelogic.cards.additionalinfo.*;

/**
 * TODO: JavaDoc
 */
public interface CardVisitor {
    void visitAdditionalCardInfo(CardFlashAction cardFlashAction);
    void visitAdditionalCardInfo(CardFlashExchangingGoods cardFlashExchangingGoods);
    void visitAdditionalCardInfo(ConditionalProduction conditionalProduction);
    void visitAdditionalCardInfo(MinRequiredOnCost minRequiredOnCost);
    void visitAdditionalCardInfo(MultipleProduction multipleProduction);
    void visitAdditionalCardInfo(RequirementsOnCard requirementsOnCard);
    void visitAdditionalCardInfo(RewardsOnCard rewardsOnCard);
}
