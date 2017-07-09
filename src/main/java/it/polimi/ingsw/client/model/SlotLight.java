package it.polimi.ingsw.client.model;

import it.polimi.ingsw.shared.model.BoardIdentifier;

import java.io.Serializable;
import java.util.Optional;

public class SlotLight implements Serializable {
    private BoardIdentifier boardIdentifier;
    private int requestedValue;
    private PawnLight pawnLight;

    public SlotLight(BoardIdentifier boardIdentifier, int requestedValue) {
        this.boardIdentifier = boardIdentifier;
        this.requestedValue = requestedValue;
        pawnLight = null;
    }

    public SlotLight(BoardIdentifier boardIdentifier, int requestedValue, PawnLight pawnLight) {
        this.boardIdentifier = boardIdentifier;
        this.requestedValue = requestedValue;
        this.pawnLight = pawnLight;
    }

    @Override
    public String toString() {
        String toString = "BoardId " + boardIdentifier + "\n" +
                "Value " + requestedValue + "\n";
        if (pawnLight != null){
            toString = toString + "Player " + pawnLight.getPlayerName() + "\n" +
                    "Color " + pawnLight.getPawnColor() + "\n";
        }
        else
            toString = toString + " \n";
        return toString;
    }

    public BoardIdentifier getBoardIdentifier() {
        return boardIdentifier;
    }

    public void setBoardIdentifier(BoardIdentifier boardIdentifier) {
        this.boardIdentifier = boardIdentifier;
    }

    public int getRequestedValue() {
        return requestedValue;
    }

    public void setRequestedValue(int requestedValue) {
        this.requestedValue = requestedValue;
    }

    public Optional<PawnLight> getPawnLight() {
        if (pawnLight == null) {
            return Optional.empty();
        }
        return Optional.of(pawnLight);
    }

    public void setPawnLight(PawnLight pawnLight) {
        this.pawnLight = pawnLight;
    }
}
