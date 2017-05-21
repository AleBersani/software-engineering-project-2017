package it.polimi.ingsw.gamelogic.rewards;

import it.polimi.ingsw.gamelogic.basics.Goods;

public class DoubleRewards extends RewardsModifier {
    public DoubleRewards(Rewards rewards) {
        super(rewards);
    }

    @Override
    public Goods calculateFinalRewards() {
        Goods doubleRewards = super.calculateFinalRewards();
        doubleRewards.addAll(doubleRewards);
        return doubleRewards;
    }
}
