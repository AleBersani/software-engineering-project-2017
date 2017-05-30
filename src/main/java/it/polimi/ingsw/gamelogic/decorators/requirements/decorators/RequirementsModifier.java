package it.polimi.ingsw.gamelogic.decorators.requirements.decorators;

import it.polimi.ingsw.gamelogic.decorators.AvailableActions;
import it.polimi.ingsw.gamelogic.decorators.AvailableIdentifiers;
import it.polimi.ingsw.gamelogic.decorators.requirements.BoardActionRequirements;
import it.polimi.ingsw.gamelogic.decorators.requirements.LeaderRequirements;
import it.polimi.ingsw.gamelogic.decorators.requirements.SpaceActionRequirements;
import it.polimi.ingsw.gamelogic.decorators.requirements.TowerActionRequirements;

public abstract class RequirementsModifier {
    protected AvailableActions availableActions;

    public RequirementsModifier(AvailableActions availableActions) {
        this.availableActions = availableActions;
    }

    public abstract void modifyRequirements(BoardActionRequirements boardActionRequirements);

    public abstract void modifyRequirements(LeaderRequirements leaderRequirements);

    public abstract void modifyRequirements(TowerActionRequirements towerActionRequirements);
}
