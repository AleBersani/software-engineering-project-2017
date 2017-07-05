package it.polimi.ingsw.server.gamelogic.modifiers.rewards;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.shared.model.ActionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicRewardsTest {
    private BasicRewards basicRewards;

    @BeforeEach
    void setUp() {
        basicRewards = new BasicRewards(ActionType.BLUE_TOWER, new Goods(new Points(1,1,1)),
                new Goods(new Points(1,1,1)));
    }

    @Test
    void testEqualsTrue() {
        BasicRewards basicRewardsToConfront = new BasicRewards(ActionType.BLUE_TOWER, new Goods(
                new Points(1,1,1)), new Goods(new Points(1,1,1)));
        assertTrue(basicRewards.equals(basicRewardsToConfront));
    }

    @Test
    void testEqualsFalse() {
        BasicRewards basicRewardsToConfront = new BasicRewards(ActionType.GREEN_TOWER, new Goods(
                new Points(0,0,0)), new Goods(new Points(1,1,1)));
        assertFalse(basicRewards.equals(basicRewardsToConfront));
    }

    @Test
    void testEqualsDifferent() {
        String different = "";
        assertFalse(basicRewards.equals(different));
    }

    @Test
    void testHashCodeTrue() {
        BasicRewards basicRewardsToConfront = new BasicRewards(ActionType.BLUE_TOWER, new Goods(
                new Points(1,1,1)), new Goods(new Points(1,1,1)));
        assertTrue(basicRewards.hashCode() == basicRewardsToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        BasicRewards basicRewardsToConfront = new BasicRewards(ActionType.GREEN_TOWER, new Goods(
                new Points(0,0,0)), new Goods(new Points(1,1,1)));
        assertFalse(basicRewards.hashCode() == basicRewardsToConfront.hashCode());
    }

    @Test
    void testCalculateFinalRewards() {
        Goods expectedGoods = new Goods(new Points(2,2,2));
        assertEquals(expectedGoods, basicRewards.calculateFinalRewards());
    }

    @Test
    void testGetActionType() {
        basicRewards.setActionType(ActionType.BLUE_TOWER);
        assertEquals(ActionType.BLUE_TOWER, basicRewards.getActionType());
    }

    @Test
    void testGetRewards() {
        basicRewards.setRewards(new Goods(new Points(1,2,3)));
        assertEquals(new Goods(new Points(1,2,3)), basicRewards.getRewards());
    }

    @Test
    void testGetAdditionalRewards() {
        basicRewards.setAdditionalRewards(new Goods(new Resources(1,2,3,4)));
        assertEquals(new Goods(new Resources(1,2,3,4)), basicRewards.getAdditionalRewards());
    }

    @Test
    void testGetRewardsCopy() {
        Goods rewardsCopyToSet = new Goods(new Points(1,2,3));
        basicRewards.setRewardsCopy(rewardsCopyToSet);
        assertEquals(rewardsCopyToSet, basicRewards.getRewardsCopy());
    }

    @Test
    void testGetBonusAndMalus() {
        basicRewards.setBonusAndMalus(new Goods(new Points(1,1,1)));
        assertEquals(new Goods(new Points(1,1,1)), basicRewards.getBonusAndMalus());
    }
}