package it.polimi.ingsw.gamelogic.modifiers.requirements;

import it.polimi.ingsw.gamelogic.enums.ActionType;
import it.polimi.ingsw.gamelogic.enums.PawnColor;

/**
 * Class that describes the requirements of an action originated by an Action Space
 */
public class SpaceActionRequirements {
    private ActionType actionType;
    private PawnColor pawnColor;
    private int actionValue;
    private int numberOfServants;
    private boolean occupied;

    public SpaceActionRequirements(ActionType actionType, PawnColor pawnColor, int actionValue,
                                   int numberOfServants, boolean occupied) {
        this.actionType = actionType;
        this.pawnColor = pawnColor;
        this.actionValue = actionValue;
        this.numberOfServants = numberOfServants;
        this.occupied = occupied;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public PawnColor getPawnColor() {
        return pawnColor;
    }

    public void setPawnColor(PawnColor pawnColor) {
        this.pawnColor = pawnColor;
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

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
