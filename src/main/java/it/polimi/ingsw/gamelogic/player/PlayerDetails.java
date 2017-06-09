package it.polimi.ingsw.gamelogic.player;

import it.polimi.ingsw.gamelogic.enums.GeneralColor;

import java.util.Objects;

/**
 * Class that describes the Player's basic information
 */
public class PlayerDetails {
    private String playerIdentifier;
    private String playerName;
    private GeneralColor playerColor;

    public PlayerDetails() {
        playerIdentifier = "";
        playerName = "";
        playerColor = GeneralColor.UNDEFINED;
    }

    public PlayerDetails(String playerIdentifier, String playerName, GeneralColor playerColor) {
        this.playerIdentifier = playerIdentifier;
        this.playerName = playerName;
        this.playerColor = playerColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PlayerDetails that = (PlayerDetails) o;
        return Objects.equals(getPlayerIdentifier(), that.getPlayerIdentifier()) &&
                Objects.equals(getPlayerName(), that.getPlayerName()) &&
                getPlayerColor() == that.getPlayerColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerIdentifier(), getPlayerName(), getPlayerColor());
    }

    /**
     * Check if details are empty
     * @return true if instance is empty
     */
    public boolean isEmpty() {
        return "".equals(playerIdentifier) &&
                "".equals(playerName) &&
                playerColor == GeneralColor.UNDEFINED;
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

    public GeneralColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(GeneralColor playerColor) {
        this.playerColor = playerColor;
    }
}
