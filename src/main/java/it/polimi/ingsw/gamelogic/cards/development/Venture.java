package it.polimi.ingsw.gamelogic.cards.development;

import it.polimi.ingsw.gamelogic.basics.Goods;

/**
 * Class that describes the Venture cards
 */
public class Venture extends Collection {
    private Goods endGameReward;

    public Venture(DevelopmentCard developmentCard, Goods endGameReward) {
        super(developmentCard);
        this.endGameReward = endGameReward;
    }

    public Goods getEndGameReward() {
        return endGameReward;
    }

    public void setEndGameReward(Goods endGameReward) {
        this.endGameReward = endGameReward;
    }
}
