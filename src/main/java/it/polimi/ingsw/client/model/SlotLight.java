package it.polimi.ingsw.client.model;

import it.polimi.ingsw.shared.model.BoardIdentifier;

import java.util.Optional;

public class SlotLight {
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
