package it.polimi.ingsw.shared.requests.clientserver;

import it.polimi.ingsw.server.middleware.ServerReceiver;
import it.polimi.ingsw.shared.support.GameStartType;

import java.io.Serializable;

public class GameStartChoice implements Serializable, ClientServerRequest {
    private String playerName;
    private GameStartType gameStartType;

    public GameStartChoice(String playerName, GameStartType gameStartType) {
        this.playerName = playerName;
        this.gameStartType = gameStartType;
    }

    @Override
    public void acceptServerReceiver(ServerReceiver serverReceiver) {
        serverReceiver.visitClientServerRequest(this);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public GameStartType getGameStartType() {
        return gameStartType;
    }

    public void setGameStartType(GameStartType gameStartType) {
        this.gameStartType = gameStartType;
    }
}
