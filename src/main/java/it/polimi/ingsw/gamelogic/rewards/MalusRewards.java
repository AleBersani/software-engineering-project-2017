package it.polimi.ingsw.gamelogic.rewards;

import it.polimi.ingsw.gamelogic.basics.Goods;

public class MalusRewards extends RewardsModifier {
    private Goods malus;

    public MalusRewards(Rewards rewards, Goods malus) {
        super(rewards);
        this.malus = malus;
    }

    @Override
    public Goods calculateFinalRewards() {
        Goods baseRewards = super.calculateFinalRewards();
        baseRewards.subtractAll(malus);
        return baseRewards;
    }
}
