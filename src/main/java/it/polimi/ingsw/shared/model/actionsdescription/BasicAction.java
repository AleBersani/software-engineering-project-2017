package it.polimi.ingsw.shared.model.actionsdescription;

import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.BoardIdentifier;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class representing the basic information of an action
 */
public class BasicAction implements Serializable {
    private ActionType actionType;
    private BoardIdentifier boardIdentifier;
    private int actionValue;

    public BasicAction(ActionType actionType, BoardIdentifier boardIdentifier, int actionValue) {
        this.actionType = actionType;
        this.boardIdentifier = boardIdentifier;
        this.actionValue = actionValue;
    }

    @Override
    public String toString() {
        return "BasicAction{" +
                "actionType=" + actionType +
                ", boardIdentifier=" + boardIdentifier +
                ", actionValue=" + actionValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BasicAction that = (BasicAction) o;
        return getActionValue() == that.getActionValue() &&
                getActionType() == that.getActionType() &&
                getBoardIdentifier() == that.getBoardIdentifier();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActionType(), getBoardIdentifier(), getActionValue());
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public BoardIdentifier getBoardIdentifier() {
        return boardIdentifier;
    }

    public void setBoardIdentifier(BoardIdentifier boardIdentifier) {
        this.boardIdentifier = boardIdentifier;
    }

    public int getActionValue() {
        return actionValue;
    }

    public void setActionValue(int actionValue) {
        this.actionValue = actionValue;
    }
}
