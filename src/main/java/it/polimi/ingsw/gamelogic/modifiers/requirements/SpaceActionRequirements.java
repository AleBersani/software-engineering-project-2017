package it.polimi.ingsw.gamelogic.modifiers.requirements;

import it.polimi.ingsw.gamelogic.enums.ActionType;
import it.polimi.ingsw.gamelogic.enums.PawnColor;
import it.polimi.ingsw.gamelogic.player.Pawn;
import it.polimi.ingsw.gamelogic.player.Player;

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

    /**
     * TODO: JavaDoc
     * @param player
     * @return
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
