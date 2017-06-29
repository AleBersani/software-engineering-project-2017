package it.polimi.ingsw.client.cli.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeaderCardLightTest {
    private LeaderCardLight leaderCardLight;

    @BeforeEach
    void setUp() {
        leaderCardLight = new LeaderCardLight("Lorenzo Il Magnifico", "4C", "4Fp");
    }

    @Test
    void testEqualsTrue() {
        LeaderCardLight leaderCardLightToConfront = new LeaderCardLight("Lorenzo Il Magnifico",
                "4C", "4Fp");
        assertTrue(leaderCardLightToConfront.equals(leaderCardLight));
    }

    @Test
    void testEqualsFalse() {
        LeaderCardLight leaderCardLightToConfront = new LeaderCardLight("Lorenzo Il Magnifico",
                "4C", "4Mp");
        assertFalse(leaderCardLightToConfront.equals(leaderCardLight));
    }

    @Test
    void testEqualsDifferent() {
        String obj = "";
        assertFalse(leaderCardLight.equals(obj));
    }

    @Test
    void testHashCodeTrue() {
        LeaderCardLight leaderCardLightToConfront = new LeaderCardLight("Lorenzo Il Magnifico",
                "4C", "4Fp");
        assertEquals(leaderCardLightToConfront.hashCode(), leaderCardLight.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        LeaderCardLight leaderCardLightToConfront = new LeaderCardLight("Lorenzo Il Magnifico",
                "4Se", "4Mp");
        assertNotEquals(leaderCardLightToConfront.hashCode(), leaderCardLight.hashCode());
    }

    @Test
    void getName() {
        String name = "Francesco Sforza";
        leaderCardLight.setName(name);
        assertEquals(name, leaderCardLight.getName());
    }

    @Test
    void getEffectDescription() {
        String effect = "4C, 2Cp";
        leaderCardLight.setEffectDescription(effect);
        assertEquals(effect, leaderCardLight.getEffectDescription());
    }

    @Test
    void getRequirements() {
        String requirements = "4C, 3Fp";
        leaderCardLight.setRequirements(requirements);
        assertEquals(requirements, leaderCardLight.getRequirements());
    }

}