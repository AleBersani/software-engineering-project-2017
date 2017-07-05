package it.polimi.ingsw.server.gamelogic.cards.excommunicationtiles;

import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExcommunicationTileTest {
    private ExcommunicationTile excommunicationTile;

    @BeforeEach
    void setUp() {
        excommunicationTile = new ExcommunicationTile("1.2", PeriodNumber.FIRST);
    }

    @Test
    void equals() {
        ExcommunicationTile excommunicationTileToConfront = new ExcommunicationTile("1.2",
                                                                                        PeriodNumber.FIRST);
        assertTrue(excommunicationTileToConfront.equals(excommunicationTile));
    }

    @Test
    void getExcommunicationTileName() {
        String name = "1.3";
        excommunicationTile.setExcommunicationTileName(name);
        assertEquals(name, excommunicationTile.getExcommunicationTileName());
    }

    @Test
    void getPeriod() {
        PeriodNumber periodNumberToConfront = PeriodNumber.FIRST;
        excommunicationTile.setPeriod(periodNumberToConfront);
        assertEquals(periodNumberToConfront, excommunicationTile.getPeriod());
    }
}