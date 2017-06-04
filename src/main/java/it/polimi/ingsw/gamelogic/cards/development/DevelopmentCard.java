package it.polimi.ingsw.gamelogic.cards.development;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.basics.Goods;

import java.util.List;

/**
 * Class that describes the Development cards in general, with basic information, a cost defined as a list
 * of Goods and an Exchanging Good as an instant reward
 */
public class DevelopmentCard {
    private CardInformation cardInformation;
    private List<Goods> costs;

    public DevelopmentCard(CardInformation cardInformation, List<Goods> costs) {
        this.cardInformation = cardInformation;
        this.costs = costs;
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
}
