package it.polimi.ingsw.gamelogic.actions.description;

import it.polimi.ingsw.gamelogic.actions.ActionVisitor;
import it.polimi.ingsw.gamelogic.basics.Goods;

/**
 * Class that describes actions related to collecting a card from the Towers.
 * This action can eventually have a "bonus" (called discount) that represents a discount on the action's cost.
 */
public class CardAction implements ActionDescription {
    private BasicAction basicAction;

    private int numberOfServants;
    private Goods discount;

    public CardAction(BasicAction basicAction) {
        this.basicAction = basicAction;
        numberOfServants = 0;
        discount = new Goods();
    }

    public CardAction(BasicAction basicAction, int numberOfServants) {
        this.basicAction = basicAction;
        this.numberOfServants = numberOfServants;
        discount = new Goods();
    }

    public CardAction(BasicAction basicAction, Goods discount) {
        this.basicAction = basicAction;
        this.discount = discount;
        numberOfServants = 0;
    }

    public CardAction(BasicAction basicAction, int numberOfServants, Goods discount) {
        this.basicAction = basicAction;
        this.numberOfServants = numberOfServants;
        this.discount = discount;
    }

    /**
     * Visitor method
     * @param actionVisitor concrete visitor
     */
    @Override
    public void acceptActionVisitor(ActionVisitor actionVisitor) {
        actionVisitor.visitActionDescription(this);
    }

    public BasicAction getBasicAction() {
        return basicAction;
    }

    public void setBasicAction(BasicAction basicAction) {
        this.basicAction = basicAction;
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
