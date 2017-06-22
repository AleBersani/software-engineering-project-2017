package it.polimi.ingsw.server.gamelogic.cards.development;

import it.polimi.ingsw.server.gamelogic.basics.Goods;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Venture venture = (Venture) o;
        return Objects.equals(getEndGameReward(), venture.getEndGameReward()) &&
                Objects.equals(getMinCostRequirements(), venture.getMinCostRequirements());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getEndGameReward(), getMinCostRequirements());
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
