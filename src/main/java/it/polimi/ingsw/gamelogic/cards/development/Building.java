package it.polimi.ingsw.gamelogic.cards.development;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.basics.Goods;

import java.util.List;

/**
 * TODO: JavaDoc
 */
public class Building implements Collectible {
    private DevelopmentCard developmentCard;
    private ExchangingGoods productionResult;

    public Building(DevelopmentCard developmentCard) {
        this.developmentCard = developmentCard;
        productionResult = new ExchangingGoods();
    }

    /*
    * TODO:
    */

    @Override
    public CardInformation getCardInformation() {
        return developmentCard.getCardInformation();
    }

    @Override
    public List<Goods> getCosts() {
        return developmentCard.getCosts();
    }

    @Override
    public ExchangingGoods getInstantEffect() {
        return developmentCard.getInstantExchangingGoods();
    }
}
