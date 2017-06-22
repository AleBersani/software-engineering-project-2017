package it.polimi.ingsw.server.gamelogic.player;

import it.polimi.ingsw.shared.model.GeneralColor;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class that describes the Player's basic information
 */
public class PlayerDetails implements Serializable {
    private String playerName;
    private GeneralColor playerColor;

    public PlayerDetails() {
        playerName = "";
        playerColor = GeneralColor.UNDEFINED;
    }

    public PlayerDetails(String playerName, GeneralColor playerColor) {
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
        return Objects.equals(getPlayerName(), that.getPlayerName()) &&
                getPlayerColor() == that.getPlayerColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerName(), getPlayerColor());
    }

    /**
     * Check if details are empty
     * @return true if instance is empty
     */
    public boolean isEmpty() {
        return "".equals(playerName) &&
                playerColor == GeneralColor.UNDEFINED;
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
