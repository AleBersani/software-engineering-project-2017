package it.polimi.ingsw.gamelogic.player;

import it.polimi.ingsw.gamelogic.basics.Goods;

/**
 * Class that describes a Bonus Tile
 */
public class BonusTiles {
    private Goods productionBonus;
    private Goods harvestBonus;

    public BonusTiles(Goods productionBonus, Goods harvestBonus) {
        this.productionBonus = productionBonus;
        this.harvestBonus = harvestBonus;
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
