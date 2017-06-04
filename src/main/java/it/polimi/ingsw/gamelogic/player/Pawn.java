package it.polimi.ingsw.gamelogic.player;

import it.polimi.ingsw.gamelogic.enums.PawnColor;

/**
 * Class that describes each player's pawn
 */
public class Pawn {
    private int value;
    private PawnColor pawnColor;

    private boolean placedOnBoard;

    public Pawn(int value, PawnColor pawnColor) {
        this.value = value;
        this.pawnColor = pawnColor;
        placedOnBoard = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Pawn))
            return false;

        Pawn pawn = (Pawn) o;

        if (getValue() != pawn.getValue())
            return false;
        return getPawnColor() == pawn.getPawnColor();
    }

    @Override
    public int hashCode() {
        int result = getValue();
        result = 31 * result + getPawnColor().hashCode();
        return result;
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

    public boolean isPlacedOnBoard() {
        return placedOnBoard;
    }

    public void setPlacedOnBoard(boolean placedOnBoard) {
        this.placedOnBoard = placedOnBoard;
    }
}
