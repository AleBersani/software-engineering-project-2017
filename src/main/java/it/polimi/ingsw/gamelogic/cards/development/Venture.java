package it.polimi.ingsw.gamelogic.cards.development;

import it.polimi.ingsw.gamelogic.basics.Goods;

import java.util.List;

/**
 * Class that describes the Venture cards
 */
public class Venture extends DevelopmentCard {
    private Goods endGameReward;
    private List<Goods> minCostRequirements;

    public Venture(BasicDevelopmentCard basicDevelopmentCard, Goods endGameReward, List<Goods> minCostRequirements) {
        super(basicDevelopmentCard);
        this.endGameReward = endGameReward;
        this.minCostRequirements = minCostRequirements;
    }

    public Goods getEndGameReward() {
        return endGameReward;
    }

    public void setEndGameReward(Goods endGameReward) {
        this.endGameReward = endGameReward;
    }

    public List<Goods> getMinCostRequirements() {
        return minCostRequirements;
    }

    public void setMinCostRequirements(List<Goods> minCostRequirements) {
        this.minCostRequirements = minCostRequirements;
    }
}
