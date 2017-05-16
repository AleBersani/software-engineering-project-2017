package it.polimi.ingsw.gamelogic.cards;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.basics.Goods;

import java.util.List;

public class Territory extends DevelopmentCard {
    private int harvestActionValueRequired;
    private ExchangingGoods harvestResult;

    public Territory(CardInformation cardInformation, List<Goods> cost,
                     FlashEffect instantEffect, int harvestActionValueRequired,
                     ExchangingGoods harvestResult) {
        super(cardInformation, cost, instantEffect);
        this.harvestActionValueRequired = harvestActionValueRequired;
        this.harvestResult = harvestResult;
    }

    public int getHarvestActionValueRequired() {
        return harvestActionValueRequired;
    }

    public void setHarvestActionValueRequired(int harvestActionValueRequired) {
        this.harvestActionValueRequired = harvestActionValueRequired;
    }

    public ExchangingGoods getHarvestResult() {
        return harvestResult;
    }

    public void setHarvestResult(ExchangingGoods harvestResult) {
        this.harvestResult = harvestResult;
    }
}
