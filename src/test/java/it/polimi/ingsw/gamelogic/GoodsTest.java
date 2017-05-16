package it.polimi.ingsw.gamelogic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsTest {
    private Goods goods;
    private Resources resources;
    private Points points;

    @Before
    public void setUp() throws Exception {
        goods = new Goods(new Resources(1,2,3,4), new Points(1,2,3));
        resources = new Resources(2,4,6,8);
        points = new Points(2,4,6);
    }

    @Test
    public void testAddAll() throws Exception {
        Goods expectedResult = new Goods(resources, points);
        goods.addAll(goods);
        assertEquals(expectedResult, goods);
    }

    @Test
    public void testAddAllWithDoubleParameter() throws Exception {
        Goods expectedResult = new Goods(resources, points);
        goods.addAll(goods.getResources(), goods.getPoints());
        assertEquals(expectedResult, goods);
    }

    @Test
    public void testAddResources() throws Exception {
        Goods expectedResources = new Goods(new Resources(3,6,9,12));
        goods.addResources(resources);
        assertEquals(expectedResources.getResources(), goods.getResources());
    }

    @Test
    public void testAddPoints() throws Exception {
        Goods expectedPoints = new Goods(new Points(3,6,9));
        goods.addPoints(points);
        assertEquals(expectedPoints.getPoints(), goods.getPoints());
    }

    @Test
    public void testSubtractAll() throws Exception {
        Goods expectedResult = new Goods();
        goods.subtractAll(goods);
        assertEquals(expectedResult, goods);
    }

    @Test
    public void testSubtractAllWithDoubleParameter() throws Exception {
        Goods expectedResult = new Goods(new Resources(), new Points());
        goods.subtractAll(resources, points);
        assertEquals(expectedResult, goods);
    }

    @Test
    public void testSubtractResources() throws Exception {
        Goods expectedResources = new Goods(new Resources(0,1,2,3));
        goods.subtractResources(new Resources(1,1,1,1));
        assertEquals(expectedResources.getResources(), goods.getResources());
    }

    @Test
    public void testSubtractPoints() throws Exception {
        Goods expectedPoints = new Goods(new Points(0,1,2));
        goods.subtractPoints(new Points(1,1,1));
        assertEquals(expectedPoints.getPoints(), goods.getPoints());
    }

    @Test
    public void testIsEmptyFalse() throws Exception {
        assertFalse(goods.isEmpty());
    }

    @Test
    public void testIsEmptyTrue() throws Exception {
        Goods emptyGoods = new Goods();
        assertTrue(emptyGoods.isEmpty());
    }

    @Test
    public void testHasNoResourcesFalse() throws Exception {
        assertFalse(goods.hasNoResources());
    }

    @Test
    public void testHasNoResourcesTrue() throws Exception {
        Goods goodsWithNoResources = new Goods(new Points(1,1,1));
        assertTrue(goodsWithNoResources.hasNoResources());
    }

    @Test
    public void testHasNoPointsFalse() throws Exception {
        assertFalse(goods.hasNoPoints());
    }

    @Test
    public void testHasNoPointsTrue() throws Exception {
        Goods goodsWithNoPoints = new Goods(new Resources(1,1,1,1));
        assertTrue(goodsWithNoPoints.hasNoPoints());
    }
}