package it.polimi.ingsw.server.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoodsBasedOnPossessionsTest {
    private GoodsBasedOnPossessions goodsBasedOnPossessions;

    @BeforeEach
    void setUp() {
        Goods rewards = new Goods(new Points(1,2,3));
        goodsBasedOnPossessions = new GoodsBasedOnPossessions("", rewards,
                "MILITARY", 5 );
    }

    @Test
    void testEqualsTrue() {
        Goods rewards = new Goods(new Points(1,2,3));
        GoodsBasedOnPossessions goodsBasedOnPossessionsToConfront = new GoodsBasedOnPossessions("", rewards,
                "MILITARY", 5 );
        GoodsBasedOnPossessions goodsBasedOnPossessionsToConfront2 = goodsBasedOnPossessions;
        assertTrue(goodsBasedOnPossessions.equals(goodsBasedOnPossessionsToConfront));
        assertTrue(goodsBasedOnPossessions.equals(goodsBasedOnPossessionsToConfront2));
    }

    @Test
    void testEqualsFalse() {
        Goods rewards = new Goods(new Points(4,2,5));
        GoodsBasedOnPossessions goodsBasedOnPossessionsToConfront = new GoodsBasedOnPossessions("", rewards,
                "MILITARY", 5 );
        GoodsBasedOnPossessions goodsBasedOnPossessionsToConfront2 = new GoodsBasedOnPossessions("", rewards,
                "FAITH", 5 );
        GoodsBasedOnPossessions goodsBasedOnPossessionsToConfront3 = new GoodsBasedOnPossessions("", rewards,
                "MILITARY", 3);
        assertFalse(goodsBasedOnPossessions.equals(goodsBasedOnPossessionsToConfront));
        goodsBasedOnPossessionsToConfront.setName("Lorenzo");
        assertFalse(goodsBasedOnPossessions.equals(goodsBasedOnPossessionsToConfront));
        assertFalse(goodsBasedOnPossessions.equals(goodsBasedOnPossessionsToConfront2));
        assertFalse(goodsBasedOnPossessions.equals(goodsBasedOnPossessionsToConfront3));
    }

    @Test
    void testEqualsDifferent() {
        String obj = "";
        assertFalse(goodsBasedOnPossessions.equals(obj));
        assertFalse(goodsBasedOnPossessions.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        Goods rewards = new Goods(new Points(1,2,3));
        GoodsBasedOnPossessions goodsBasedOnPossessionsToConfront = new GoodsBasedOnPossessions("", rewards,
                "MILITARY", 5 );
        assertEquals(goodsBasedOnPossessionsToConfront.hashCode(), goodsBasedOnPossessions.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        Goods rewards = new Goods(new Points(2,2,3));
        GoodsBasedOnPossessions goodsBasedOnPossessionsToConfront = new GoodsBasedOnPossessions("", rewards,
                "MILITARY", 4 );
        assertNotEquals(goodsBasedOnPossessionsToConfront.hashCode(), goodsBasedOnPossessions.hashCode());
    }

    @Test
    void getRewardForPossession() {
        Goods expectedRewards = new Goods(new Resources(1,2,3,4));
        goodsBasedOnPossessions.setRewardForPossession(expectedRewards);
        assertEquals(expectedRewards, goodsBasedOnPossessions.getRewardForPossession());
    }

    @Test
    void getTypeOfObjectRequired() {
        String typeOfObj = "GREEN_CARD";
        goodsBasedOnPossessions.setTypeOfObjectRequired(typeOfObj);
        assertEquals(typeOfObj, goodsBasedOnPossessions.getTypeOfObjectRequired());
    }

    @Test
    void getNumberOfObjectRequired() {
        int numberToConfront = 3;
        goodsBasedOnPossessions.setNumberOfObjectRequired(numberToConfront);
        assertEquals(numberToConfront, goodsBasedOnPossessions.getNumberOfObjectRequired());
    }

    @Test
    void getName() {
        String name = "Lorenzo";
        goodsBasedOnPossessions.setName(name);
        assertEquals(name, goodsBasedOnPossessions.getName());
    }

}