package it.polimi.ingsw.gamelogic.player;

import it.polimi.ingsw.gamelogic.enums.PawnColor;

public class Pawn {
    private int value;
    private PawnColor pawnColor;

    public Pawn(int value, PawnColor pawnColor) {
        this.value = value;
        this.pawnColor = pawnColor;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public PawnColor getPawnColor() {
        return pawnColor;
    }

    public void setPawnColor(PawnColor pawnColor) {
        this.pawnColor = pawnColor;
    }
}
