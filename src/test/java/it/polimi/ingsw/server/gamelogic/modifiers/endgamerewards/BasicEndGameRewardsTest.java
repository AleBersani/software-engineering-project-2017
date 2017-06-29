package it.polimi.ingsw.server.gamelogic.modifiers.endgamerewards;

import it.polimi.ingsw.server.gamelogic.basics.Points;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BasicEndGameRewardsTest {
    private BasicEndGameRewards basicEndGameRewards;

    @BeforeEach
    void setUp() {
        basicEndGameRewards = new BasicEndGameRewards(new Points(5,0,0),
                new Points(10,0,0), new Points(15,0,0));
    }

    @Test
    void testEqualsTrue() {
        BasicEndGameRewards basicEndGameRewardsToConfront = new BasicEndGameRewards(new Points(5,0,0),
                new Points(10,0,0), new Points(15,0,0));
        assertTrue(basicEndGameRewards.equals(basicEndGameRewardsToConfront));
    }

    @Test
    void testEqualsFalse() {
        BasicEndGameRewards basicEndGameRewardsToConfront = new BasicEndGameRewards(new Points(0,0,0),
                new Points(0,0,0), new Points(0,0,0));
        assertFalse(basicEndGameRewards.equals(basicEndGameRewardsToConfront));
    }

    @Test
    void testEqualsDifferent() {
        String different = "";
        assertFalse(basicEndGameRewards.equals(different));
    }

    @Test
    void testHashCodeTrue() {
        BasicEndGameRewards basicEndGameRewardsToConfront = new BasicEndGameRewards(new Points(5,0,0),
                new Points(10,0,0), new Points(15,0,0));
        assertTrue(basicEndGameRewards.hashCode() == basicEndGameRewardsToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        BasicEndGameRewards basicEndGameRewardsToConfront = new BasicEndGameRewards(new Points(0,0,0),
                new Points(0,0,0), new Points(0,0,0));
        assertFalse(basicEndGameRewards.hashCode() == basicEndGameRewardsToConfront.hashCode());
    }

    @Test
    void testCalculateFinalEndGameRewards() {
        assertEquals(new Points(30, 0, 0), basicEndGameRewards.calculateFinalEndGameRewards());
    }
}