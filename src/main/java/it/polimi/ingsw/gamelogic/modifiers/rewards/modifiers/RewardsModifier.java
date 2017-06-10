package it.polimi.ingsw.gamelogic.modifiers.rewards.modifiers;

import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.rewards.BasicRewards;

import java.util.Objects;

/**
 * Abstract class that is extended by the modifiers related to the Rewards
 */
public abstract class RewardsModifier {
    protected AvailableActions availableActions;

    public RewardsModifier(AvailableActions availableActions) {
        this.availableActions = availableActions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        RewardsModifier that = (RewardsModifier) o;
        return Objects.equals(availableActions, that.availableActions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(availableActions);
    }

    public abstract void modifyRewards(BasicRewards basicRewards);
}
