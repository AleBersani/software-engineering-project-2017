package it.polimi.ingsw.server.gamelogic.cards.additionalinfo;

import com.sun.org.apache.xpath.internal.operations.Mult;
import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultipleProductionTest {
    private MultipleProduction multipleProduction;

    @BeforeEach
    void setUp() {
        List<Goods> costs = new ArrayList<>();
        costs.add(new Goods(new Points(1,3,4)));
        costs.add(new Goods(new Resources(1,2,3,4)));
        List<ExchangingGoods> results = new ArrayList<>();
        results.add(new ExchangingGoods(new Goods(new Points(1,2,3)), 0));
        results.add(new ExchangingGoods(new Goods(), 2));
        multipleProduction = new MultipleProduction("", costs, results);
    }

    @Test
    void testEqualsTrue1() {
        List<Goods> costs = new ArrayList<>();
        costs.add(new Goods(new Points(1,3,4)));
        costs.add(new Goods(new Resources(1,2,3,4)));
        List<ExchangingGoods> results = new ArrayList<>();
        results.add(new ExchangingGoods(new Goods(new Points(1,2,3)), 0));
        results.add(new ExchangingGoods(new Goods(), 2));
        MultipleProduction multipleProductionToConfront = new MultipleProduction("", costs, results);
        assertTrue(multipleProductionToConfront.equals(multipleProduction));
    }

    @Test
    void testEqualsTrue2() {
        MultipleProduction multipleProductionToConfront = multipleProduction;
        assertTrue(multipleProduction.equals(multipleProductionToConfront));
    }

    @Test
    void testEqualsFalse() {
        List<Goods> costs = new ArrayList<>();
        costs.add(new Goods(new Points(1,2,4)));
        costs.add(new Goods(new Resources(1,0,3,4)));
        List<ExchangingGoods> results = new ArrayList<>();
        results.add(new ExchangingGoods(new Goods(new Points(1,5,3)), 0));
        results.add(new ExchangingGoods(new Goods(), 2));
        MultipleProduction multipleProductionToConfront = new MultipleProduction("", costs, results);
        assertFalse(multipleProductionToConfront.equals(multipleProduction));
    }

    @Test
    void testEqualsDifferent1() {
        String obj = "";
        assertFalse(multipleProduction.equals(obj));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(multipleProduction.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        List<Goods> costs = new ArrayList<>();
        costs.add(new Goods(new Points(1,3,4)));
        costs.add(new Goods(new Resources(1,2,3,4)));
        List<ExchangingGoods> results = new ArrayList<>();
        results.add(new ExchangingGoods(new Goods(new Points(1,2,3)), 0));
        results.add(new ExchangingGoods(new Goods(), 2));
        MultipleProduction multipleProductionToConfront = new MultipleProduction("", costs, results);
        assertEquals(multipleProductionToConfront.hashCode(), multipleProduction.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        List<Goods> costs = new ArrayList<>();
        costs.add(new Goods(new Points(1,2,4)));
        costs.add(new Goods(new Resources(1,0,3,4)));
        List<ExchangingGoods> results = new ArrayList<>();
        results.add(new ExchangingGoods(new Goods(new Points(1,5,3)), 0));
        results.add(new ExchangingGoods(new Goods(), 2));
        MultipleProduction multipleProductionToConfront = new MultipleProduction("", costs, results);
        assertNotEquals(multipleProductionToConfront.hashCode(), multipleProduction.hashCode());
    }

    @Test
    void testGetCosts() {
        List<Goods> costs = new ArrayList<>();
        costs.add(new Goods(new Points(3,3,4)));
        costs.add(new Goods(new Resources(1,5,3,2)));
        multipleProduction.setCosts(costs);
        assertEquals(costs, multipleProduction.getCosts());
    }

    @Test
    void testGetResult() {
        List<ExchangingGoods> results = new ArrayList<>();
        results.add(new ExchangingGoods(new Goods(new Points(2,2,3)), 1));
        results.add(new ExchangingGoods(new Goods(new Resources(1,0,0,0)),
                2));
        multipleProduction.setResult(results);
        assertEquals(results, multipleProduction.getResult());
    }

    @Test
    void testGetName() {
        String name = "Lorenzo";
        multipleProduction.setName(name);
        assertEquals(name, multipleProduction.getName());
    }
}