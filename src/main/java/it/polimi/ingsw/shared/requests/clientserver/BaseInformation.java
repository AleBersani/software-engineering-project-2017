package it.polimi.ingsw.shared.requests.clientserver;

import java.io.Serializable;

public class BaseInformation implements Serializable {
    private int gameId;
    private String playerName;

    public BaseInformation(int gameId, String playerName) {
        this.gameId = gameId;
        this.playerName = playerName;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
