package it.polimi.ingsw.server.gamelogic.modifiers.endgamerewards.modifiers;

import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.modifiers.endgamerewards.BasicEndGameRewards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LessVictoryBasedOnVictoryTest {
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
    void testModifyEndGameRewards() {
        LessVictoryBasedOnVictory modifier = new LessVictoryBasedOnVictory();
        modifier.setPlayerVictory(new Points(50, 10,10));
        modifier.modifyEndGameRewards(basicEndGameRewards);

        assertEquals(new Points(20, 0, 0), basicEndGameRewards.calculateFinalEndGameRewards());
    }

    @Test
    void testModifyEndGameRewardsWithOddValue() {
        LessVictoryBasedOnVictory modifier = new LessVictoryBasedOnVictory();
        modifier.setPlayerVictory(new Points(51, 10,10));
        modifier.modifyEndGameRewards(basicEndGameRewards);

        assertEquals(new Points(20, 0, 0), basicEndGameRewards.calculateFinalEndGameRewards());
    }
}