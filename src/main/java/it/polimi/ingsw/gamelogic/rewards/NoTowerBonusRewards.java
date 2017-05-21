package it.polimi.ingsw.gamelogic.rewards;

import it.polimi.ingsw.gamelogic.basics.Goods;

public class NoTowerBonusRewards extends RewardsModifier {
    public NoTowerBonusRewards(Rewards rewards) {
        super(rewards);
    }

    @Override
    public Goods calculateFinalRewards() {
        return new Goods();
    }
}
