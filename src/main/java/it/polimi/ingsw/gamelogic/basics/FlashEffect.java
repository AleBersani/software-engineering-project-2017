package it.polimi.ingsw.gamelogic.basics;

public class FlashEffect {
    /*
    TODO: change to Goods
     */
    protected ExchangingGoods instantGoods;

    public FlashEffect(ExchangingGoods instantGoods) {
        this.instantGoods = instantGoods;
    }

    public ExchangingGoods getInstantGoods() {
        return instantGoods;
    }

    public void setInstantGoods(ExchangingGoods instantGoods) {
        this.instantGoods = instantGoods;
    }
}
