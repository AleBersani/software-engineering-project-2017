package it.polimi.ingsw.gamelogic.cards.development;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.cards.additionalinfo.AdditionalCardInfo;

import java.util.Optional;

/**
 * Class that describes the Building cards
 */
public class Building extends Collection {
    private int productionActionValueRequired;
    private ExchangingGoods productionResult;
    private AdditionalCardInfo additionalCardInfo;

    public Building(DevelopmentCard developmentCard) {
        super(developmentCard);
        productionActionValueRequired = 0;
        productionResult = new ExchangingGoods();
        additionalCardInfo = null;
    }

    public Building(DevelopmentCard developmentCard, int productionActionValueRequired,
                    ExchangingGoods productionResult) {
        super(developmentCard);
        this.productionActionValueRequired = productionActionValueRequired;
        this.productionResult = productionResult;
        additionalCardInfo = null;
    }

    public Building(DevelopmentCard developmentCard, int productionActionValueRequired,
                    ExchangingGoods productionResult, AdditionalCardInfo additionalCardInfo) {
        super(developmentCard);
        this.productionActionValueRequired = productionActionValueRequired;
        this.productionResult = productionResult;
        this.additionalCardInfo = additionalCardInfo;
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

    /**
     * Optional getter for AdditionalCardInfo
     * @return Optional.empty() if the attribute is null
     */
    public Optional<AdditionalCardInfo> getAdditionalCardInfo() {
        if (additionalCardInfo == null)
            return Optional.empty();
        return Optional.of(additionalCardInfo);
    }

    public void setAdditionalCardInfo(AdditionalCardInfo additionalCardInfo) {
        this.additionalCardInfo = additionalCardInfo;
    }
}
