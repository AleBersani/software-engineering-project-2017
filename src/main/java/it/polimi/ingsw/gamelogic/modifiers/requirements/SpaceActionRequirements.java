package it.polimi.ingsw.gamelogic.modifiers.requirements;

import it.polimi.ingsw.gamelogic.player.Pawn;
import it.polimi.ingsw.gamelogic.player.Player;
import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.PawnColor;

import java.util.Objects;
import java.util.Optional;

/**
 * Class that describes the requirements of an action originated by an Action Space
 */
public class SpaceActionRequirements implements Requirements {
    private ActionType actionType;
    private PawnColor pawnColor;
    private int requiredValue;
    private int actionValue;
    private int numberOfServants;
    private boolean occupied;

    private int initialActionValue;
    private int actionValueModifier;

    public SpaceActionRequirements(ActionType actionType, PawnColor pawnColor, int requiredValue, int actionValue,
                                   int numberOfServants, boolean occupied) {
        this.actionType = actionType;
        this.pawnColor = pawnColor;
        this.requiredValue = requiredValue;
        this.actionValue = actionValue;
        this.numberOfServants = numberOfServants;
        this.occupied = occupied;
        initialActionValue = actionValue;
        actionValueModifier = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SpaceActionRequirements that = (SpaceActionRequirements) o;
        return getRequiredValue() == that.getRequiredValue() &&
                getActionValue() == that.getActionValue() &&
                getNumberOfServants() == that.getNumberOfServants() &&
                isOccupied() == that.isOccupied() &&
                getInitialActionValue() == that.getInitialActionValue() &&
                getActionValueModifier() == that.getActionValueModifier() &&
                getActionType() == that.getActionType() &&
                getPawnColor() == that.getPawnColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActionType(), getPawnColor(), getRequiredValue(), getActionValue(),
                getNumberOfServants(), isOccupied(), getInitialActionValue(), getActionValueModifier());
    }

    /**
     * Checks the pawn if it is present, then checks if it is placed on the board
     * @param player player whose turn it is
     * @return true if all the check are passed
     */
    @Override
    public boolean hasRequirements(Player player) {
        Optional<Pawn> optionalPawn = player.getPawnGivenColor(pawnColor);
        if (optionalPawn.isPresent()) {
            Pawn pawn = optionalPawn.get();
            if (pawn.isPlacedOnBoard())
                return false;
            if (pawn.getValue() != initialActionValue)
                return  false;
        } else {
            return false;
        }

        if (getFinalActionValue() < requiredValue)
            return false;

        if (occupied)
            return false;

        return true;
    }

    public int getFinalActionValue() {
        return actionValue + numberOfServants + actionValueModifier;
    }

    public boolean modifiedActionValue() {
        return actionValue != initialActionValue;
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

    public int getRequiredValue() {
        return requiredValue;
    }

    public void setRequiredValue(int requiredValue) {
        this.requiredValue = requiredValue;
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

    public int getInitialActionValue() {
        return initialActionValue;
    }

    public void setInitialActionValue(int initialActionValue) {
        this.initialActionValue = initialActionValue;
    }

    public int getActionValueModifier() {
        return actionValueModifier;
    }

    public void setActionValueModifier(int actionValueModifier) {
        this.actionValueModifier = actionValueModifier;
    }
}
