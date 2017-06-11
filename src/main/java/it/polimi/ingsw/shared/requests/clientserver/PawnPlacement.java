package it.polimi.ingsw.shared.requests.clientserver;

import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import it.polimi.ingsw.shared.model.PawnColor;

import java.io.Serializable;

public class PawnPlacement implements Serializable {
    private ActionType actionType;
    private BoardIdentifier boardIdentifier;
    private PawnColor pawnColor;
    private int actionValue;

    public PawnPlacement(ActionType actionType, BoardIdentifier boardIdentifier, PawnColor pawnColor, int actionValue) {
        this.actionType = actionType;
        this.boardIdentifier = boardIdentifier;
        this.pawnColor = pawnColor;
        this.actionValue = actionValue;
    }

    @Override
    public String toString() {
        return "PawnPlacement{" +
                "actionType=" + actionType +
                ", boardIdentifier=" + boardIdentifier +
                ", pawnColor=" + pawnColor +
                ", actionValue=" + actionValue +
                '}';
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

    public PawnColor getPawnColor() {
        return pawnColor;
    }

    public void setPawnColor(PawnColor pawnColor) {
        this.pawnColor = pawnColor;
    }

    public int getActionValue() {
        return actionValue;
    }

    public void setActionValue(int actionValue) {
        this.actionValue = actionValue;
    }
}
