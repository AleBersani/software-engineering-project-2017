package it.polimi.ingsw.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.cards.CardVisitor;

/**
 * Class that describes the immediate action of a card. The Flash Effect takes place as the player picks up the
 * card from the board, in this case the player gets an Exchanging Good, so either a Good or a Council's Privilege
 * that can be converted also in a Good
 */
public class CardFlashExchangingGoods extends AdditionalCardInfo {
    private ExchangingGoods exchangingGoods;

    public CardFlashExchangingGoods(String name, ExchangingGoods exchangingGoods) {
        super(name);
        this.exchangingGoods = exchangingGoods;
    }

    @Override
    public void acceptCardVisitor(CardVisitor cardVisitor) {
        cardVisitor.visitAdditionalCardInfo(this);
    }

    public ExchangingGoods getExchangingGoods() {
        return exchangingGoods;
    }

    public void setExchangingGoods(ExchangingGoods exchangingGoods) {
        this.exchangingGoods = exchangingGoods;
    }
}
