package it.polimi.ingsw.client.model;

import it.polimi.ingsw.shared.model.BoardIdentifier;

import java.util.ArrayList;
import java.util.List;

public class CouncilPalaceLight {
    private BoardIdentifier boardIdentifier;
    private List<PawnLight> pawnLightList;

    public CouncilPalaceLight(BoardIdentifier boardIdentifier) {
        this.boardIdentifier = boardIdentifier;
        pawnLightList = new ArrayList<>();
    }

    public BoardIdentifier getBoardIdentifier() {
        return boardIdentifier;
    }

    public void setBoardIdentifier(BoardIdentifier boardIdentifier) {
        this.boardIdentifier = boardIdentifier;
    }

    public List<PawnLight> getPawnLightList() {
        return pawnLightList;
    }

    public void setPawnLightList(List<PawnLight> pawnLightList) {
        this.pawnLightList = pawnLightList;
    }
}
