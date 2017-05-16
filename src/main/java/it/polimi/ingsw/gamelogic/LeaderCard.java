package it.polimi.ingsw.gamelogic;

public abstract class LeaderCard {
    protected String name;
    protected CardCost cardCost;

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
