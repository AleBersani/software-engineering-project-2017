package it.polimi.ingsw.gamelogic.modifiers.rewards.modifiers;

import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.rewards.BasicRewards;

/**
 * TODO: JavaDoc
 */
public abstract class RewardsModifier {
    protected AvailableActions availableActions;

    public RewardsModifier(AvailableActions availableActions) {
        this.availableActions = availableActions;
    }

    public abstract void modifyRewards(BasicRewards basicRewards);
}
