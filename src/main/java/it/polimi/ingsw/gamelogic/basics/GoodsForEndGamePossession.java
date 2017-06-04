package it.polimi.ingsw.gamelogic.basics;

/**
 * TODO: JavaDoc
 */
public class GoodsForEndGamePossession {
    private int numberOfObjectsRequired;
    private Goods rewards;

    public GoodsForEndGamePossession(int numberOfObjectsRequired, Goods rewards) {
        this.numberOfObjectsRequired = numberOfObjectsRequired;
        this.rewards = rewards;
    }

    public int getNumberOfObjectsRequired() {
        return numberOfObjectsRequired;
    }

    public void setNumberOfObjectsRequired(int numberOfObjectsRequired) {
        this.numberOfObjectsRequired = numberOfObjectsRequired;
    }

    public Goods getRewards() {
        return rewards;
    }

    public void setRewards(Goods rewards) {
        this.rewards = rewards;
    }
}
