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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerDetails)) return false;

        PlayerDetails that = (PlayerDetails) o;

        if (!getPlayerIdentifier().equals(that.getPlayerIdentifier())) return false;
        if (!getPlayerName().equals(that.getPlayerName())) return false;
        return getPlayerColor() == that.getPlayerColor();
    }

    @Override
    public int hashCode() {
        int result = getPlayerIdentifier().hashCode();
        result = 31 * result + getPlayerName().hashCode();
        result = 31 * result + getPlayerColor().hashCode();
        return result;
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
