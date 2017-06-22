package it.polimi.ingsw.server.gamelogic.modifiers.endgamerewards;

import it.polimi.ingsw.server.gamelogic.basics.Points;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasicEndGameRewardsTest {
    private BasicEndGameRewards basicEndGameRewards;

    @BeforeEach
    void setUp() {
        basicEndGameRewards = new BasicEndGameRewards(new Points(5,0,0),
                new Points(10,0,0), new Points(15,0,0));
    }

    @Test
    void testCalculateFinalEndGameRewards() {
        assertEquals(new Points(30, 0, 0), basicEndGameRewards.calculateFinalEndGameRewards());
    }
}