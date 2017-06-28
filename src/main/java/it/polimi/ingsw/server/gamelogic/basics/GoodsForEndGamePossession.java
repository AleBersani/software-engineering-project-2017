package it.polimi.ingsw.server.gamelogic.basics;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GoodsForEndGamePossession that = (GoodsForEndGamePossession) o;
        return getNumberOfObjectsRequired() == that.getNumberOfObjectsRequired() &&
                Objects.equals(getRewards(), that.getRewards());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumberOfObjectsRequired(), getRewards());
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
