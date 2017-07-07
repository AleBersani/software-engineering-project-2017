package it.polimi.ingsw.server.gamelogic.basics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExchangingGoodsTest {
    private ExchangingGoods exchangingGoods;

    @BeforeEach
    void setUp() {
        exchangingGoods = new ExchangingGoods(2);
    }

    @Test
    void testEquals1() {
        ExchangingGoods exchangingGoodsToConfront = new ExchangingGoods(2);
        assertTrue(exchangingGoods.equals(exchangingGoodsToConfront));
    }

    @Test
    void testEquals2() {
        ExchangingGoods exchangingGoodsToConfront = exchangingGoods;
        assertTrue(exchangingGoods.equals(exchangingGoodsToConfront));
    }

    @Test
    void testEqualsFalse1() {
        ExchangingGoods exchangingGoodsToConfront = new ExchangingGoods(1);
        assertFalse(exchangingGoods.equals(exchangingGoodsToConfront));
    }

    @Test
    void testEqualsFalse2() {
        ExchangingGoods exchangingGoodsToConfront = new ExchangingGoods(new Goods(new Points(1,2,3)));
        assertFalse(exchangingGoods.equals(exchangingGoodsToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(exchangingGoods.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(exchangingGoods.equals(null));
    }

    @Test
    void testExchangeCouncilsPrivileges() {
        List<Goods> chosenGoods = new ArrayList<>();
        chosenGoods.add(new Goods(new Points(1,2,3)));
        chosenGoods.add(new Goods(new Resources(1,2,3,4)));
        Goods sumOfConvertedGoods = new Goods();
        chosenGoods.forEach(sumOfConvertedGoods::addAll);
        Goods convertedGoods = exchangingGoods.exchangeCouncilsPrivileges(chosenGoods);
        assertEquals(sumOfConvertedGoods, convertedGoods);
    }

    @Test
    void testExchangeCouncilsPrivilegesWithWrongListLength() {
        List<Goods> chosenGoods = new ArrayList<>();
        chosenGoods.add(new Goods(new Points(1,2,3)));
        Goods sumOfConvertedGoods = new Goods();
        chosenGoods.forEach(sumOfConvertedGoods::addAll);
        assertThrows(IllegalArgumentException.class, ( )-> exchangingGoods.exchangeCouncilsPrivileges(chosenGoods));
    }

    @Test
    void testGetResources() {
        Resources resourcesToGet = new Resources(1,2,3,4);
        exchangingGoods.getGoods().setResources(resourcesToGet);
        assertEquals(resourcesToGet, exchangingGoods.getResources());
    }

    @Test
    void testGetPoints() {
        Points pointsToGet = new Points(1,2,3);
        exchangingGoods.getGoods().setPoints(pointsToGet);
        assertEquals(pointsToGet, exchangingGoods.getPoints());
    }

    @Test
    void testGetGoods() {
        Goods goodsToGet = new Goods(new Points(3,2,1));
        exchangingGoods.setGoods(goodsToGet);
        assertEquals(goodsToGet, exchangingGoods.getGoods());
    }

    @Test
    void testGetNumberOfCouncilPrivilege() {
        int numberToGet = 5;
        exchangingGoods.setNumberOfCouncilPrivilege(numberToGet);
        assertEquals(numberToGet, exchangingGoods.getNumberOfCouncilPrivilege());
    }
}