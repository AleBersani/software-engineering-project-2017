package it.polimi.ingsw.server.gamelogic.player;

import it.polimi.ingsw.server.gamelogic.basics.Goods;

import java.util.Objects;

/**
 * Class that describes a Bonus Tile
 */
public class BonusTiles {
    private String bonusTileIdentifier;
    private Goods productionBonus;
    private Goods harvestBonus;

    public BonusTiles() {
        bonusTileIdentifier = "";
        productionBonus = new Goods();
        harvestBonus = new Goods();
    }

    public BonusTiles(Goods productionBonus, Goods harvestBonus) {
        this.productionBonus = productionBonus;
        this.harvestBonus = harvestBonus;
        bonusTileIdentifier = "";
    }

    public BonusTiles(String bonusTileIdentifier, Goods productionBonus, Goods harvestBonus) {
        this.bonusTileIdentifier = bonusTileIdentifier;
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
        return Objects.equals(bonusTileIdentifier, that.bonusTileIdentifier) &&
                Objects.equals(getProductionBonus(), that.getProductionBonus()) &&
                Objects.equals(getHarvestBonus(), that.getHarvestBonus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonusTileIdentifier, getProductionBonus(), getHarvestBonus());
    }

    public void setBonusTile(BonusTiles bonusTile) {
        bonusTileIdentifier = bonusTile.getBonusTileIdentifier();
        productionBonus = bonusTile.getProductionBonus();
        harvestBonus = bonusTile.getHarvestBonus();
    }

    public String getBonusTileIdentifier() {
        return bonusTileIdentifier;
    }

    public void setBonusTileIdentifier(String bonusTileIdentifier) {
        this.bonusTileIdentifier = bonusTileIdentifier;
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
