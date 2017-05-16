package it.polimi.ingsw.gamelogic.cards;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.basics.Points;

import java.util.List;

public class Venture extends DevelopmentCard {
    private Goods endGameReward;

    public Venture(CardInformation cardInformation, List<Goods> cost, FlashEffect instantEffect, Goods endGameReward) {
        super(cardInformation, cost, instantEffect);
        this.endGameReward = endGameReward;
    }

    public Goods getEndGameReward() {
        return endGameReward;
    }

    public void setEndGameReward(Goods endGameReward) {
        this.endGameReward = endGameReward;
    }
}
