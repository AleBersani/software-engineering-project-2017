package it.polimi.ingsw.client.cli.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BonusTileDescriptionLightTest {
    private BonusTileDescriptionLight bonusTileDescriptionLight;

    @BeforeEach
    void setUp() {
        bonusTileDescriptionLight = new BonusTileDescriptionLight("B_TILE_2", "3C, 2Se");
    }

    @Test
    void testEqualsTrue() {
        BonusTileDescriptionLight bonusTileDescriptionLightToConfront = new BonusTileDescriptionLight(
                "B_TILE_2", "3C, 2Se");
        assertTrue(bonusTileDescriptionLightToConfront.equals(bonusTileDescriptionLight));
    }

    @Test
    void testEqualsFalse() {
        BonusTileDescriptionLight bonusTileDescriptionLightToConfront =  new BonusTileDescriptionLight(
                "B_TILE_1", "3C, 2Se");
        assertFalse(bonusTileDescriptionLightToConfront.equals(bonusTileDescriptionLight));
    }

    @Test
    void testEqualsDifferent() {
        String obj = "";
        assertFalse(bonusTileDescriptionLight.equals(obj));
    }

    @Test
    void testHashCodeTrue() {
        BonusTileDescriptionLight bonusTileDescriptionLightToConfront = new BonusTileDescriptionLight(
                "B_TILE_2", "3C, 2Se");
        assertTrue(bonusTileDescriptionLightToConfront.hashCode() == bonusTileDescriptionLight.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        BonusTileDescriptionLight bonusTileDescriptionLightToConfront =  new BonusTileDescriptionLight(
                "B_TILE_1", "3C, 2Se");
        assertFalse(bonusTileDescriptionLightToConfront.hashCode() == bonusTileDescriptionLight.hashCode());
    }

    @Test
    void testGetBonusTileIdentifier() {
        String bonusIdentifier = "B_TILE_1";
        bonusTileDescriptionLight.setBonusTileIdentifier(bonusIdentifier);
        assertTrue(bonusIdentifier.equals(bonusTileDescriptionLight.getBonusTileIdentifier()));
    }

    @Test
    void testGetDescription() {
        String description = "4C, 5S";
        bonusTileDescriptionLight.setDescription(description);
        assertEquals(description, bonusTileDescriptionLight.getDescription());
    }

}