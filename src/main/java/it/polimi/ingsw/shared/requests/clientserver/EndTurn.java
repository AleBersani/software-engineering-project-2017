package it.polimi.ingsw.shared.requests.clientserver;

import it.polimi.ingsw.server.middleware.ServerReceiver;

import java.io.Serializable;

public class EndTurn implements ClientServerRequest, Serializable {
    private BaseInformation baseInformation;

    public EndTurn(BaseInformation baseInformation) {
        this.baseInformation = baseInformation;
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
}
