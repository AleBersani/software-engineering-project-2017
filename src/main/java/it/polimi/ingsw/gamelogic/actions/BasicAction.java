package it.polimi.ingsw.gamelogic.actions;

import it.polimi.ingsw.gamelogic.enums.AvailableActions;
import it.polimi.ingsw.gamelogic.enums.BoardIdentifiers;

/**
 * Class representing the basic information of an action
 */
public class BasicAction {
    private AvailableActions actionType;
    private BoardIdentifiers boardIdentifiers;
    private int actionValue;

    public BasicAction(AvailableActions actionType, BoardIdentifiers boardIdentifiers, int actionValue) {
        this.actionType = actionType;
        this.boardIdentifiers = boardIdentifiers;
        this.actionValue = actionValue;
    }

    public AvailableActions getActionType() {
        return actionType;
    }

    public void setActionType(AvailableActions actionType) {
        this.actionType = actionType;
    }

    public BoardIdentifiers getBoardIdentifiers() {
        return boardIdentifiers;
    }

    public void setBoardIdentifiers(BoardIdentifiers boardIdentifiers) {
        this.boardIdentifiers = boardIdentifiers;
    }

    public int getActionValue() {
        return actionValue;
    }

    public void setActionValue(int actionValue) {
        this.actionValue = actionValue;
    }

    public BasicAction() {
    }
}
