package it.polimi.ingsw.client.cli.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExcommunicationTileLightTest {
    private ExcommunicationTileLight excommunicationTileLight;

    @BeforeEach
    void setUp() {
        excommunicationTileLight = new ExcommunicationTileLight("1.2", "4C, 3S");
    }

    @Test
    void testEqualsTrue() {
        ExcommunicationTileLight excommunicationTileLightToConfront = new ExcommunicationTileLight("1.2",
                "4C, 3S");
        ExcommunicationTileLight excommunicationTileLightToConfront2 = excommunicationTileLight;
        assertTrue(excommunicationTileLightToConfront.equals(excommunicationTileLight));
        assertTrue(excommunicationTileLight.equals(excommunicationTileLightToConfront2));
    }

    @Test
    void testEqualsFalse() {
        ExcommunicationTileLight excommunicationTileLightToConfront = new ExcommunicationTileLight("1.5",
                "3Fp, 3Mp");
        ExcommunicationTileLight excommunicationTileLightToConfront2 = new ExcommunicationTileLight("1.2",
                "3Mp");
        assertFalse(excommunicationTileLightToConfront.equals(excommunicationTileLight));
        assertFalse(excommunicationTileLightToConfront2.equals(excommunicationTileLight));
    }

    @Test
    void testEqualsDifferent() {
        String obj = "";
        assertFalse(excommunicationTileLight.equals(obj));
        assertFalse(excommunicationTileLight.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        ExcommunicationTileLight excommunicationTileLightToConfront = new ExcommunicationTileLight("1.2",
                "4C, 3S");
        assertEquals(excommunicationTileLightToConfront.hashCode(), excommunicationTileLight.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        ExcommunicationTileLight excommunicationTileLightToConfront = new ExcommunicationTileLight("1.7",
                "4C, 3S");
        assertNotEquals(excommunicationTileLightToConfront.hashCode(), excommunicationTileLight.hashCode());
    }

    @Test
    void getName() {
        String name = "2.1";
        excommunicationTileLight.setName(name);
        assertEquals(name, excommunicationTileLight.getName());
    }

    @Test
    void getEffectDescription() {
        String effectDescription = "3Se, 1Fp";
        excommunicationTileLight.setEffectDescription(effectDescription);
        assertEquals(effectDescription, excommunicationTileLight.getEffectDescription());
    }
}