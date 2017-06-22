package it.polimi.ingsw.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.cards.CardVisitor;

import java.util.Objects;

/**
 * Class that describes the immediate action of a Leader card. The Flash Effect takes place as the player uses the
 * card, the player gets an Exchanging Good, so either a Good or a Council's Privilege
 * that can be converted also in a Good
 */
public class CardFlashExchangingGoods extends AdditionalCardInfo {
    private ExchangingGoods exchangingGoods;

    public CardFlashExchangingGoods(String name, ExchangingGoods exchangingGoods) {
        super(name);
        this.exchangingGoods = exchangingGoods;
    }

    @Override
    public String toString() {
        return "CardFlashExchangingGoods{" +
                "exchangingGoods=" + exchangingGoods +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        CardFlashExchangingGoods that = (CardFlashExchangingGoods) o;
        return Objects.equals(getExchangingGoods(), that.getExchangingGoods());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getExchangingGoods());
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
