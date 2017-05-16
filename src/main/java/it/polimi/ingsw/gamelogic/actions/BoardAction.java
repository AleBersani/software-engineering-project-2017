package it.polimi.ingsw.gamelogic.actions;

import it.polimi.ingsw.gamelogic.actions.ActionDescription;

/**
 * This Class describes an action concerning the activation of an Action Space on the Board.
 * It can be set by the player using the effect of a Card (Leader or Development).
 */
public class BoardAction extends ActionDescription {
    private String spaceIdentifier;
    private int actionValue;

    private int numberOfServants;
    private String pawnColor;

    public BoardAction(String actionIdentifier, String spaceIdentifier, int actionValue) {
        super(actionIdentifier);
        this.spaceIdentifier = spaceIdentifier;
        this.actionValue = actionValue;
        numberOfServants = 0;
        pawnColor = "";
    }

    @Override
    public String getConcatenatedActionIdentifier() {
        return actionIdentifier + ":" + spaceIdentifier;
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

    public String getPawnColor() {
        return pawnColor;
    }

    public void setPawnColor(String pawnColor) {
        this.pawnColor = pawnColor;
    }
}
