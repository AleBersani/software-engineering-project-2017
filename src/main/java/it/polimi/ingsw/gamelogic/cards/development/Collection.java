package it.polimi.ingsw.gamelogic.cards.development;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.basics.Goods;

import java.util.List;

/**
 * This Class allows to declare all the different kinds of Development Cards as the same type
 * whenever we need to use and represent them as a set of objects with the same behaviour
 * @see it.polimi.ingsw.gamelogic.board.TowerSlot
 */
public abstract class Collection {
    private DevelopmentCard developmentCard;

    public Collection(DevelopmentCard developmentCard) {
        this.developmentCard = developmentCard;
    }

    /**
     * TODO: JavaDoc
     * @return
     */
    public CardInformation getCardInformation() {
        return developmentCard.getCardInformation();
    }

    /**
     * TODO: JavaDoc
     * @return
     */
    public List<Goods> getCosts() {
        return developmentCard.getCosts();
    }

    public DevelopmentCard getDevelopmentCard() {
        return developmentCard;
    }

    public void setDevelopmentCard(DevelopmentCard developmentCard) {
        this.developmentCard = developmentCard;
    }
}
