package it.polimi.ingsw.gamelogic.basics;

public class FlashEffect {
    protected Goods instantGoods;

    public FlashEffect(Goods instantGoods) {
        this.instantGoods = instantGoods;
    }

    public Goods getInstantGoods() {
        return instantGoods;
    }

    public void setInstantGoods(Goods instantGoods) {
        this.instantGoods = instantGoods;
    }
}
