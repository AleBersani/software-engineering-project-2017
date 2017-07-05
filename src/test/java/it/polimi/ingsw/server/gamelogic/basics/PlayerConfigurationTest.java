package it.polimi.ingsw.server.gamelogic.basics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PlayerConfigurationTest {
    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetNumberOfLeaders() {
        int numberOfLeadersToSet = 4;
        PlayerConfiguration.setNumberOfLeaders(numberOfLeadersToSet);
        assertEquals(numberOfLeadersToSet, PlayerConfiguration.getNumberOfLeaders());
    }

    @Test
    void testGetMaxNumberOfDevCardsForCategory() {
        int maxNumberOfDevCardsToSet = 6;
        PlayerConfiguration.setMaxNumberOfDevCardsForCategory(maxNumberOfDevCardsToSet);
        assertEquals(maxNumberOfDevCardsToSet, PlayerConfiguration.getMaxNumberOfDevCardsForCategory());
    }

    @Test
    void testGetStartingGoods() {
        List<Goods> startingGoodsToSet = new ArrayList<>();
        startingGoodsToSet.add(new Goods(new Resources(1,2,3,4)));
        PlayerConfiguration.setStartingGoods(startingGoodsToSet);
        assertEquals(startingGoodsToSet, PlayerConfiguration.getStartingGoods());
    }

    @Test
    void testGetNumberOfPawns() {
        int numberOfPawnToSet = 4;
        PlayerConfiguration.setNumberOfPawns(numberOfPawnToSet);
        assertEquals(numberOfPawnToSet, PlayerConfiguration.getNumberOfPawns());
    }

    @Test
    void testGetRequiredPointsForTerritories() {
        Map<Integer, Points> requiredPointsForTerritoriesToSet = new HashMap<>();
        requiredPointsForTerritoriesToSet.put(1, new Points(1,2,3));
        PlayerConfiguration.setRequiredPointsForTerritories(requiredPointsForTerritoriesToSet);
        assertEquals(requiredPointsForTerritoriesToSet, PlayerConfiguration.getRequiredPointsForTerritories());
    }
}