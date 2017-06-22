package it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.BoardActionRequirements;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.TowerActionRequirements;

import java.util.Objects;

/**
 * Abstract class that is extended by the modifiers related to the Requirements
 */
public abstract class RequirementsModifier {
    protected AvailableActions availableActions;

    public RequirementsModifier(AvailableActions availableActions) {
        this.availableActions = availableActions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        RequirementsModifier that = (RequirementsModifier) o;
        return Objects.equals(availableActions, that.availableActions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(availableActions);
    }

    public BoardActionRequirements modifyRequirements(BoardActionRequirements boardActionRequirements) {
        return boardActionRequirements;
    }

    public TowerActionRequirements modifyRequirements(TowerActionRequirements towerActionRequirements) {
        return towerActionRequirements;
    }
}
