package it.polimi.ingsw.server.gamelogic.cards.excommunicationtiles;

import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExcommunicationTileTest {
    private ExcommunicationTile excommunicationTile;

    @BeforeEach
    void setUp() {
        excommunicationTile = new ExcommunicationTile("1.2", PeriodNumber.FIRST);
    }

    @Test
    void testEqualsTrue1() {
        ExcommunicationTile excommunicationTileToConfront = new ExcommunicationTile("1.2",
                                                                                        PeriodNumber.FIRST);
        assertTrue(excommunicationTileToConfront.equals(excommunicationTile));
    }

    @Test
    void testEqualsTrue2() {
        ExcommunicationTile excommunicationTileToConfront = excommunicationTile;
        assertTrue(excommunicationTileToConfront.equals(excommunicationTile));
    }

    @Test
    void testEqualsFalse1() {
        ExcommunicationTile excommunicationTileToConfront = new ExcommunicationTile("1.4",
                PeriodNumber.FIRST);
        assertFalse(excommunicationTile.equals(excommunicationTileToConfront));
    }

    @Test
    void testEqualsFalse2() {
        ExcommunicationTile excommunicationTileToConfront = new ExcommunicationTile("1.2",
                PeriodNumber.SECOND);
        assertFalse(excommunicationTile.equals(excommunicationTileToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(excommunicationTile.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(excommunicationTile.equals(null));
    }

    @Test
    void testGetExcommunicationTileName() {
        String name = "1.3";
        excommunicationTile.setExcommunicationTileName(name);
        assertEquals(name, excommunicationTile.getExcommunicationTileName());
    }

    @Test
    void testGetPeriod() {
        PeriodNumber periodNumberToConfront = PeriodNumber.FIRST;
        excommunicationTile.setPeriod(periodNumberToConfront);
        assertEquals(periodNumberToConfront, excommunicationTile.getPeriod());
    }
}