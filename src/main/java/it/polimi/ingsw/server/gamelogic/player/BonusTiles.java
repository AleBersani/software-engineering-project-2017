package it.polimi.ingsw.server.gamelogic.player;

import it.polimi.ingsw.server.gamelogic.basics.Goods;

import java.util.Objects;

/**
 * Class that describes a Bonus Tile
 */
public class BonusTiles {
    private Goods productionBonus;
    private Goods harvestBonus;

    public BonusTiles() {
        productionBonus = new Goods();
        harvestBonus = new Goods();
    }

    public BonusTiles(Goods productionBonus, Goods harvestBonus) {
        this.productionBonus = productionBonus;
        this.harvestBonus = harvestBonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BonusTiles that = (BonusTiles) o;
        return Objects.equals(getProductionBonus(), that.getProductionBonus()) &&
                Objects.equals(getHarvestBonus(), that.getHarvestBonus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductionBonus(), getHarvestBonus());
    }

    public Goods getProductionBonus() {
        return productionBonus;
    }

    public void setProductionBonus(Goods productionBonus) {
        this.productionBonus = productionBonus;
    }

    public Goods getHarvestBonus() {
        return harvestBonus;
    }

    public void setHarvestBonus(Goods harvestBonus) {
        this.harvestBonus = harvestBonus;
    }
}
