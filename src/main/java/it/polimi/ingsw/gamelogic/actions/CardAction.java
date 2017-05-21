package it.polimi.ingsw.gamelogic.actions;

import it.polimi.ingsw.gamelogic.basics.Goods;

/**
 * Class that describes actions related to collecting a card from the Towers.
 * This action can eventually have a "bonus" (called discount) that represents a discount on the action's cost.
 */
public class CardAction implements ActionSupplement {
    private String spaceIdentifier;
    private int actionValue;

    private int numberOfServants;
    private Goods discount;

    public CardAction(String spaceIdentifier, int actionValue) {
        this.spaceIdentifier = spaceIdentifier;
        this.actionValue = actionValue;
        numberOfServants = 0;
        discount = new Goods();
    }

    public String getSpaceIdentifier() {
        return spaceIdentifier;
    }

    public void setSpaceIdentifier(String spaceIdentifier) {
        this.spaceIdentifier = spaceIdentifier;
    }

    public int getActionValue() {
        return actionValue;
    }

    public void setActionValue(int actionValue) {
        this.actionValue = actionValue;
    }

    public int getNumberOfServants() {
        return numberOfServants;
    }

    public void setNumberOfServants(int numberOfServants) {
        this.numberOfServants = numberOfServants;
    }

    public Goods getDiscount() {
        return discount;
    }

    public void setDiscount(Goods discount) {
        this.discount = discount;
    }
}
