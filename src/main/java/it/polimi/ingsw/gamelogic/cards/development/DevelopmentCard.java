package it.polimi.ingsw.gamelogic.cards.development;

import it.polimi.ingsw.gamelogic.basics.Goods;

import java.util.List;

/**
 * This Class allows to declare all the different kinds of Development Cards as the same type
 * whenever we need to use and represent them as a set of objects with the same behaviour
 * @see it.polimi.ingsw.gamelogic.board.TowerSlot
 */
public abstract class DevelopmentCard {
    private BasicDevelopmentCard basicDevelopmentCard;

    public DevelopmentCard(BasicDevelopmentCard basicDevelopmentCard) {
        this.basicDevelopmentCard = basicDevelopmentCard;
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