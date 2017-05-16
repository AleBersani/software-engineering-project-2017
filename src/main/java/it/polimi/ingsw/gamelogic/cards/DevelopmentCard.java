package it.polimi.ingsw.gamelogic.cards;

import it.polimi.ingsw.gamelogic.basics.Goods;

import java.util.List;

public abstract class DevelopmentCard {
    protected CardInformation cardInformation;
    protected List<Goods> cost;
    protected FlashEffect instantEffect;

    public DevelopmentCard(CardInformation cardInformation, List<Goods> cost, FlashEffect instantEffect) {
        this.cardInformation = cardInformation;
        this.cost = cost;
        this.instantEffect = instantEffect;
    }

    public CardInformation getCardInformation() {
        return cardInformation;
    }

    public void setCardInformation(CardInformation cardInformation) {
        this.cardInformation = cardInformation;
    }

    public List<Goods> getCost() {
        return cost;
    }

    public void setCost(List<Goods> cost) {
        this.cost = cost;
    }

    public FlashEffect getInstantEffect() {
        return instantEffect;
    }

    public void setInstantEffect(FlashEffect instantEffect) {
        this.instantEffect = instantEffect;
    }
}
