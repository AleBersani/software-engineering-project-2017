package it.polimi.ingsw.shared.requests.clientserver;

import it.polimi.ingsw.server.middleware.ServerReceiver;
import it.polimi.ingsw.shared.model.actionsdescription.BoardAction;

import java.io.Serializable;

public class ChosenConsumableAction implements ClientServerRequest, Serializable {
    private BaseInformation baseInformation;
    private BoardAction boardAction;
    private String nameOfCardGivingAction;

    public ChosenConsumableAction(BaseInformation baseInformation, BoardAction boardAction, String nameOfCardGivingAction) {
        this.baseInformation = baseInformation;
        this.boardAction = boardAction;
        this.nameOfCardGivingAction = nameOfCardGivingAction;
    }

    @Override
    public void acceptServerReceiver(ServerReceiver serverReceiver) {
        serverReceiver.visitClientServerRequest(this);
    }

    public int getGameId() {
        return baseInformation.getGameId();
    }

    public void setGameId(int gameId) {
        baseInformation.setGameId(gameId);
    }

    public String getPlayerName() {
        return baseInformation.getPlayerName();
    }

    public void setPlayerName(String playerName) {
        baseInformation.setPlayerName(playerName);
    }

    public BoardAction getBoardAction() {
        return boardAction;
    }

    public void setBoardAction(BoardAction boardAction) {
        this.boardAction = boardAction;
    }

    public String getNameOfCardGivingAction() {
        return nameOfCardGivingAction;
    }

    public void setNameOfCardGivingAction(String nameOfCardGivingAction) {
        this.nameOfCardGivingAction = nameOfCardGivingAction;
    }
}
