package it.polimi.ingsw.server.gamelogic.player;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BonusTilesTest {
    private BonusTiles bonusTiles;

    @BeforeEach
    void setUp() {
        bonusTiles = new BonusTiles(new Goods(new Resources(1,2,3,0),
                                                new Points(1,2,3)),
                                    new Goods(new Resources(1,3,4,0)));
    }
    @Test
    void testEquals() {
        BonusTiles expectedBonusTile = new BonusTiles(new Goods(new Resources(1,2,3,0),
                                                                new Points(1,2,3)),
                                                        new Goods(new Resources(1,3,4,0)));
        assertTrue(expectedBonusTile.equals(bonusTiles));
    }

    @Test
    void testGetProductionBonus() {
        Goods goodsToConfront = new Goods(new Resources(4,2,3,9),
                                            new Points(1,4,3));
        bonusTiles.setProductionBonus(goodsToConfront);
        assertEquals(goodsToConfront, bonusTiles.getProductionBonus());
    }

    @Test
    void testGetHarvestBonus() {
        Goods goodsToConfront = new Goods(new Resources(1,2,9,7),
                                            new Points(2,4,6));
        bonusTiles.setHarvestBonus(goodsToConfront);
        assertEquals(goodsToConfront, bonusTiles.getHarvestBonus());
    }

}