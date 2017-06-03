package it.polimi.ingsw.gamelogic.cards.development;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.basics.Goods;

import java.util.List;

/**
 * Class that describes the Venture cards
 */
public class Venture implements Collectible {
    private DevelopmentCard developmentCard;
    private Goods endGameReward;

    public Venture(DevelopmentCard developmentCard, Goods endGameReward) {
        this.developmentCard = developmentCard;
        this.endGameReward = endGameReward;
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
    public ExchangingGoods getInstantEffect() {
        return developmentCard.getInstantExchangingGoods();
    }

    public DevelopmentCard getDevelopmentCard() {
        return developmentCard;
    }

    public void setDevelopmentCard(DevelopmentCard developmentCard) {
        this.developmentCard = developmentCard;
    }

    public Goods getEndGameReward() {
        return endGameReward;
    }

    public void setEndGameReward(Goods endGameReward) {
        this.endGameReward = endGameReward;
    }
}
