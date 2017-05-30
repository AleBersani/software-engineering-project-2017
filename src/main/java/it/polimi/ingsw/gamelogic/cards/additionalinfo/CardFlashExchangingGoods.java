package it.polimi.ingsw.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.cards.CardVisitor;

/**
 * TODO: JavaDoc
 */
public class CardFlashExchangingGoods extends AdditionalCardInfo {
    private ExchangingGoods exchangingGoods;

    public CardFlashExchangingGoods(String name, ExchangingGoods exchangingGoods) {
        super(name);
        this.exchangingGoods = exchangingGoods;
    }

    @Override
    public void acceptCardVisitor(CardVisitor cardVisitor) {
        cardVisitor.visitCardFlashExchangingGoods(this);
    }

    public ExchangingGoods getExchangingGoods() {
        return exchangingGoods;
    }

    public void setExchangingGoods(ExchangingGoods exchangingGoods) {
        this.exchangingGoods = exchangingGoods;
    }
}
