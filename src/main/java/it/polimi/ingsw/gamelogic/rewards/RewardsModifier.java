package it.polimi.ingsw.gamelogic.rewards;

import it.polimi.ingsw.gamelogic.basics.Goods;

public abstract class RewardsModifier implements Rewards {
    private Rewards rewards;

    public RewardsModifier(Rewards rewards) {
        this.rewards = rewards;
    }

    /**
     * Delegation
     * @return Goods of concrete class
     * @see ActualRewards
     */
    @Override
    public Goods calculateFinalRewards() {
        return rewards.calculateFinalRewards();
    }
}
