package it.polimi.ingsw.gamelogic.basics;

import java.util.Objects;

/**
 * TODO: JavaDoc
 */
public class Resources {
    private int woods;
    private int stones;
    private int servants;
    private int coins;

    public Resources() {
        woods = 0;
        stones = 0;
        servants = 0;
        coins = 0;
    }

    public Resources(int woods, int stones, int servants, int coins) {
        this.woods = woods;
        this.stones = stones;
        this.servants = servants;
        this.coins = coins;
    }

    @Override
    public String toString() {
        return "Resources{" +
                "woods=" + woods +
                ", stones=" + stones +
                ", servants=" + servants +
                ", coins=" + coins +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Resources resources = (Resources) o;
        return getWoods() == resources.getWoods() &&
                getStones() == resources.getStones() &&
                getServants() == resources.getServants() &&
                getCoins() == resources.getCoins();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWoods(), getStones(), getServants(), getCoins());
    }

    /**
     * Add other Resources to this instance
     * @param resourcesToAdd added Resources
     */
    public void add(Resources resourcesToAdd) {
        woods += resourcesToAdd.getWoods();
        stones += resourcesToAdd.getStones();
        servants += resourcesToAdd.getServants();
        coins += resourcesToAdd.getCoins();
    }

    /**
     * Subtract other Resources to this instance
     * @param resourcesToSubtract subtracted Resources
     */
    public void subtract(Resources resourcesToSubtract) {
        woods -= resourcesToSubtract.getWoods();
        stones -= resourcesToSubtract.getStones();
        servants -= resourcesToSubtract.getServants();
        coins -= resourcesToSubtract.getCoins();
    }

    /**
     * Check if Resources passed is major than this
     * @param resourcesToConfront Resources to confront
     * @return true if Points to confront is major or equal than this
     */
    public boolean isLessThan(Resources resourcesToConfront) {
        return woods <= resourcesToConfront.getWoods() &&
                stones <= resourcesToConfront.getStones() &&
                servants <= resourcesToConfront.getServants() &&
                coins <= resourcesToConfront.getCoins();
    }

    /**
     * Check if all attributes are 0
     * @return true if all attributes are 0
     */
    public boolean isEmpty() {
        return woods == 0 &&
                stones == 0 &&
                servants == 0 &&
                coins == 0;
    }

    public int getWoods() {
        return woods;
    }

    public void setWoods(int woods) {
        this.woods = woods;
    }

    public int getStones() {
        return stones;
    }

    public void setStones(int stones) {
        this.stones = stones;
    }

    public int getServants() {
        return servants;
    }

    public void setServants(int servants) {
        this.servants = servants;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
