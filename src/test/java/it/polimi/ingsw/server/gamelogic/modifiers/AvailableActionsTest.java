package it.polimi.ingsw.server.gamelogic.modifiers;

import it.polimi.ingsw.shared.model.ActionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AvailableActionsTest {
    private AvailableActions availableActions;

    @BeforeEach
    void setUp() {
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.BLUE_TOWER);
        actionTypes.add(ActionType.COUNCIL_PALACE);
        availableActions = new AvailableActions(actionTypes);
    }

    @Test
    void testEqualsTrue() {
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.BLUE_TOWER);
        actionTypes.add(ActionType.COUNCIL_PALACE);
        AvailableActions availableActionsToConfront = new AvailableActions(actionTypes);
        AvailableActions availableActionsToConfront2 = availableActions;
        assertTrue(availableActionsToConfront.equals(availableActions));
        assertTrue(availableActions.equals(availableActionsToConfront2));
    }

    @Test
    void testEqualsFalse() {
        AvailableActions availableActionsToConfront = new AvailableActions(ActionType.COUNCIL_PALACE);
        assertFalse(availableActionsToConfront.equals(availableActions));
    }

    @Test
    void testEqualsDifferent() {
        String obj = "";
        assertFalse(availableActions.equals(obj));
        assertFalse(availableActions.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.BLUE_TOWER);
        actionTypes.add(ActionType.COUNCIL_PALACE);
        AvailableActions availableActionsToConfront = new AvailableActions(actionTypes);
        assertEquals(availableActionsToConfront.hashCode(), availableActions.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        AvailableActions availableActionsToConfront = new AvailableActions(ActionType.COUNCIL_PALACE);
        assertNotEquals(availableActionsToConfront.hashCode(), availableActions.hashCode());
    }

    @Test
    void testHasAvailableActions() {
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.BLUE_TOWER);
        actionTypes.add(ActionType.COUNCIL_PALACE);
        assertTrue(availableActions.hasAvailableActions(actionTypes));
    }

    @Test
    void testHasAvailableAction() {
        assertTrue(availableActions.hasAvailableAction(ActionType.COUNCIL_PALACE));
    }

    @Test
    void testGetActionTypes() {
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.GREEN_TOWER);
        actionTypes.add(ActionType.YELLOW_TOWER);
        availableActions.setActionTypes(actionTypes);
        assertEquals(actionTypes, availableActions.getActionTypes());
    }

}