package it.polimi.ingsw.client.model;

import it.polimi.ingsw.client.model.enums.PointsLight;
import it.polimi.ingsw.shared.model.BoardIdentifier;

import java.util.Map;

public class SlotLight {
    private BoardIdentifier boardIdentifier;
    private int requestedValue;
    private PawnLight pawnLight; // TODO

    public SlotLight(BoardIdentifier boardIdentifier, int requestedValue) {
        this.boardIdentifier = boardIdentifier;
        this.requestedValue = requestedValue;
        //TODO
    }

    public SlotLight(BoardIdentifier boardIdentifier, int requestedValue, PawnLight pawnLight) {
        this.boardIdentifier = boardIdentifier;
        this.requestedValue = requestedValue;
        this.pawnLight = pawnLight;
    }
}
