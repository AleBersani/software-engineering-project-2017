package it.polimi.ingsw.gamelogic.player;

import it.polimi.ingsw.shared.model.PawnColor;

import java.util.Objects;

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
        if (o == null || getClass() != o.getClass())
            return false;
        Pawn pawn = (Pawn) o;
        return getValue() == pawn.getValue() &&
                isPlacedOnBoard() == pawn.isPlacedOnBoard() &&
                getPawnColor() == pawn.getPawnColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getPawnColor(), isPlacedOnBoard());
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
