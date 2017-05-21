package it.polimi.ingsw.gamelogic.board;

import it.polimi.ingsw.gamelogic.cards.developmentcards.DevelopmentCard;
import it.polimi.ingsw.gamelogic.basics.Goods;

public class TowerSlot {
    private DevelopmentCard card;
    private int requestedValue;
    private Goods instantGoods;

    private boolean occupiedTowerSlot;

    public TowerSlot(DevelopmentCard card, int requestedValue, Goods instantGoods) {
        this.card = card;
        this.requestedValue = requestedValue;
        this.instantGoods = instantGoods;
        occupiedTowerSlot = false;
    }

    public DevelopmentCard getCard() {
        return card;
    }

    public void setCard(DevelopmentCard card) {
        this.card = card;
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

    public boolean isOccupiedTowerSlot() {
        return occupiedTowerSlot;
    }

    public void setOccupiedTowerSlot(boolean occupiedTowerSlot) {
        this.occupiedTowerSlot = occupiedTowerSlot;
    }
}
