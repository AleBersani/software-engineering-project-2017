package it.polimi.ingsw.gamelogic.player;

import it.polimi.ingsw.gamelogic.basics.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: JavaDoc
 */
public class PlayerBoard {
    private BonusTiles bonusTiles;
    private Goods goods;

    private Deck deck;
    private List<Pawn> pawns;

    public PlayerBoard(BonusTiles bonusTiles, Goods goods) {
        this.bonusTiles = bonusTiles;
        this.goods = goods;
        deck = new Deck();
        pawns = new ArrayList<>();
    }

    /*
    TODO: Aux method
     */

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

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public List<Pawn> getPawns() {
        return pawns;
    }

    public void setPawns(List<Pawn> pawns) {
        this.pawns = pawns;
    }
}
