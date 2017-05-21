package it.polimi.ingsw.gamelogic.cards.leadercards;

import it.polimi.ingsw.gamelogic.basics.CardCost;

public abstract class LeaderCard {
    protected String name;
    protected CardCost cardCost;
    /*
    TODO: add decorator already instantiated
     */

    public LeaderCard(String name, CardCost cardCost) {
        this.name = name;
        this.cardCost = cardCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CardCost getCardCost() {
        return cardCost;
    }

    public void setCardCost(CardCost cardCost) {
        this.cardCost = cardCost;
    }
}
