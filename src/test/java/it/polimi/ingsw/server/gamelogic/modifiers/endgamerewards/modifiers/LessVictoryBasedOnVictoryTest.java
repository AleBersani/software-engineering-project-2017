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
    void testEqualsTrue1() {
        LessVictoryBasedOnVictory lessVictoryBasedOnVictory = new LessVictoryBasedOnVictory();
        lessVictoryBasedOnVictory.setPlayerVictory(new Points(1,2,3));
        LessVictoryBasedOnVictory lessVictoryBasedOnVictoryToConfront = new LessVictoryBasedOnVictory();
        lessVictoryBasedOnVictoryToConfront.setPlayerVictory(new Points(1,2,3));
        assertTrue(lessVictoryBasedOnVictory.equals(lessVictoryBasedOnVictoryToConfront));
    }

    @Test
    void testEqualsTrue2() {
        LessVictoryBasedOnVictory lessVictoryBasedOnVictory = new LessVictoryBasedOnVictory();
        lessVictoryBasedOnVictory.setPlayerVictory(new Points(1,2,3));
        LessVictoryBasedOnVictory lessVictoryBasedOnVictoryToConfront = lessVictoryBasedOnVictory;
        assertTrue(lessVictoryBasedOnVictory.equals(lessVictoryBasedOnVictoryToConfront));
    }

    @Test
    void testEqualsFalse() {
        LessVictoryBasedOnVictory lessVictoryBasedOnVictory = new LessVictoryBasedOnVictory();
        lessVictoryBasedOnVictory.setPlayerVictory(new Points(1,2,3));
        LessVictoryBasedOnVictory lessVictoryBasedOnVictoryToConfront = new LessVictoryBasedOnVictory();
        lessVictoryBasedOnVictoryToConfront.setPlayerVictory(new Points(4,5,6));
        assertFalse(lessVictoryBasedOnVictory.equals(lessVictoryBasedOnVictoryToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        LessVictoryBasedOnVictory lessVictoryBasedOnVictory = new LessVictoryBasedOnVictory();
        lessVictoryBasedOnVictory.setPlayerVictory(new Points(1,2,3));
        String different = "";
        assertFalse(lessVictoryBasedOnVictory.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        LessVictoryBasedOnVictory lessVictoryBasedOnVictory = new LessVictoryBasedOnVictory();
        lessVictoryBasedOnVictory.setPlayerVictory(new Points(1,2,3));
        assertFalse(lessVictoryBasedOnVictory.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        LessVictoryBasedOnVictory lessVictoryBasedOnVictory = new LessVictoryBasedOnVictory();
        lessVictoryBasedOnVictory.setPlayerVictory(new Points(1,2,3));
        LessVictoryBasedOnVictory lessVictoryBasedOnVictoryToConfront = new LessVictoryBasedOnVictory();
        lessVictoryBasedOnVictoryToConfront.setPlayerVictory(new Points(1,2,3));
        assertEquals(lessVictoryBasedOnVictory.hashCode(), lessVictoryBasedOnVictoryToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        LessVictoryBasedOnVictory lessVictoryBasedOnVictory = new LessVictoryBasedOnVictory();
        lessVictoryBasedOnVictory.setPlayerVictory(new Points(1,2,3));
        LessVictoryBasedOnVictory lessVictoryBasedOnVictoryToConfront = new LessVictoryBasedOnVictory();
        lessVictoryBasedOnVictoryToConfront.setPlayerVictory(new Points(3,5,3));
        assertNotEquals(lessVictoryBasedOnVictory.hashCode(), lessVictoryBasedOnVictoryToConfront.hashCode());
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