package it.polimi.ingsw.client.cli.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DevelopmentCardsLightTest {
    private DevelopmentCardsLight developmentCardsLight;

    @BeforeEach
    void setUp() {
        developmentCardsLight = new DevelopmentCardsLight("Bosco", "4C",
                "2S","5C");
    }

    @Test
    void testEqualsTrue() {
        DevelopmentCardsLight developmentCardsLightToConfront = new DevelopmentCardsLight(
                                        "Bosco", "4C",
                                        "2S","5C");
        DevelopmentCardsLight developmentCardsLightToConfront2 = developmentCardsLight;
        assertTrue(developmentCardsLight.equals(developmentCardsLightToConfront2));
        assertTrue(developmentCardsLight.equals(developmentCardsLightToConfront));
    }

    @Test
    void testEqualsFalse() {
        DevelopmentCardsLight developmentCardsLightToConfront = new DevelopmentCardsLight(
                "Miniera", "4S",
                "2Se","3C");
        assertFalse(developmentCardsLightToConfront.equals(developmentCardsLight));
    }

    @Test
    void testEqualsDifferent() {
        String obj = "";
        assertFalse(developmentCardsLight.equals(obj));
        assertFalse(developmentCardsLight.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        DevelopmentCardsLight developmentCardsLightToConfront = new DevelopmentCardsLight(
                "Bosco", "4C",
                "2S","5C");
        assertTrue(developmentCardsLightToConfront.hashCode() == developmentCardsLight.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        DevelopmentCardsLight developmentCardsLightToConfront = new DevelopmentCardsLight(
                "Bosco", "2C",
                "2S","5C");
        assertFalse(developmentCardsLightToConfront.hashCode() == developmentCardsLight.hashCode());
    }

    @Test
    void getName() {
        String name = "Zecca";
        developmentCardsLight.setName(name);
        assertEquals(name, developmentCardsLight.getName());
    }

    @Test
    void getInstantEffectDescription() {
        String instantEffect = "5Mp";
        developmentCardsLight.setInstantEffectDescription(instantEffect);
        assertEquals(instantEffect, developmentCardsLight.getInstantEffectDescription());
    }

    @Test
    void getPermanentEffectDescription() {
        String permanentEffect = "5Fp";
        developmentCardsLight.setPermanentEffectDescription(permanentEffect);
        assertEquals(permanentEffect, developmentCardsLight.getPermanentEffectDescription());
    }

    @Test
    void getCost() {
        String cost = "5Vp";
        developmentCardsLight.setCost(cost);
        assertEquals(cost, developmentCardsLight.getCost());
    }
}