package it.polimi.ingsw.gamelogic.cards.development;

import it.polimi.ingsw.gamelogic.basics.Goods;

import java.util.List;
import java.util.Objects;

/**
 * Class that describes the Development cards in general, with basic information, a cost defined as a list
 * of Goods and an Exchanging Good as an instant reward
 */
public class BasicDevelopmentCard {
    private CardInformation cardInformation;
    private List<Goods> costs;

    public BasicDevelopmentCard(CardInformation cardInformation, List<Goods> costs) {
        this.cardInformation = cardInformation;
        this.costs = costs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BasicDevelopmentCard that = (BasicDevelopmentCard) o;
        return Objects.equals(getCardInformation(), that.getCardInformation()) &&
                Objects.equals(getCosts(), that.getCosts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardInformation(), getCosts());
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
