package it.polimi.ingsw.gamelogic.actions;

import it.polimi.ingsw.gamecontroller.Action;
import it.polimi.ingsw.gamelogic.enums.PawnColor;

/**
 * This Class describes an action concerning the activation of an Action Space on the Board.
 * It can be set by the player using the effect of a Card (Leader or Development).
 */
public class BoardAction implements ActionDescription {
    private BasicAction basicAction;

    private PawnColor pawnColor;
    private int numberOfServants;

    public BoardAction(BasicAction basicAction) {
        this.basicAction = basicAction;
        pawnColor = PawnColor.UNCOLORED;
        numberOfServants = 0;
    }

    public BoardAction(BasicAction basicAction, PawnColor pawnColor) {
        this.basicAction = basicAction;
        this.pawnColor = pawnColor;
    }

    public BoardAction(BasicAction basicAction, int numberOfServants) {
        this.basicAction = basicAction;
        this.numberOfServants = numberOfServants;
    }

    public BoardAction(BasicAction basicAction, PawnColor pawnColor, int numberOfServants) {
        this.basicAction = basicAction;
        this.pawnColor = pawnColor;
        this.numberOfServants = numberOfServants;
    }

    @Override
    public void callAction(Action action) {
        action.readAction(this);
    }

    public BasicAction getBasicAction() {
        return basicAction;
    }

    public void setBasicAction(BasicAction basicAction) {
        this.basicAction = basicAction;
    }

    public PawnColor getPawnColor() {
        return pawnColor;
    }

    public void setPawnColor(PawnColor pawnColor) {
        this.pawnColor = pawnColor;
    }

    public int getNumberOfServants() {
        return numberOfServants;
    }

    public void setNumberOfServants(int numberOfServants) {
        this.numberOfServants = numberOfServants;
    }
}
