package it.polimi.ingsw.server.gamelogic.actions.description;

import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import it.polimi.ingsw.shared.model.PawnColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardActionTest {
    private BoardAction boardAction;

    @BeforeEach
    void setUp() {
        boardAction = new BoardAction(new BasicAction(ActionType.BLUE_TOWER, BoardIdentifier.T_B_1, 1));
    }

    @Test
    void testEqualsTrue() {
        BoardAction boardActionToConfront = new BoardAction(new BasicAction(ActionType.BLUE_TOWER, BoardIdentifier.T_B_1,
                1));
        assertTrue(boardAction.equals(boardActionToConfront));
    }

    @Test
    void testEqualsFalse() {
        BoardAction boardActionToConfront = new BoardAction(new BasicAction(ActionType.GREEN_TOWER, BoardIdentifier.T_B_1,
                2));
        assertFalse(boardAction.equals(boardActionToConfront));
    }

    @Test
    void testEqualsDifferent() {
        String different = "";
        assertFalse(boardAction.equals(different));
    }

    @Test
    void testHashCodeTrue() {
        BoardAction boardActionToConfront = new BoardAction(new BasicAction(ActionType.BLUE_TOWER, BoardIdentifier.T_B_1,
                1));
        assertTrue(boardAction.hashCode() == boardActionToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        BoardAction boardActionToConfront = new BoardAction(new BasicAction(ActionType.GREEN_TOWER, BoardIdentifier.T_B_1,
                2));
        assertFalse(boardAction.hashCode() == boardActionToConfront.hashCode());
    }

    @Test
    void testGetBasicAction() {
        boardAction.setBasicAction(new BasicAction(ActionType.YELLOW_TOWER, BoardIdentifier.T_Y_1, 3));
        assertEquals(new BasicAction(ActionType.YELLOW_TOWER, BoardIdentifier.T_Y_1, 3),
                boardAction.getBasicAction());
    }

    @Test
    void testGetPawnColor() {
        boardAction.setPawnColor(PawnColor.BLACK);
        assertEquals(PawnColor.BLACK, boardAction.getPawnColor());
    }

    @Test
    void testGetNumberOfServants() {
        boardAction.setNumberOfServants(3);
        assertEquals(3, boardAction.getNumberOfServants());
    }
}