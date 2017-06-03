package it.polimi.ingsw.gamelogic.basics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExchangingGoodsTest {
    private ExchangingGoods exchangingGoods;

    @BeforeEach
    void setUp() {
        exchangingGoods = new ExchangingGoods(2);
    }

    @Test
    void testExchangeCouncilsPrivileges() {
        List<Goods> chosenGoods = new ArrayList<>();
        chosenGoods.add(new Goods(new Points(1,2,3)));
        chosenGoods.add(new Goods(new Resources(1,2,3,4)));
        Goods sumOfConvertedGoods = new Goods();
        chosenGoods.forEach(g -> sumOfConvertedGoods.addAll(g));
        Goods convertedGoods = exchangingGoods.exchangeCouncilsPrivileges(chosenGoods);
        assertEquals(sumOfConvertedGoods, convertedGoods);
    }

    @Test
    void testExchangeCouncilsPrivilegesWithWrongListLength() {
        List<Goods> chosenGoods = new ArrayList<>();
        chosenGoods.add(new Goods(new Points(1,2,3)));
        Goods sumOfConvertedGoods = new Goods();
        chosenGoods.forEach(g -> sumOfConvertedGoods.addAll(g));
        assertThrows(IllegalArgumentException.class, ( )-> exchangingGoods.exchangeCouncilsPrivileges(chosenGoods));
    }
}