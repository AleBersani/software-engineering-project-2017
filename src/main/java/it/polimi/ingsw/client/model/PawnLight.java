package it.polimi.ingsw.client.model;

import it.polimi.ingsw.shared.model.PawnColor;

import java.io.Serializable;

public class PawnLight implements Serializable {
    private String playerName;
    private PawnColor pawnColor;
    private boolean placed;

    public PawnLight(String playerName, PawnColor pawnColor, boolean placed) {
        this.playerName = playerName;
        this.pawnColor = pawnColor;
        this.placed = placed;
    }

    @Override
    public String toString() {
        return "PawnLight{" +
                "playerName='" + playerName + '\'' +
                ", pawnColor=" + pawnColor +
                ", placed=" + placed +
                '}';
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public PawnColor getPawnColor() {
        return pawnColor;
    }

    public void setPawnColor(PawnColor pawnColor) {
        this.pawnColor = pawnColor;
    }

    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }
}
