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

    public abstract BoardActionRequirements modifyRequirements(BoardActionRequirements boardActionRequirements);

    public abstract LeaderRequirements modifyRequirements(LeaderRequirements leaderRequirements);

    public abstract TowerActionRequirements modifyRequirements(TowerActionRequirements towerActionRequirements);
}
