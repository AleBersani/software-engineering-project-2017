package it.polimi.ingsw.gamelogic.cards.developmentcards;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.cards.FlashEffect;

import java.util.ArrayList;
import java.util.List;

public class Building implements Collectible {
    private DevelopmentCard developmentCard; // Composite object attribute
    private int productionActionValueRequired;
    private List<ExchangingGoods> productionResult;
    private List<Goods> requirementsForProductionExchange;
    // ActionDescription

    public Building(DevelopmentCard developmentCard, int productionActionValueRequired,
                    List<ExchangingGoods> productionResult) {
        this.developmentCard = developmentCard;
        this.productionActionValueRequired = productionActionValueRequired;
        this.productionResult = productionResult;
        requirementsForProductionExchange = new ArrayList<>();
    }

    public Building(DevelopmentCard developmentCard, int productionActionValueRequired,
                    List<ExchangingGoods> productionResult, List<Goods> requirementsForProductionExchange) {
        this.developmentCard = developmentCard;
        this.productionActionValueRequired = productionActionValueRequired;
        this.productionResult = productionResult;
        this.requirementsForProductionExchange = requirementsForProductionExchange;
    }

    @Override
    public CardInformation getCardInformation() {
        return developmentCard.getCardInformation();
    }

    @Override
    public List<Goods> getCosts() {
        return developmentCard.getCosts();
    }

    @Override
    public FlashEffect getInstantEffect() {
        return developmentCard.getInstantEffect();
    }

    public DevelopmentCard getDevelopmentCard() {
        return developmentCard;
    }

    public void setDevelopmentCard(DevelopmentCard developmentCard) {
        this.developmentCard = developmentCard;
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
