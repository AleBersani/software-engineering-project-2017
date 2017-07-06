package it.polimi.ingsw.server.gamelogic.actions.description;

import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import it.polimi.ingsw.shared.model.PawnColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardActionTest {
    private BoardAction boardAction;

    @BeforeEach
    void setUp() {
        boardAction = new BoardAction(new BasicAction(ActionType.BLUE_TOWER, BoardIdentifier.T_B_1, 1));
    }

    @Test
    void testEqualsTrue1() {
        BoardAction boardActionToConfront = new BoardAction(new BasicAction(ActionType.BLUE_TOWER, BoardIdentifier.T_B_1,
                1));
        assertTrue(boardAction.equals(boardActionToConfront));
    }

    @Test
    void testEqualsTrue2() {
        BoardAction boardActionToConfront = boardAction;
        assertTrue(boardAction.equals(boardActionToConfront));
    }

    @Test
    void testEqualsFalse1() {
        BoardAction boardActionToConfront = new BoardAction(new BasicAction(ActionType.GREEN_TOWER, BoardIdentifier.T_B_1,
                2));
        assertFalse(boardAction.equals(boardActionToConfront));
    }

    @Test
    void testEqualsFalse2() {
        BoardAction boardActionToConfront = new BoardAction(new BasicAction(ActionType.BLUE_TOWER, BoardIdentifier.T_B_1,
                1),2);
        assertFalse(boardAction.equals(boardActionToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(boardAction.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(boardAction.equals(null));
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