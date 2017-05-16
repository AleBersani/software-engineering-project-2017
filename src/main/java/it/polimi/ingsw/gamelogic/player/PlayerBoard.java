package it.polimi.ingsw.gamelogic.player;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.cards.DevelopmentCard;

import java.util.ArrayList;
import java.util.List;

public class PlayerBoard {
    private BonusTiles bonusTiles;
    private Goods goods;

    private List<Pawn> pawns;
    private Deck deck;
    private List<DevelopmentCard> developmentCards;

    public PlayerBoard(BonusTiles bonusTiles, Goods goods) {
        this.bonusTiles = bonusTiles;
        this.goods = goods;
        pawns = new ArrayList<>();
        deck = new Deck();
        developmentCards = new ArrayList<>();
    }

    /*
    TODO: methods for decorators or other auxiliaries methods?
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

    public List<Pawn> getPawns() {
        return pawns;
    }

    public void setPawns(List<Pawn> pawns) {
        this.pawns = pawns;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public List<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }

    public void setDevelopmentCards(List<DevelopmentCard> developmentCards) {
        this.developmentCards = developmentCards;
    }
}
