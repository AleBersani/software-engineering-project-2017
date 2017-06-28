package it.polimi.ingsw.server.gamelogic.cards.leader;

import it.polimi.ingsw.server.gamelogic.enums.LeaderCategory;
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
    void testEquals() {
        LeaderInformation expectedLeaderInformation = new LeaderInformation("Lorenzo Il Magnifico",
                                                                                LeaderCategory.CONSUMABLE);
        assertTrue(expectedLeaderInformation.equals(leaderInformation));
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