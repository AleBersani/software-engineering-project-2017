package it.polimi.ingsw.gamelogic.cards;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.basics.Points;

import java.util.List;

public class Venture extends DevelopmentCard {
    private Points endGamePoints;

    public Venture(CardInformation cardInformation, List<Goods> cost,
                   FlashEffect instantEffect, Points endGamePoints) {
        super(cardInformation, cost, instantEffect);
        this.endGamePoints = endGamePoints;
    }

    public Points getEndGamePoints() {
        return endGamePoints;
    }

    public void setEndGamePoints(Points endGamePoints) {
        this.endGamePoints = endGamePoints;
    }
}
