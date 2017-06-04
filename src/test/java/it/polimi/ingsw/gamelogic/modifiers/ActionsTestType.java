package it.polimi.ingsw.gamelogic.modifiers;

import it.polimi.ingsw.gamelogic.enums.ActionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ActionsTestType {
    private AvailableActions availableActions;
    private AvailableActions singleAvailableAction;

    @BeforeEach
    void setUp() {
        List<ActionType> actionTypeList = new ArrayList<>();
        actionTypeList.add(ActionType.PRODUCTION);
        actionTypeList.add(ActionType.COUNCIL_PALACE);
        actionTypeList.add(ActionType.YELLOW_TOWER);
        actionTypeList.add(ActionType.MARKET);
        availableActions = new AvailableActions(actionTypeList);
        singleAvailableAction = new AvailableActions(ActionType.BLUE_TOWER);
    }

    @Test
    void testHasAvailableAction() {
        List<ActionType> testList = new ArrayList<>();
        testList.add(ActionType.BLUE_TOWER);
        assertTrue(singleAvailableAction.hasAvailableActions(testList));
    }

    @Test
    void testHasAvailableActionFalse() {
        List<ActionType> testList = new ArrayList<>();
        testList.add(ActionType.PURPLE_TOWER);
        assertFalse(singleAvailableAction.hasAvailableActions(testList));
    }

    @Test
    void testHasAvailableActions() {
        List<ActionType> testList = new ArrayList<>();
        testList.add(ActionType.COUNCIL_PALACE);
        testList.add(ActionType.MARKET);
        assertTrue(availableActions.hasAvailableActions(testList));
    }

    @Test
    void testHasAvailableActionsFalse() {
        List<ActionType> testList = new ArrayList<>();
        testList.add(ActionType.COUNCIL_PALACE);
        testList.add(ActionType.BLUE_TOWER);
        assertFalse(availableActions.hasAvailableActions(testList));
    }
}