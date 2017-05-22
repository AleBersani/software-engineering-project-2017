package it.polimi.ingsw.gamelogic.cards.leadercards;

import it.polimi.ingsw.gamelogic.basics.CardCost;

public class LeaderCard {
    private String name;
    private CardCost cardCost;

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
