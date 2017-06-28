package it.polimi.ingsw.server.gamelogic.actions.description;

import it.polimi.ingsw.shared.model.ActionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LeaderActionTest {
    private LeaderAction leaderAction;

    @BeforeEach
    void setUp() {
        leaderAction = new LeaderAction(ActionType.LEADER_ACTIVATION, "");
    }

    @Test
    void testEquals() {
        LeaderAction leaderActionToConfront = new LeaderAction(ActionType.LEADER_ACTIVATION, "");
        assertTrue(leaderAction.equals(leaderActionToConfront));
    }

    @Test
    void testGetActionType() {
        leaderAction.setActionType(ActionType.LEADER_PLACEMENT);
        assertEquals(ActionType.LEADER_PLACEMENT, leaderAction.getActionType());
    }

    @Test
    void testGetLeaderName() {
        leaderAction.setLeaderName("Lorenzo il magnifico");
        assertEquals("Lorenzo il magnifico", leaderAction.getLeaderName());
    }
}