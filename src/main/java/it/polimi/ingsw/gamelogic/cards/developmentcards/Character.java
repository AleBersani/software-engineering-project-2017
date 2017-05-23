package it.polimi.ingsw.gamelogic.cards.developmentcards;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.cards.FlashEffect;
import it.polimi.ingsw.gamelogic.requirements.Requirements;
import it.polimi.ingsw.gamelogic.rewards.Rewards;

import java.util.List;

public class Character implements Collectible {
    private DevelopmentCard developmentCard; // Composite
    private Requirements requirements; // Decorator for Requirements
    private Rewards rewards; // Decorator for Rewards

    /*
        TODO: Constructor and methods
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
