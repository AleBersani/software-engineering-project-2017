package it.polimi.ingsw.gamelogic.modifiers;

import it.polimi.ingsw.gamelogic.enums.ActionType;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that describes the available actions in a given situation
 */
public class AvailableActions {
    private List<ActionType> actionTypes;

    public AvailableActions() {
        actionTypes = new ArrayList<>();
    }

    public AvailableActions(ActionType actionType) {
        actionTypes = new ArrayList<>();
        actionTypes.add(actionType);
    }

    public AvailableActions(List<ActionType> actionTypes) {
        this.actionTypes = actionTypes;
    }

    /**
     * Checks if all the action types match with the Available Actions
     * @param actionTypes all the action type
     * @return true if all the action type match the available actions
     */
    public boolean hasAvailableActions(List<ActionType> actionTypes) {
        return actionTypes.stream()
                .allMatch(availableAction -> hasAvailableAction(availableAction));
    }

    /**
     * Checks if at least one of the action types matches an available action
     * @param actionTypeToVerify action to check
     * @return true if the actionTypeToVerify matches at least one availableAction
     */
    public boolean hasAvailableAction(ActionType actionTypeToVerify) {
        return actionTypes.stream()
                .anyMatch(availableAction -> availableAction.equals(actionTypeToVerify));
    }

    public List<ActionType> getActionTypes() {
        return actionTypes;
    }

    public void setActionTypes(List<ActionType> actionTypes) {
        this.actionTypes = actionTypes;
    }
}
