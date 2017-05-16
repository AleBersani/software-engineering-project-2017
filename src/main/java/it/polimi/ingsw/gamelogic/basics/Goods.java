package it.polimi.ingsw.gamelogic.basics;

import java.util.Objects;

/**
 * This class is a wrapper for the Classes Resources and Points,
 * since in most of the cases we need to work with both of them at the same time.
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

    public void addAll(Goods goodsToAdd) {
        resources.add(goodsToAdd.getResources());
        points.add(goodsToAdd.getPoints());
    }

    public void addAll(Resources resourcesToAdd, Points pointsToAdd) {
        resources.add(resourcesToAdd);
        points.add(pointsToAdd);
    }

    public void addResources(Resources resourcesToAdd) {
        resources.add(resourcesToAdd);
    }

    public void addPoints(Points pointsToAdd) {
        points.add(pointsToAdd);
    }

    public void subtractAll(Goods goodsToSubtract) {
        resources.subtract(goodsToSubtract.getResources());
        points.subtract(goodsToSubtract.getPoints());
    }

    public void subtractAll(Resources resourcesToSubtract, Points pointsToSubtract) {
        resources.subtract(resourcesToSubtract);
        points.subtract(pointsToSubtract);
    }

    public void subtractResources(Resources resourcesToSubtract) {
        resources.subtract(resourcesToSubtract);
    }

    public void subtractPoints(Points pointsToSubtract) {
        points.subtract(pointsToSubtract);
    }

    public boolean isEmpty() {
        return resources.isEmpty() &&
                points.isEmpty();
    }

    public boolean hasNoResources() {
        return resources.isEmpty();
    }

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
