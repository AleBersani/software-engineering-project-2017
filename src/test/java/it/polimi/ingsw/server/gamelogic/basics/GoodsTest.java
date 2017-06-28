package it.polimi.ingsw.server.gamelogic.basics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoodsTest {
    private Goods goods;

    @BeforeEach
    void setUp() {
        goods = new Goods(new Resources(1,2,3,0), new Points(1,2,3));
    }

    @Test
    void testEquals() {
        Goods goodsToConfront = new Goods(new Resources(1,2,3,0),
                new Points(1,2,3));
        assertTrue(goods.equals(goodsToConfront));
    }

    @Test
    void testToString() {
        String goodsToString = "Goods{Resources{woods=1, stones=2, servants=3, coins=0}, " +
                "Points{victory=1, military=2, faith=3}}";
        assertEquals(goodsToString, goods.toString());
    }

    @Test
    void testAddAll() {
        Goods goodsToAdd = new Goods(new Resources(1,1,1,1),
                new Points(1,1,1));
        Goods expectedResult = new Goods(new Resources(2,3,4,1),
                new Points(2,3,4));
        goods.addAll(goodsToAdd);
        assertEquals(expectedResult, goods);
    }

    @Test
    void testSubtractAll() {
        Goods goodsToSubtract = new Goods(new Resources(1,1,1,0),
                new Points(1,1,1));
        Goods expctedResult = new Goods(new Resources(0,1,2,0),
                new Points(0,1,2));
        goods.subtractAll(goodsToSubtract);
        assertEquals(expctedResult, goods);
    }

    @Test
    void testTrueIsLessThan() {
        Goods goodsToConfront = new Goods(new Resources(4,4,4,4),
                new Points(4,4,4));
        assertTrue(goods.isLessThan(goodsToConfront));
    }

    @Test
    void testFalseIsLessThan() {
        Goods goodsToConfront = new Goods(new Resources(0,0,0,0),
                new Points(0,0,0));
        assertFalse(goods.isLessThan(goodsToConfront));
    }

    @Test
    void testTrueIsEmpty() {
        Goods goodsToTest = new Goods(new Resources(0,0,0,0),
                new Points(0,0,0));
        assertTrue(goodsToTest.isEmpty());
    }

    @Test
    void testFalseIsEmpty() { goods.isEmpty(); }

}