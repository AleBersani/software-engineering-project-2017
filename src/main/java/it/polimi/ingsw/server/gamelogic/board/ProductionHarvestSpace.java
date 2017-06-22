package it.polimi.ingsw.server.gamelogic.board;

import java.util.Objects;

/**
 * Class that describes the spaces where the player can perform the Harvest or the Production Actions
 */
public class ProductionHarvestSpace {
    private Space space;
    private int malusValue;

    public ProductionHarvestSpace(Space space) {
        this.space = space;
        malusValue = 0;
    }

    public ProductionHarvestSpace(Space space, int malusValue) {
        this.space = space;
        this.malusValue = malusValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ProductionHarvestSpace that = (ProductionHarvestSpace) o;
        return getMalusValue() == that.getMalusValue() &&
                Objects.equals(getSpace(), that.getSpace());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSpace(), getMalusValue());
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
}
