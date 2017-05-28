package it.polimi.ingsw.gamelogic.decorators.requirements;

import it.polimi.ingsw.gamelogic.enums.ActionType;
import it.polimi.ingsw.gamelogic.enums.BoardIdentifier;
import it.polimi.ingsw.gamelogic.enums.PawnColor;

public class SpaceActionRequirements {
    private ActionType actionType;
    private BoardIdentifier boardIdentifier;
    private PawnColor pawnColor;
    private int actionValue;
    private int numberOfServants;

    public SpaceActionRequirements(ActionType actionType, BoardIdentifier boardIdentifier,
                                   PawnColor pawnColor, int actionValue, int numberOfServants) {
        this.actionType = actionType;
        this.boardIdentifier = boardIdentifier;
        this.pawnColor = pawnColor;
        this.actionValue = actionValue;
        this.numberOfServants = numberOfServants;
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

    public int getNumberOfServants() {
        return numberOfServants;
    }

    public void setNumberOfServants(int numberOfServants) {
        this.numberOfServants = numberOfServants;
    }
}
