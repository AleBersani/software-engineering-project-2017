package it.polimi.ingsw.gamelogic.rewards;

import it.polimi.ingsw.gamelogic.basics.Goods;

public class BunusChurchReward extends RewardsModifier {
    private Goods bonus;

    public BunusChurchReward(Rewards rewards, Goods bonus) {
        super(rewards);
        this.bonus = bonus;
    }

    @Override
    public Goods calculateFinalRewards() {
        bonus.addAll(super.calculateFinalRewards());
        return bonus;
    }
}
