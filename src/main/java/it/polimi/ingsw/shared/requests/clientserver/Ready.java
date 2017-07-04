package it.polimi.ingsw.shared.requests.clientserver;

import it.polimi.ingsw.server.middleware.ServerReceiver;

public class Ready implements ClientServerRequest {
    private int gameId;
    private String readyForWhat;

    public Ready(int gameId, String readyForWhat) {
        this.gameId = gameId;
        this.readyForWhat = readyForWhat;
    }

    @Override
    public void acceptServerReceiver(ServerReceiver serverReceiver) {
        serverReceiver.visitClientServerRequest(this);
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getReadyForWhat() {
        return readyForWhat;
    }

    public void setReadyForWhat(String readyForWhat) {
        this.readyForWhat = readyForWhat;
    }
}
