package it.polimi.ingsw.gamelogic.cards.development;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;

/**
 * Class that describes the Territory cards
 */
public class Territory extends Collection {
    private int harvestActionValueRequired;
    private ExchangingGoods harvestResult;

    public Territory(DevelopmentCard developmentCard, int harvestActionValueRequired, ExchangingGoods harvestResult) {
        super(developmentCard);
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
