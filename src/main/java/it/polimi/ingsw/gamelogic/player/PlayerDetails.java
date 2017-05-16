package it.polimi.ingsw.gamelogic.player;

import it.polimi.ingsw.gamelogic.enums.GlobalColor;

public class PlayerDetails {
    private String playerIdentifier;
    private String playerName;
    private GlobalColor playerColor;

    public PlayerDetails(String playerIdentifier, String playerName, GlobalColor playerColor) {
        this.playerIdentifier = playerIdentifier;
        this.playerName = playerName;
        this.playerColor = playerColor;
    }

    public String getPlayerIdentifier() {
        return playerIdentifier;
    }

    public void setPlayerIdentifier(String playerIdentifier) {
        this.playerIdentifier = playerIdentifier;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public GlobalColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(GlobalColor playerColor) {
        this.playerColor = playerColor;
    }
}
