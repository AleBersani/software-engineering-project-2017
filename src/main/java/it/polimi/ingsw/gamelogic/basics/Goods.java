package it.polimi.ingsw.gamelogic.basics;

import java.util.Objects;

/**
 * Composition of Resources and Points
 */
public class Goods {
    private Resources resources;
    private Points points;

    public Goods() {
        resources = new Resources();
        points = new Points();
    }

    /**
     * Constructor with only Resources, set points as default (0)
     * @param resources resources to set
     */
    public Goods(Resources resources) {
        this.resources = resources;
        points = new Points();
    }

    /**
     * Constructor with only Points, set resources as default (0)
     * @param points points to set
     */
    public Goods(Points points) {
        resources = new Resources();
        this.points = points;
    }

    public Goods(Resources resources, Points points) {
        this.resources = resources;
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Objects.equals(getResources(), goods.getResources()) &&
                Objects.equals(getPoints(), goods.getPoints());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResources(), getPoints());
    }

    /**
     * Add Goods to the instance
     * @param goodsToAdd added Goods
     */
    public void addAll(Goods goodsToAdd) {
        addAll(goodsToAdd.getResources(), goodsToAdd.getPoints());
    }

    /**
     * Add Resources and Points objects to the instance
     * @param resourcesToAdd added Resources
     * @param pointsToAdd added Points
     */
    public void addAll(Resources resourcesToAdd, Points pointsToAdd) {
        addResources(resourcesToAdd);
        addPoints(pointsToAdd);
    }

    /**
     * Add only Resources
     * @param resourcesToAdd added Resources
     */
    public void addResources(Resources resourcesToAdd) {
        resources.add(resourcesToAdd);
    }

    /**
     * Add only Points
     * @param pointsToAdd added Points
     */
    public void addPoints(Points pointsToAdd) {
        points.add(pointsToAdd);
    }

    /**
     * Subtract Goods to the instance
     * @param goodsToSubtract subtracted Goods
     */
    public void subtractAll(Goods goodsToSubtract) {
        subtractAll(goodsToSubtract.getResources(), goodsToSubtract.getPoints());
    }

    /**
     * Subtract Resources and Points to the instance
     * @param resourcesToSubtract subtracted Resources
     * @param pointsToSubtract subtracted Points
     */
    public void subtractAll(Resources resourcesToSubtract, Points pointsToSubtract) {
        subtractResources(resourcesToSubtract);
        subtractPoints(pointsToSubtract);
    }

    /**
     * Subtract only Resources
     * @param resourcesToSubtract subtracted Resources
     */
    public void subtractResources(Resources resourcesToSubtract) {
        resources.subtract(resourcesToSubtract);
    }

    /**
     * Subtract only Points
     * @param pointsToSubtract subtracted Points
     */
    public void subtractPoints(Points pointsToSubtract) {
        points.subtract(pointsToSubtract);
    }

    /**
     * Auxiliary method
     * @return true if resources and Points are empty
     */
    public boolean isEmpty() {
        return hasNoResources() &&
                hasNoPoints();
    }

    /**
     * Auxiliary method
     * @return true if resources is empty
     */
    public boolean hasNoResources() {
        return resources.isEmpty();
    }

    /**
     * Auxiliary method
     * @return true if points is empty
     */
    public boolean hasNoPoints() {
        return points.isEmpty();
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }
}
