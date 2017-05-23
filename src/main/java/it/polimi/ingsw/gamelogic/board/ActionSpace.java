package it.polimi.ingsw.gamelogic.board;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.enums.BoardIdentifiers;

public class ActionSpace {
    private Space space;
    private int requiredPlayersNumber;
    private Goods instantGoods; // Optional

    public ActionSpace(Space space, int requiredPlayersNumber) {
        this.space = space;
        this.requiredPlayersNumber = requiredPlayersNumber;
        instantGoods = new Goods();
    }

    public ActionSpace(Space space, int requiredPlayersNumber, Goods instantGoods) {
        this.space = space;
        this.requiredPlayersNumber = requiredPlayersNumber;
        this.instantGoods = instantGoods;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public int getRequiredPlayersNumber() {
        return requiredPlayersNumber;
    }

    public void setRequiredPlayersNumber(int requiredPlayersNumber) {
        this.requiredPlayersNumber = requiredPlayersNumber;
    }

    public Goods getInstantGoods() {
        return instantGoods;
    }

    public void setInstantGoods(Goods instantGoods) {
        this.instantGoods = instantGoods;
    }
}
