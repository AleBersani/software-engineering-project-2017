package it.polimi.ingsw.server.gamelogic.actions.description;

import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicActionTest {
    private BasicAction basicAction;

    @BeforeEach
    void setUp() {
        basicAction = new BasicAction(ActionType.BLUE_TOWER, BoardIdentifier.T_B_1, 1);
    }

    @Test
    void testEqualsTrue() {
        BasicAction basicActionToConfront = new BasicAction(ActionType.BLUE_TOWER, BoardIdentifier.T_B_1, 1);
        BasicAction basicActionToConfront2 = basicAction;
        assertTrue(basicAction.equals(basicActionToConfront));
        assertTrue(basicAction.equals(basicActionToConfront2));
    }

    @Test
    void testEqualsFalse() {
        BasicAction basicActionToConfront = new BasicAction(ActionType.GREEN_TOWER, BoardIdentifier.T_B_1, 1);
        BasicAction basicActionToConfront2 = new BasicAction(ActionType.BLUE_TOWER, BoardIdentifier.T_G_1, 1);
        BasicAction basicActionToConfront3 = new BasicAction(ActionType.BLUE_TOWER, BoardIdentifier.T_B_1, 3);
        assertFalse(basicAction.equals(basicActionToConfront));
        assertFalse(basicAction.equals(basicActionToConfront2));
        assertFalse(basicAction.equals(basicActionToConfront3));
    }

    @Test
    void testEqualsDifferent() {
        assertFalse(basicAction.equals(" "));
        assertFalse(basicAction.equals(null));
    }

    @Test
    void testGetActionType() {
        basicAction.setActionType(ActionType.YELLOW_TOWER);
        assertEquals(ActionType.YELLOW_TOWER, basicAction.getActionType());
    }

    @Test
    void testGetBoardIdentifier() {
        basicAction.setBoardIdentifier(BoardIdentifier.T_Y_1);
        assertEquals(BoardIdentifier.T_Y_1, basicAction.getBoardIdentifier());
    }

    @Test
    void testGetActionValue() {
        basicAction.setActionValue(3);
        assertEquals(3, basicAction.getActionValue());
    }
}