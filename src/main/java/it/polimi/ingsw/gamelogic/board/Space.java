package it.polimi.ingsw.gamelogic.board;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.enums.BoardIdentifiers;

public class Space {
    private BoardIdentifiers boardIdentifier;
    private int requestedValue;
    private Goods instantGoods; // Optional

    private boolean alreadyTaken;

    public Space(BoardIdentifiers boardIdentifier, int requestedValue) {
        this.boardIdentifier = boardIdentifier;
        this.requestedValue = requestedValue;
        instantGoods = new Goods();
        alreadyTaken = false;
    }

    public Space(BoardIdentifiers boardIdentifier, int requestedValue, Goods instantGoods) {
        this.boardIdentifier = boardIdentifier;
        this.requestedValue = requestedValue;
        this.instantGoods = instantGoods;
        alreadyTaken = false;
    }

    public BoardIdentifiers getBoardIdentifier() {
        return boardIdentifier;
    }

    public void setBoardIdentifier(BoardIdentifiers boardIdentifier) {
        this.boardIdentifier = boardIdentifier;
    }

    public int getRequestedValue() {
        return requestedValue;
    }

    public void setRequestedValue(int requestedValue) {
        this.requestedValue = requestedValue;
    }

    public Goods getInstantGoods() {
        return instantGoods;
    }

    public void setInstantGoods(Goods instantGoods) {
        this.instantGoods = instantGoods;
    }

    public boolean isAlreadyTaken() {
        return alreadyTaken;
    }

    public void setAlreadyTaken(boolean alreadyTaken) {
        this.alreadyTaken = alreadyTaken;
    }
}
