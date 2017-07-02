package it.polimi.ingsw.server.gamelogic.board;

import java.util.Objects;

/**
 * Class that describes the spaces where the player can perform the Harvest or the Production Actions
 */
public class ProductionHarvestSpace {
    private Space space;
    private int malusValue;
    private int numberOfRequiredPlayers;

    public ProductionHarvestSpace(Space space, int malusValue) {
        this.space = space;
        this.malusValue = malusValue;
        int numberOfRequiredPlayers = 0;
    }

    public ProductionHarvestSpace(Space space, int malusValue, int numberOfRequiredPlayers) {
        this.space = space;
        this.malusValue = malusValue;
        this.numberOfRequiredPlayers = numberOfRequiredPlayers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ProductionHarvestSpace that = (ProductionHarvestSpace) o;
        return getMalusValue() == that.getMalusValue() &&
                getNumberOfRequiredPlayers() == that.getNumberOfRequiredPlayers() &&
                Objects.equals(getSpace(), that.getSpace());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSpace(), getMalusValue(), getNumberOfRequiredPlayers());
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public int getMalusValue() {
        return malusValue;
    }

    public void setMalusValue(int malusValue) {
        this.malusValue = malusValue;
    }

    public int getNumberOfRequiredPlayers() {
        return numberOfRequiredPlayers;
    }

    public void setNumberOfRequiredPlayers(int numberOfRequiredPlayers) {
        this.numberOfRequiredPlayers = numberOfRequiredPlayers;
    }
}
