package it.polimi.ingsw.gamelogic.cards.development;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.cards.additionalinfo.AdditionalCardInfo;

import java.util.Optional;

/**
 * Class that describes the Building cards
 */
public class Building extends DevelopmentCard {
    private int productionActionValueRequired;
    private ExchangingGoods productionResult;
    private AdditionalCardInfo additionalCardInfo;

    /**
     * TODO: JavaDoc
     * @param basicDevelopmentCard
     */
    public Building(BasicDevelopmentCard basicDevelopmentCard) {
        super(basicDevelopmentCard);
        productionActionValueRequired = 0;
        productionResult = new ExchangingGoods();
        additionalCardInfo = null;
    }

    /**
     * TODO: JavaDoc
     * @param basicDevelopmentCard
     * @param productionActionValueRequired
     * @param productionResult
     */
    public Building(BasicDevelopmentCard basicDevelopmentCard, int productionActionValueRequired,
                    ExchangingGoods productionResult) {
        super(basicDevelopmentCard);
        this.productionActionValueRequired = productionActionValueRequired;
        this.productionResult = productionResult;
        additionalCardInfo = null;
    }

    /**
     * TODO: JavaDoc
     * @param basicDevelopmentCard
     * @param productionActionValueRequired
     * @param productionResult
     * @param additionalCardInfo
     */
    public Building(BasicDevelopmentCard basicDevelopmentCard, int productionActionValueRequired,
                    ExchangingGoods productionResult, AdditionalCardInfo additionalCardInfo) {
        super(basicDevelopmentCard);
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
