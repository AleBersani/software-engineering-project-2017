package it.polimi.ingsw.gamelogic.modifiers.endgamerewards.modifiers;

import it.polimi.ingsw.gamelogic.basics.Points;
import it.polimi.ingsw.gamelogic.basics.Resources;
import it.polimi.ingsw.gamelogic.modifiers.endgamerewards.BasicEndGameRewards;
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
    void testModifyEndGameRewards() {
        LessVictoryForResources modifier = new LessVictoryForResources(
                new Resources(1,2,3,4));
        modifier.modifyEndGameRewards(basicEndGameRewards);

        assertEquals(new Points(20, 0, 0), basicEndGameRewards.calculateFinalEndGameRewards());
    }
}