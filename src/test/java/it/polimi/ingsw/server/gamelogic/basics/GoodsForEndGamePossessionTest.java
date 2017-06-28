package it.polimi.ingsw.server.gamelogic.basics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoodsForEndGamePossessionTest {
    private GoodsForEndGamePossession goodsForEndGamePossession;

    @BeforeEach
    void setUp() {
        goodsForEndGamePossession = new GoodsForEndGamePossession(4,
                new Goods(new Resources(1,0,1,0),
                        new Points(1,0,0)));
    }

    @Test
    void testEquals() {
        GoodsForEndGamePossession result = new GoodsForEndGamePossession(4,
                new Goods(new Resources(1,0,1,0),
                        new Points(1,0,0)));
        assertTrue(goodsForEndGamePossession.equals(result));
    }

    @Test
    void testGetNumberOfObjectsRequired() {
        int result = 4;
        assertTrue(result == goodsForEndGamePossession.getNumberOfObjectsRequired());
    }

    @Test
    void testSetNumberOfObjectsRequired() {
        int previous = goodsForEndGamePossession.getNumberOfObjectsRequired();
        goodsForEndGamePossession.setNumberOfObjectsRequired(3);
        assertFalse(previous == goodsForEndGamePossession.getNumberOfObjectsRequired());
    }

    @Test
    void testGetRewards() {
        Goods rewards = new Goods(new Resources(1,0,1,0),
                new Points(1,0,0));
        assertEquals(rewards, goodsForEndGamePossession.getRewards());
    }

    @Test
    void testSetRewards() {
        Goods previous = goodsForEndGamePossession.getRewards();
        goodsForEndGamePossession.setRewards(new Goods(new Resources(0,0,1,0),
                new Points(1,2,0)));
        assertFalse(previous.equals(goodsForEndGamePossession.getRewards()));
    }
}