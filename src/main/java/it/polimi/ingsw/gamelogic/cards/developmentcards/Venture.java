package it.polimi.ingsw.gamelogic.cards.developmentcards;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.basics.Points;
import it.polimi.ingsw.gamelogic.cards.FlashEffect;

import java.util.List;

public class Venture implements Collectible {
    private DevelopmentCard developmentCard; // Composite object attribute
    private Points endGameReward;

    public Venture(DevelopmentCard developmentCard, Points endGameReward) {
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
    public FlashEffect getInstantEffect() {
        return developmentCard.getInstantEffect();
    }

    public DevelopmentCard getDevelopmentCard() {
        return developmentCard;
    }

    public void setDevelopmentCard(DevelopmentCard developmentCard) {
        this.developmentCard = developmentCard;
    }

    public Points getEndGameReward() {
        return endGameReward;
    }

    public void setEndGameReward(Points endGameReward) {
        this.endGameReward = endGameReward;
    }
}
