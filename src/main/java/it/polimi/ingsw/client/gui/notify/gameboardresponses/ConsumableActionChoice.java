package it.polimi.ingsw.client.gui.notify.gameboardresponses;

import it.polimi.ingsw.shared.model.ActionType;

import java.util.List;

public class ConsumableActionChoice implements GameBoardResponse {
    private List<ActionType> actionTypeList;
    private String cardNameToResend;

    public ConsumableActionChoice(List<ActionType> actionTypeList, String cardNameToResend) {
        this.actionTypeList = actionTypeList;
        this.cardNameToResend = cardNameToResend;
    }

    @Override
    public void acceptGameBoardVisitor(GameBoardVisitor gameBoardVisitor) {
        gameBoardVisitor.visitGameBoardResponse(this);
    }

    public List<ActionType> getActionTypeList() {
        return actionTypeList;
    }

    public void setActionTypeList(List<ActionType> actionTypeList) {
        this.actionTypeList = actionTypeList;
    }

    public String getCardNameToResend() {
        return cardNameToResend;
    }

    public void setCardNameToResend(String cardNameToResend) {
        this.cardNameToResend = cardNameToResend;
    }
}
