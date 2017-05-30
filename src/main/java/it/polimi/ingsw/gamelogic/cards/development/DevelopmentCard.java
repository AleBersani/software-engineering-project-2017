package it.polimi.ingsw.gamelogic.cards.development;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.basics.Goods;

import java.util.List;

/**
 * TODO: JavaDoc
 */
public class DevelopmentCard {
    private CardInformation cardInformation;
    private List<Goods> costs;
    private ExchangingGoods instantExchangingGoods;

    public DevelopmentCard(CardInformation cardInformation, List<Goods> costs, ExchangingGoods instantExchangingGoods) {
        this.cardInformation = cardInformation;
        this.costs = costs;
        this.instantExchangingGoods = instantExchangingGoods;
    }

    public CardInformation getCardInformation() {
        return cardInformation;
    }

    public void setCardInformation(CardInformation cardInformation) {
        this.cardInformation = cardInformation;
    }

    public List<Goods> getCosts() {
        return costs;
    }

    public void setCosts(List<Goods> costs) {
        this.costs = costs;
    }

    public ExchangingGoods getInstantExchangingGoods() {
        return instantExchangingGoods;
    }

    public void setInstantExchangingGoods(ExchangingGoods instantExchangingGoods) {
        this.instantExchangingGoods = instantExchangingGoods;
    }
}
