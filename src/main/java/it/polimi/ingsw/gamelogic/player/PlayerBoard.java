package it.polimi.ingsw.gamelogic.player;

import it.polimi.ingsw.gamelogic.basics.Goods;

import java.util.ArrayList;
import java.util.List;

public class PlayerBoard {
    private BonusTiles bonusTiles;
    private Goods goods;

    private List<Pawn> pawns;

    public PlayerBoard(BonusTiles bonusTiles, Goods goods) {
        this.bonusTiles = bonusTiles;
        this.goods = goods;
        pawns = new ArrayList<>();
    }

    public BonusTiles getBonusTiles() {
        return bonusTiles;
    }

    public void setBonusTiles(BonusTiles bonusTiles) {
        this.bonusTiles = bonusTiles;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public List<Pawn> getPawns() {
        return pawns;
    }

    public void setPawns(List<Pawn> pawns) {
        this.pawns = pawns;
    }
}
