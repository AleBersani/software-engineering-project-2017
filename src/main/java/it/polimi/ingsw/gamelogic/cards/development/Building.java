package it.polimi.ingsw.gamelogic.cards.development;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;

import java.util.Objects;

/**
 * Class that describes the Building cards
 */
public class Building extends DevelopmentCard {
    private int productionActionValueRequired;
    private ExchangingGoods productionResult;

    /**
     * Constructor with production that set the required value for the production as default (0) and the
     * addtional card information to null
     * @param basicDevelopmentCard
     */
    public Building(BasicDevelopmentCard basicDevelopmentCard) {
        super(basicDevelopmentCard);
        productionActionValueRequired = 0;
        productionResult = new ExchangingGoods();
    }

    /**
     * Constructor with the value required to perform the production action and the production result, additional
     * card info is set as null
     * @param basicDevelopmentCard card
     * @param productionActionValueRequired value requested to perform the production
     * @param productionResult result of the production
     */
    public Building(BasicDevelopmentCard basicDevelopmentCard, int productionActionValueRequired,
                    ExchangingGoods productionResult) {
        super(basicDevelopmentCard);
        this.productionActionValueRequired = productionActionValueRequired;
        this.productionResult = productionResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Building building = (Building) o;
        return getProductionActionValueRequired() == building.getProductionActionValueRequired() &&
                Objects.equals(getProductionResult(), building.getProductionResult());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getProductionActionValueRequired(), getProductionResult());
    }

    public int getProductionActionValueRequired() {
        return productionActionValueRequired;
    }

    public void setProductionActionValueRequired(int productionActionValueRequired) {
        this.productionActionValueRequired = productionActionValueRequired;
    }

    public ExchangingGoods getProductionResult() {
        return productionResult;
    }

    public void setProductionResult(ExchangingGoods productionResult) {
        this.productionResult = productionResult;
    }
}
