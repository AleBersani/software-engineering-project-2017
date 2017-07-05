package it.polimi.ingsw.server.gamelogic.basics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EndGameRewardsTest {
    private EndGameRewards endGameRewards;

    @BeforeEach
    void setUp() {
        Map<String, List<GoodsForEndGamePossession>> rewardsForPossessionToSet = new HashMap<>();
        List<GoodsForEndGamePossession> goodsForEndGamePossessionsToAdd = new ArrayList<>();
        goodsForEndGamePossessionsToAdd.add(new GoodsForEndGamePossession(1, new Goods(
                new Points(1,2,3))));
        rewardsForPossessionToSet.put("", goodsForEndGamePossessionsToAdd);
        endGameRewards = new EndGameRewards(rewardsForPossessionToSet);
    }

    @Test
    void testEqualsTrue() {
        EndGameRewards endGameRewardsToConfront;
        Map<String, List<GoodsForEndGamePossession>> rewardsForPossessionToSetToConfront = new HashMap<>();
        List<GoodsForEndGamePossession> goodsForEndGamePossessionsToAddToConfront = new ArrayList<>();
        goodsForEndGamePossessionsToAddToConfront.add(new GoodsForEndGamePossession(1, new Goods(
                new Points(1,2,3))));
        rewardsForPossessionToSetToConfront.put("", goodsForEndGamePossessionsToAddToConfront);
        endGameRewardsToConfront = new EndGameRewards(rewardsForPossessionToSetToConfront);
        assertTrue(endGameRewardsToConfront.equals(endGameRewards));
    }

    @Test
    void testEqualsFalse() {
        EndGameRewards endGameRewardsToConfront;
        Map<String, List<GoodsForEndGamePossession>> rewardsForPossessionToSetToConfront = new HashMap<>();
        List<GoodsForEndGamePossession> goodsForEndGamePossessionsToAddToConfront = new ArrayList<>();
        goodsForEndGamePossessionsToAddToConfront.add(new GoodsForEndGamePossession(2, new Goods(
                new Points(1,1,1))));
        rewardsForPossessionToSetToConfront.put("", goodsForEndGamePossessionsToAddToConfront);
        endGameRewardsToConfront = new EndGameRewards(rewardsForPossessionToSetToConfront);
        assertFalse(endGameRewardsToConfront.equals(endGameRewards));
    }

    @Test
    void testEqualsDifferent() {
        String different = "";
        assertFalse(different.equals(endGameRewards));
    }

    @Test
    void testEqualsNull() {
        assertFalse(endGameRewards.equals(null));
    }

    @Test
    void testEqualsObject() {
        EndGameRewards newEndGamRewards = endGameRewards;
        assertTrue(newEndGamRewards.equals(endGameRewards));
    }

    @Test
    void testHashCodeTrue() {
        EndGameRewards endGameRewardsToConfront;
        Map<String, List<GoodsForEndGamePossession>> rewardsForPossessionToSetToConfront = new HashMap<>();
        List<GoodsForEndGamePossession> goodsForEndGamePossessionsToAddToConfront = new ArrayList<>();
        goodsForEndGamePossessionsToAddToConfront.add(new GoodsForEndGamePossession(1, new Goods(
                new Points(1,2,3))));
        rewardsForPossessionToSetToConfront.put("", goodsForEndGamePossessionsToAddToConfront);
        endGameRewardsToConfront = new EndGameRewards(rewardsForPossessionToSetToConfront);
        assertEquals(endGameRewardsToConfront.hashCode(),endGameRewards.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        EndGameRewards endGameRewardsToConfront;
        Map<String, List<GoodsForEndGamePossession>> rewardsForPossessionToSetToConfront = new HashMap<>();
        List<GoodsForEndGamePossession> goodsForEndGamePossessionsToAddToConfront = new ArrayList<>();
        goodsForEndGamePossessionsToAddToConfront.add(new GoodsForEndGamePossession(2, new Goods(
                new Points(1,1,1))));
        rewardsForPossessionToSetToConfront.put("", goodsForEndGamePossessionsToAddToConfront);
        endGameRewardsToConfront = new EndGameRewards(rewardsForPossessionToSetToConfront);
        assertNotEquals(endGameRewardsToConfront.hashCode(),endGameRewards.hashCode());
    }

    @Test
    void testGetRewardsForPossessions() {
        Map<String, List<GoodsForEndGamePossession>> rewardsForPossessionToSet = new HashMap<>();
        List<GoodsForEndGamePossession> goodsForEndGamePossessionsToAddToConfront = new ArrayList<>();
        goodsForEndGamePossessionsToAddToConfront.add(new GoodsForEndGamePossession(1, new Goods(
                new Points(1,2,3))));
        rewardsForPossessionToSet.put("", goodsForEndGamePossessionsToAddToConfront);
        endGameRewards.setRewardsForPossessions(rewardsForPossessionToSet);
        assertEquals(rewardsForPossessionToSet, endGameRewards.getRewardsForPossessions());
    }
}