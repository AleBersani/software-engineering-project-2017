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
    void testEqualsTrue1() {
        GoodsForEndGamePossession result = new GoodsForEndGamePossession(4,
                new Goods(new Resources(1,0,1,0),
                        new Points(1,0,0)));
        assertTrue(goodsForEndGamePossession.equals(result));
    }

    @Test
    void testEqualsTrue2() {
        GoodsForEndGamePossession result = goodsForEndGamePossession;
        assertTrue(goodsForEndGamePossession.equals(result));
    }

    @Test
    void testEqualsFalse1() {
        GoodsForEndGamePossession result = new GoodsForEndGamePossession(4,
                new Goods(new Points(1,0,0)));
        assertFalse(goodsForEndGamePossession.equals(result));
    }

    @Test
    void testEqualsFalse2() {
        GoodsForEndGamePossession result = new GoodsForEndGamePossession(5,
                new Goods(new Points(1,0,0)));
        assertFalse(goodsForEndGamePossession.equals(result));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(goodsForEndGamePossession.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(goodsForEndGamePossession.equals(null));
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