package it.polimi.ingsw.gamelogic.modifiers;

import it.polimi.ingsw.gamelogic.enums.ActionType;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: JavaDoc
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
     * TODO: JavaDoc
     * @param actionTypes
     * @return
     */
    public boolean hasAvailableActions(List<ActionType> actionTypes) {
        return actionTypes.stream()
                .allMatch(availableAction -> hasAvailableAction(availableAction));
    }

    /**
     * TODO: JavaDoc
     * @param actionTypeToVerify
     * @return
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
