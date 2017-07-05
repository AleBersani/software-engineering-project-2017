package it.polimi.ingsw.server.gamelogic.basics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameConfigurationTest {
    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetMaxNumberOfPlayer() {
        int maxNumberOfPlayerToSet = 2;
        GameConfiguration.setMaxNumberOfPlayer(maxNumberOfPlayerToSet);
        assertEquals(maxNumberOfPlayerToSet, GameConfiguration.getMaxNumberOfPlayer());
    }

    @Test
    void testGetStartingGameTimeout() {
        int startingGameTimeoutToSet = 3;
        GameConfiguration.setStartingGameTimeout(startingGameTimeoutToSet);
        assertEquals(startingGameTimeoutToSet, GameConfiguration.getStartingGameTimeout());
    }

    @Test
    void testGetMoveTimeout() {
        int moveTimeoutToSet = 4;
        GameConfiguration.setMoveTimeout(moveTimeoutToSet);
        assertEquals(moveTimeoutToSet, GameConfiguration.getMoveTimeout());
    }

    @Test
    void testGetNumberOfPeriods() {
        int numberOfPeriodsToSet = 5;
        GameConfiguration.setNumberOfPeriods(numberOfPeriodsToSet);
        assertEquals(numberOfPeriodsToSet, GameConfiguration.getNumberOfPeriods());
    }
}