package it.polimi.ingsw.gamelogic.cards.developmentcards;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.basics.Points;
import it.polimi.ingsw.gamelogic.cards.FlashEffect;

import java.util.List;

public class Venture implements Collectible {
    private DevelopmentCard developmentCard; // Composite object attribute
    private Points endGameReward;

    /*
        TODO Constructor and methods
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
    public FlashEffect getInstantEffect() {
        return developmentCard.getInstantEffect();
    }
}
