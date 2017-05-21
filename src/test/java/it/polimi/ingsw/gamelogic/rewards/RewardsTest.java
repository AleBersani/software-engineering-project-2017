package it.polimi.ingsw.gamelogic.rewards;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.basics.Points;
import it.polimi.ingsw.gamelogic.basics.Resources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RewardsTest {
    private Rewards rewards;

    @BeforeEach
    void setUp() {
        Goods baseGoods = new Goods(new Points(5,5,5));
        Goods bonusGoods = new Goods(new Resources(1,2,3,4));
        Goods malusGoods = new Goods(new Points(1,1,1));
        rewards = new BonusRewards(new MalusRewards(new ActualRewards(baseGoods), malusGoods), bonusGoods);
    }

    @Test
    void testCalculateFinalRewardsWithBonusAndMalus() {
        Goods result = rewards.calculateFinalRewards();
        Goods expected = new Goods(new Resources(1,2,3,4),
                new Points(4,4,4));
        assertEquals(expected, result);
    }

    @Test
    void testDoubleRewardsOnAlreadyDecoratedRewards() {
        Rewards doubledRewards = new DoubleRewards(rewards);
        Goods expected = new Goods(new Resources(2,4,6,8),
                new Points(8,8,8));
        assertEquals(expected, doubledRewards.calculateFinalRewards());
    }

    @Test
    void testBonusChurchReward() {
        rewards = new BonusRewards(new ActualRewards(new Goods(new Points(1,2,3))),
                new Goods(new Points(3,2,1)));
        Goods actual = new Goods(new Points(4,4,4));
        assertEquals(actual, rewards.calculateFinalRewards());
    }

    @Test
    void testNoTowerBonusRewards() {
        rewards = new NoTowerBonusRewards(new ActualRewards(new Goods(new Points(1,1,1))));
        Goods actual = new Goods();
        assertEquals(actual, rewards.calculateFinalRewards());
    }
}