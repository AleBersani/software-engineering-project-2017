package it.polimi.ingsw.shared.requests.clientserver;

import it.polimi.ingsw.server.middleware.ServerReceiver;
import it.polimi.ingsw.shared.model.actionsdescription.BoardAction;

import java.io.Serializable;
import java.util.List;

public class ChosenCouncilPrivilege implements ClientServerRequest, Serializable {
    private BaseInformation baseInformation;
    private List<Integer> choices;
    private BoardAction boardAction;

    public ChosenCouncilPrivilege(BaseInformation baseInformation, List<Integer> choices, BoardAction boardAction) {
        this.baseInformation = baseInformation;
        this.choices = choices;
        this.boardAction = boardAction;
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

    public List<Integer> getChoices() {
        return choices;
    }

    public void setChoices(List<Integer> choices) {
        this.choices = choices;
    }

    public BoardAction getBoardAction() {
        return boardAction;
    }

    public void setBoardAction(BoardAction boardAction) {
        this.boardAction = boardAction;
    }
}
