package it.polimi.ingsw.gamelogic.cards;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;

public class FlashEffect {
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
