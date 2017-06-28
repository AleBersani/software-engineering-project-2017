package it.polimi.ingsw.server.gamelogic.basics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoodsForEndGamePossessionTest {
    private GoodsForEndGamePossession goodsForEndGamePossession;

    @BeforeEach
    void setGoodsForEndGamePossession() {
        goodsForEndGamePossession = new GoodsForEndGamePossession(4,
                new Goods(new Resources(1,0,1,0),
                        new Points(1,0,0)));
    }


    @Test
    void equals() {
        GoodsForEndGamePossession result = new GoodsForEndGamePossession(4,
                new Goods(new Resources(1,0,1,0),
                        new Points(1,0,0)));
        assertTrue(goodsForEndGamePossession.equals(result));
    }

    @Test
    void getNumberOfObjectsRequired() {
        int result = 4;
        assertTrue(result == goodsForEndGamePossession.getNumberOfObjectsRequired());
    }

    @Test
    void setNumberOfObjectsRequired() {
        int previous = goodsForEndGamePossession.getNumberOfObjectsRequired();
        goodsForEndGamePossession.setNumberOfObjectsRequired(3);
        assertFalse(previous == goodsForEndGamePossession.getNumberOfObjectsRequired());
    }

    @Test
    void getRewards() {
        Goods rewards = new Goods(new Resources(1,0,1,0),
                new Points(1,0,0));
        assertEquals(rewards, goodsForEndGamePossession.getRewards());
    }

    @Test
    void setRewards() {
        Goods previous = goodsForEndGamePossession.getRewards();
        goodsForEndGamePossession.setRewards(new Goods(new Resources(0,0,1,0),
                new Points(1,2,0)));
        assertFalse(previous.equals(goodsForEndGamePossession.getRewards()));
    }

}