package it.polimi.ingsw.gamelogic.cards;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.basics.Goods;

import java.util.List;

public class Building extends DevelopmentCard {
    private int productionActionValueRequired;
    private List<ExchangingGoods> productionResult;
    private List<Goods> requirementsForProductionExchange;

    public Building(CardInformation cardInformation, List<Goods> cost, FlashEffect instantEffect,
                    int productionActionValueRequired, List<ExchangingGoods> productionResult,
                    List<Goods> requirementsForProductionExchange) {
        super(cardInformation, cost, instantEffect);
        this.productionActionValueRequired = productionActionValueRequired;
        this.productionResult = productionResult;
        this.requirementsForProductionExchange = requirementsForProductionExchange;
    }

    public int getProductionActionValueRequired() {
        return productionActionValueRequired;
    }

    public void setProductionActionValueRequired(int productionActionValueRequired) {
        this.productionActionValueRequired = productionActionValueRequired;
    }

    public List<ExchangingGoods> getProductionResult() {
        return productionResult;
    }

    public void setProductionResult(List<ExchangingGoods> productionResult) {
        this.productionResult = productionResult;
    }

    public List<Goods> getRequirementsForProductionExchange() {
        return requirementsForProductionExchange;
    }

    public void setRequirementsForProductionExchange(List<Goods> requirementsForProductionExchange) {
        this.requirementsForProductionExchange = requirementsForProductionExchange;
    }
}
