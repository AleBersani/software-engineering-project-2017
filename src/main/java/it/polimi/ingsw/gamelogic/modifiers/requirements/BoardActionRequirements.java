package it.polimi.ingsw.gamelogic.modifiers.requirements;

import it.polimi.ingsw.gamelogic.enums.ActionType;
import it.polimi.ingsw.gamelogic.player.Player;

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