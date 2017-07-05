package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.shared.model.BoardIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionSpaceTest {
    private ActionSpace actionSpace;

    @BeforeEach
    void setUp() {
        actionSpace = new ActionSpace(new Space(BoardIdentifier.HARVEST_1, 1),
                2);
    }

    @Test
    void testEqualsTrue() {
        ActionSpace actionSpaceToConfront = new ActionSpace(new Space(BoardIdentifier.HARVEST_1,
                1), 2);
        assertTrue(actionSpace.equals(actionSpaceToConfront));
    }

    @Test
    void testEqualsFalse() {
        ActionSpace actionSpaceToConfront = new ActionSpace(new Space(BoardIdentifier.HARVEST_1,
                2), 2);
        assertFalse(actionSpace.equals(actionSpaceToConfront));
    }

    @Test
    void testEqualsDifferent() {
        String obj = "";
        assertFalse(actionSpace.equals(obj));
    }

    @Test
    void testHashCodeTrue() {
        ActionSpace actionSpaceToConfront = new ActionSpace(new Space(BoardIdentifier.HARVEST_1,
                1), 2);
        assertEquals(actionSpace.hashCode(), actionSpaceToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        ActionSpace actionSpaceToConfront = new ActionSpace(new Space(BoardIdentifier.HARVEST_1,
                2), 2);
        assertNotEquals(actionSpace.hashCode(), actionSpaceToConfront.hashCode());
    }

    @Test
    void getSpace() {
        Space spaceToConfront = new Space(BoardIdentifier.T_G_4, 4);
        actionSpace.setSpace(spaceToConfront);
        assertEquals(spaceToConfront, actionSpace.getSpace());
    }

    @Test
    void getRequiredPlayersNumber() {
        int numberToConfront = 2;
        actionSpace.setRequiredPlayersNumber(numberToConfront);
        assertEquals(numberToConfront, actionSpace.getRequiredPlayersNumber());
    }
}