package it.polimi.ingsw.server.gamelogic.basics;

/**
 * Class that represents the points assigned to the player at the end of the game counting the player's Goods
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
