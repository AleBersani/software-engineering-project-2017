package it.polimi.ingsw.gamelogic.decorators.rewards;

import it.polimi.ingsw.gamelogic.decorators.AvailableActions;

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
