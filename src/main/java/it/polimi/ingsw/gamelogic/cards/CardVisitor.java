package it.polimi.ingsw.gamelogic.cards;

import it.polimi.ingsw.gamelogic.cards.additionalinfo.CardFlashExchangingGoods;
import it.polimi.ingsw.gamelogic.cards.additionalinfo.MinRequiredOnCost;

/**
 * TODO: JavaDoc
 */
public interface CardVisitor {
    void visitCardFlashExchangingGoods(CardFlashExchangingGoods cardFlashExchangingGoods);
    void visitMinRequiredOnCost(MinRequiredOnCost minRequiredOnCost);
    /**
     * TODO
     */
}
