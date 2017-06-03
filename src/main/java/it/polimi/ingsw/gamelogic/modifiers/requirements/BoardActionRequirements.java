package it.polimi.ingsw.gamelogic.modifiers.requirements;

import it.polimi.ingsw.gamelogic.enums.ActionType;
import it.polimi.ingsw.gamelogic.player.Player;

/**
 * Class that describes the requirements of a BoardAction
 */
public class BoardActionRequirements implements Requirements {
    private SpaceActionRequirements spaceActionRequirements;
    private int malusValue;

    public BoardActionRequirements(SpaceActionRequirements spaceActionRequirements) {
        this.spaceActionRequirements = spaceActionRequirements;
        malusValue = 0;
    }

    public BoardActionRequirements(SpaceActionRequirements spaceActionRequirements, int malusValue) {
        this.spaceActionRequirements = spaceActionRequirements;
        this.malusValue = malusValue;
    }

    @Override
    public boolean hasRequirements(Player player) {
        /*
        TODO
         */
        return false;
    }

    /**
     * TODO: JavaDoc
     * @return
     */
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
}
