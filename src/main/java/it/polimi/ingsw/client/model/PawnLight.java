package it.polimi.ingsw.client.model;

import it.polimi.ingsw.shared.model.PawnColor;

public class PawnLight {
    private String playerName;
    private PawnColor pawnColor;
    private boolean placed;

    public PawnLight(String playerName, PawnColor pawnColor, boolean placed) {
        this.playerName = playerName;
        this.pawnColor = pawnColor;
        this.placed = placed;
    }
}
