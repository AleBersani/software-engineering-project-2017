package it.polimi.ingsw.server.gamelogic.modifiers.endgamerewards.modifiers;

import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.modifiers.endgamerewards.BasicEndGameRewards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LessVictoryForResourcesTest {
    private BasicEndGameRewards basicEndGameRewards;

    @BeforeEach
    void setUp() {
        basicEndGameRewards = new BasicEndGameRewards(new Points(5,0,0),
                new Points(10,0,0), new Points(15,0,0));
    }

    @Test
    void TestEqualsTrue1() {
        LessVictoryForResources lessVictoryForResources = new LessVictoryForResources();
        lessVictoryForResources.setResources(new Resources(1,2,3,4));
        LessVictoryForResources lessVictoryForResourcesToConfront = new LessVictoryForResources();
        lessVictoryForResourcesToConfront.setResources(new Resources(1,2,3,4));
        assertTrue(lessVictoryForResources.equals(lessVictoryForResourcesToConfront));
    }

    @Test
    void TestEqualsTrue2() {
        LessVictoryForResources lessVictoryForResources = new LessVictoryForResources();
        lessVictoryForResources.setResources(new Resources(1,2,3,4));
        LessVictoryForResources lessVictoryForResourcesToConfront = lessVictoryForResources;
        assertTrue(lessVictoryForResources.equals(lessVictoryForResourcesToConfront));
    }

    @Test
    void TestEqualsFalse() {
        LessVictoryForResources lessVictoryForResources = new LessVictoryForResources();
        lessVictoryForResources.setResources(new Resources(1,2,3,4));
        LessVictoryForResources lessVictoryForResourcesToConfront = new LessVictoryForResources();
        lessVictoryForResources.setResources(new Resources(5,6,7,8));
        assertFalse(lessVictoryForResources.equals(lessVictoryForResourcesToConfront));
    }

    @Test
    void TestEqualsDifferent1() {
        String different = "";
        LessVictoryForResources lessVictoryForResources = new LessVictoryForResources();
        lessVictoryForResources.setResources(new Resources(1,2,3,4));
        assertFalse(lessVictoryForResources.equals(different));
    }

    @Test
    void TestEqualsDifferent2() {
        LessVictoryForResources lessVictoryForResources = new LessVictoryForResources();
        lessVictoryForResources.setResources(new Resources(1,2,3,4));
        assertFalse(lessVictoryForResources.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        LessVictoryForResources lessVictoryForResources = new LessVictoryForResources();
        lessVictoryForResources.setResources(new Resources(1,2,3,4));
        LessVictoryForResources lessVictoryForResourcesToConfront = new LessVictoryForResources();
        lessVictoryForResourcesToConfront.setResources(new Resources(1,2,3,4));
        assertEquals(lessVictoryForResources.hashCode(), lessVictoryForResourcesToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        LessVictoryForResources lessVictoryForResources = new LessVictoryForResources();
        lessVictoryForResources.setResources(new Resources(1,2,3,4));
        LessVictoryForResources lessVictoryForResourcesToConfront = new LessVictoryForResources();
        assertNotEquals(lessVictoryForResources.hashCode(), lessVictoryForResourcesToConfront.hashCode());
    }

    @Test
    void testModifyEndGameRewards() {
        LessVictoryForResources modifier = new LessVictoryForResources();
        modifier.setResources(new Resources(1,2,3,4));
        modifier.modifyEndGameRewards(basicEndGameRewards);

        assertEquals(new Points(20, 0, 0), basicEndGameRewards.calculateFinalEndGameRewards());
    }
}