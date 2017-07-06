package it.polimi.ingsw.server.gamelogic.modifiers.endgamerewards.modifiers;

import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.modifiers.endgamerewards.BasicEndGameRewards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LessVictoryBasedOnMilitaryTest {
    private BasicEndGameRewards basicEndGameRewards;

    @BeforeEach
    void setUp() {
        basicEndGameRewards = new BasicEndGameRewards(new Points(5,0,0),
                new Points(10,0,0), new Points(15,0,0));
    }

    @Test
    void testEqualsTrue1() {
        LessVictoryBasedOnMilitary lessVictoryBasedOnMilitary = new LessVictoryBasedOnMilitary();
        lessVictoryBasedOnMilitary.setPlayerMilitary(new Points(1,2,3));
        LessVictoryBasedOnMilitary lessVictoryBasedOnMilitaryToConfront = lessVictoryBasedOnMilitary;
        assertTrue(lessVictoryBasedOnMilitary.equals(lessVictoryBasedOnMilitaryToConfront));
    }

    @Test
    void testEqualsTrue2() {
        LessVictoryBasedOnMilitary lessVictoryBasedOnMilitary = new LessVictoryBasedOnMilitary();
        lessVictoryBasedOnMilitary.setPlayerMilitary(new Points(1,2,3));
        LessVictoryBasedOnMilitary lessVictoryBasedOnMilitaryToConfront = new LessVictoryBasedOnMilitary();
        lessVictoryBasedOnMilitaryToConfront.setPlayerMilitary(new Points(1,2,3));
        assertTrue(lessVictoryBasedOnMilitary.equals(lessVictoryBasedOnMilitaryToConfront));
    }

    @Test
    void testEqualsFalse() {
        LessVictoryBasedOnMilitary lessVictoryBasedOnMilitary = new LessVictoryBasedOnMilitary();
        lessVictoryBasedOnMilitary.setPlayerMilitary(new Points(1,2,3));
        LessVictoryBasedOnMilitary lessVictoryBasedOnMilitaryToConfront = new LessVictoryBasedOnMilitary();
        lessVictoryBasedOnMilitaryToConfront.setPlayerMilitary(new Points(2,2,3));
        assertFalse(lessVictoryBasedOnMilitary.equals(lessVictoryBasedOnMilitaryToConfront));
    }

    @Test
    void testEqualsDifferent() {
        LessVictoryBasedOnMilitary lessVictoryBasedOnMilitary = new LessVictoryBasedOnMilitary();
        lessVictoryBasedOnMilitary.setPlayerMilitary(new Points(1,2,3));
        String different = "";
        assertFalse(lessVictoryBasedOnMilitary.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        LessVictoryBasedOnMilitary lessVictoryBasedOnMilitary = new LessVictoryBasedOnMilitary();
        lessVictoryBasedOnMilitary.setPlayerMilitary(new Points(1,2,3));
        assertFalse(lessVictoryBasedOnMilitary.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        LessVictoryBasedOnMilitary lessVictoryBasedOnMilitary = new LessVictoryBasedOnMilitary();
        lessVictoryBasedOnMilitary.setPlayerMilitary(new Points(1,2,3));
        LessVictoryBasedOnMilitary lessVictoryBasedOnMilitaryToConfront = new LessVictoryBasedOnMilitary();
        lessVictoryBasedOnMilitaryToConfront.setPlayerMilitary(new Points(1,2,3));
        assertEquals(lessVictoryBasedOnMilitary.hashCode(), lessVictoryBasedOnMilitaryToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        LessVictoryBasedOnMilitary lessVictoryBasedOnMilitary = new LessVictoryBasedOnMilitary();
        lessVictoryBasedOnMilitary.setPlayerMilitary(new Points(1,2,3));
        LessVictoryBasedOnMilitary lessVictoryBasedOnMilitaryToConfront = new LessVictoryBasedOnMilitary();
        lessVictoryBasedOnMilitaryToConfront.setPlayerMilitary(new Points(2,2,3));
        assertNotEquals(lessVictoryBasedOnMilitary.hashCode(), lessVictoryBasedOnMilitaryToConfront.hashCode());
    }

    @Test
    void testModifyEndGameRewards() {
        LessVictoryBasedOnMilitary modifier = new LessVictoryBasedOnMilitary();
        modifier.setPlayerMilitary(new Points(10,10,10));
        modifier.modifyEndGameRewards(basicEndGameRewards);

        assertEquals(new Points(20,0,0), basicEndGameRewards.calculateFinalEndGameRewards());
    }
}