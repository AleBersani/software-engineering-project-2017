package it.polimi.ingsw.gamelogic;

public class FlashEffect {
    private ExchangingGoods instantGoods;

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
