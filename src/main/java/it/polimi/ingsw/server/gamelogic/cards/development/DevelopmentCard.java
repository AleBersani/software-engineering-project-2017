package it.polimi.ingsw.server.gamelogic.cards.development;

import it.polimi.ingsw.server.gamelogic.basics.Goods;

import java.util.List;
import java.util.Objects;

/**
 * This Class allows to declare all the different kinds of Development Cards as the same type
 * whenever we need to use and represent them as a set of objects with the same behaviour
 * @see it.polimi.ingsw.server.gamelogic.board.TowerSlot
 */
public abstract class DevelopmentCard {
    private BasicDevelopmentCard basicDevelopmentCard;

    public DevelopmentCard(BasicDevelopmentCard basicDevelopmentCard) {
        this.basicDevelopmentCard = basicDevelopmentCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DevelopmentCard that = (DevelopmentCard) o;
        return Objects.equals(getBasicDevelopmentCard(), that.getBasicDevelopmentCard());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBasicDevelopmentCard());
    }

    public CardInformation getCardInformation() {
        return basicDevelopmentCard.getCardInformation();
    }

    public List<Goods> getCosts() {
        return basicDevelopmentCard.getCosts();
    }

    public BasicDevelopmentCard getBasicDevelopmentCard() {
        return basicDevelopmentCard;
    }

    public void setBasicDevelopmentCard(BasicDevelopmentCard basicDevelopmentCard) {
        this.basicDevelopmentCard = basicDevelopmentCard;
    }
}