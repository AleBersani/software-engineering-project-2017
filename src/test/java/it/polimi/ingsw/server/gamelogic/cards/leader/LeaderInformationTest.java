package it.polimi.ingsw.server.gamelogic.cards.leader;

import it.polimi.ingsw.shared.model.LeaderCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeaderInformationTest {
    private LeaderInformation leaderInformation;

    @BeforeEach
    void setUp() {
        leaderInformation = new LeaderInformation("Lorenzo Il Magnifico", LeaderCategory.CONSUMABLE);
    }

    @Test
    void testEqualsTrue() {
        LeaderInformation expectedLeaderInformation = new LeaderInformation("Lorenzo Il Magnifico",
                                                                                LeaderCategory.CONSUMABLE);
        LeaderInformation leaderInformationToConfront2 = leaderInformation;
        assertTrue(expectedLeaderInformation.equals(leaderInformation));
        assertTrue(leaderInformation.equals(leaderInformationToConfront2));
    }

    @Test
    void testEqualsFalse() {
        LeaderInformation leaderInformationToConfront = new LeaderInformation("Lorenzo Il Magnifico",
                LeaderCategory.PERMANENT);
        LeaderInformation leaderInformationToConfront2 = new LeaderInformation("Santa Rita",
                LeaderCategory.CONSUMABLE);
        assertFalse(leaderInformation.equals(leaderInformationToConfront));
        assertFalse(leaderInformation.equals(leaderInformationToConfront2));
    }

    @Test
    void testEqualsDifferent() {
        assertFalse(leaderInformation.equals(" "));
        assertFalse(leaderInformation.equals(null));
    }

    @Test
    void testGetName() {
        String name = "Francesco Sforza";
        leaderInformation.setName(name);
        assertEquals(name, leaderInformation.getName());
    }

    @Test
    void testGetLeaderCategory() {
        LeaderCategory expectedLeaderCategory = LeaderCategory.PERMANENT;
        leaderInformation.setLeaderCategory(expectedLeaderCategory);
        assertEquals(expectedLeaderCategory, leaderInformation.getLeaderCategory());
    }
}