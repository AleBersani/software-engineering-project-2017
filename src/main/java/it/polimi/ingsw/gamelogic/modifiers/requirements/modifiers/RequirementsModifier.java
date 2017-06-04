package it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.BoardActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.LeaderRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.TowerActionRequirements;

public abstract class RequirementsModifier {
    protected AvailableActions availableActions;

    public RequirementsModifier(AvailableActions availableActions) {
        this.availableActions = availableActions;
    }

    public BoardActionRequirements modifyRequirements(BoardActionRequirements boardActionRequirements) {
        return boardActionRequirements;
    }

    public TowerActionRequirements modifyRequirements(TowerActionRequirements towerActionRequirements) {
        return towerActionRequirements;
    }
}
