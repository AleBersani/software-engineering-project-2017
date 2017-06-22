package it.polimi.ingsw.server.gamelogic.modifiers.requirements;

import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.shared.model.ActionType;

import java.util.Objects;

/**
 * Class that describes the requirements of a BoardAction
 */
public class BoardActionRequirements implements Requirements {
    private SpaceActionRequirements spaceActionRequirements;
    private int malusValue;

    private boolean canPlace;

    public BoardActionRequirements(SpaceActionRequirements spaceActionRequirements) {
        this.spaceActionRequirements = spaceActionRequirements;
        malusValue = 0;
        canPlace = true;
    }

    public BoardActionRequirements(SpaceActionRequirements spaceActionRequirements, int malusValue) {
        this.spaceActionRequirements = spaceActionRequirements;
        this.malusValue = malusValue;
        canPlace = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BoardActionRequirements that = (BoardActionRequirements) o;
        return getMalusValue() == that.getMalusValue() &&
                isCanPlace() == that.isCanPlace() &&
                Objects.equals(getSpaceActionRequirements(), that.getSpaceActionRequirements());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSpaceActionRequirements(), getMalusValue(), isCanPlace());
    }

    /**
     * Checks if the Player fulfills the requirements to perform an Action
     * @param player player whose turn it is
     * @return true if the player fulfills the requirements
     */
    @Override
    public boolean hasRequirements(Player player) {
        if (!spaceActionRequirements.hasRequirements(player))
            return false;

        if (spaceActionRequirements.getFinalActionValue() - malusValue < spaceActionRequirements.getRequiredValue())
            return false;

        if (!canPlace)
            return false;

        return true;
    }

    public ActionType getActionType() {
        return spaceActionRequirements.getActionType();
    }

    public SpaceActionRequirements getSpaceActionRequirements() {
        return spaceActionRequirements;
    }

    public void setSpaceActionRequirements(SpaceActionRequirements spaceActionRequirements) {
        this.spaceActionRequirements = spaceActionRequirements;
    }

    public int getMalusValue() {
        return malusValue;
    }

    public void setMalusValue(int malusValue) {
        this.malusValue = malusValue;
    }

    public boolean isCanPlace() {
        return canPlace;
    }

    public void setCanPlace(boolean canPlace) {
        this.canPlace = canPlace;
    }
}