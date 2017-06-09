package it.polimi.ingsw.gamelogic.cards.development;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;

import java.util.Objects;

/**
 * Class that describes the Territory cards
 */
public class Territory extends DevelopmentCard {
    private int harvestActionValueRequired;
    private ExchangingGoods harvestResult;

    public Territory(BasicDevelopmentCard basicDevelopmentCard, int harvestActionValueRequired, ExchangingGoods harvestResult) {
        super(basicDevelopmentCard);
        this.harvestActionValueRequired = harvestActionValueRequired;
        this.harvestResult = harvestResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Territory territory = (Territory) o;
        return getHarvestActionValueRequired() == territory.getHarvestActionValueRequired() &&
                Objects.equals(getHarvestResult(), territory.getHarvestResult());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getHarvestActionValueRequired(), getHarvestResult());
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
