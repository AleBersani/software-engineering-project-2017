package it.polimi.ingsw.gamelogic.decorators.requirements;

import it.polimi.ingsw.gamelogic.enums.BoardIdentifier;

public class BoardActionRequirements implements Requirements {
    SpaceActionRequirements spaceActionRequirements;

    public BoardActionRequirements(SpaceActionRequirements spaceActionRequirements) {
        this.spaceActionRequirements = spaceActionRequirements;
    }

    public BoardIdentifier getBoardIdentifier() {
        return spaceActionRequirements.getBoardIdentifier();
    }

    public SpaceActionRequirements getSpaceActionRequirements() {
        return spaceActionRequirements;
    }

    public void setSpaceActionRequirements(SpaceActionRequirements spaceActionRequirements) {
        this.spaceActionRequirements = spaceActionRequirements;
    }
}
