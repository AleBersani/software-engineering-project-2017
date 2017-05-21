package it.polimi.ingsw.gamelogic.rewards;

import it.polimi.ingsw.gamelogic.basics.Goods;

public class ActualRewards implements Rewards {
    private Goods rewards;

    public ActualRewards(Goods rewards) {
        this.rewards = rewards;
    }

    @Override
    public Goods calculateFinalRewards() {
        return getRewards();
    }

    public Goods getRewards() {
        return rewards;
    }

    public void setRewards(Goods rewards) {
        this.rewards = rewards;
    }
}
