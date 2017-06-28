package it.polimi.ingsw.server.gamelogic.actions.description;

import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasicActionTest {
    private BasicAction basicAction;

    @BeforeEach
    void setUp() {
        basicAction = new BasicAction(ActionType.BLUE_TOWER, BoardIdentifier.T_B_1, 1);
    }

    @Test
    void testEquals() {
        BasicAction basicActionToConfront = new BasicAction(ActionType.BLUE_TOWER, BoardIdentifier.T_B_1, 1);
        assertEquals(basicActionToConfront, basicAction);
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