package it.polimi.ingsw.gamelogic.modifiers;

import it.polimi.ingsw.gamelogic.enums.ActionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AvailableActions that = (AvailableActions) o;
        return Objects.equals(getActionTypes(), that.getActionTypes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActionTypes());
    }

    /**
     * Checks if all the action types match with the Available Actions
     * @param actionTypes all the action type
     * @return true if all the action type match the available actions
     */
    public boolean hasAvailableActions(List<ActionType> actionTypes) {
        return actionTypes.stream()
                .allMatch(this::hasAvailableAction);
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
