package it.polimi.ingsw.server.gamelogic.basics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardConfigurationTest {
    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetNumberOfTowers() {
        BoardConfiguration.setNumberOfTowers(3);
        assertEquals(3, BoardConfiguration.getNumberOfTowers());
    }

    @Test
    void testGetNumberOfSlotsForTowers() {
        BoardConfiguration.setNumberOfSlotsForTowers(4);
        assertEquals(4, BoardConfiguration.getNumberOfSlotsForTowers());
    }

    @Test
    void testGetNumberOfDices() {
        BoardConfiguration.setNumberOfDices(2);
        assertEquals(2, BoardConfiguration.getNumberOfDices());
    }

    @Test
    void testGetMaxMilitaryPoints() {
        BoardConfiguration.setMaxMilitaryPoints(12);
        assertEquals(12, BoardConfiguration.getMaxMilitaryPoints());
    }

    @Test
    void testGetMaxFaithPoints() {
        BoardConfiguration.setMaxFaithPoints(666);
        assertEquals(666, BoardConfiguration.getMaxFaithPoints());
    }

}