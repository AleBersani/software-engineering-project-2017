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
    void TestEqualsTrue() {
        BasicEndGameRewards basicEndGameRewardsToConfront = new BasicEndGameRewards(new Points(5,0,0),
                new Points(10,0,0), new Points(15,0,0));
        assertTrue(basicEndGameRewards.equals(basicEndGameRewardsToConfront));
    }

    @Test
    void TestEqualsFalse() {
        BasicEndGameRewards basicEndGameRewardsToConfront = new BasicEndGameRewards(new Points(0,0,0),
                new Points(0,0,0), new Points(0,0,0));
        assertFalse(basicEndGameRewards.equals(basicEndGameRewardsToConfront));
    }

    @Test
    void TestEqualsDifferent() {
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
        LessVictoryForResources modifier = new LessVictoryForResources();
        modifier.setResources(new Resources(1,2,3,4));
        modifier.modifyEndGameRewards(basicEndGameRewards);

        assertEquals(new Points(20, 0, 0), basicEndGameRewards.calculateFinalEndGameRewards());
    }
}