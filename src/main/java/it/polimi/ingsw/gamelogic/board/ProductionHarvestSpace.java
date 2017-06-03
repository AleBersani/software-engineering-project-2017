package it.polimi.ingsw.gamelogic.board;

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
