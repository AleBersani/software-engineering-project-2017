package it.polimi.ingsw.server.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardFlashExchangingGoodsTest {
    private CardFlashExchangingGoods cardFlashExchangingGoods;

    @BeforeEach
    void setUp() {
        cardFlashExchangingGoods = new CardFlashExchangingGoods("", new ExchangingGoods(
                new Goods(new Points(1,2,3)),
                2));
    }

    @Test
    void testEqualsTrue() {
        CardFlashExchangingGoods cardFlashExchangingGoodsToConfront = new CardFlashExchangingGoods("",
                new ExchangingGoods(
                        new Goods(new Points(1,2,3)),
                        2));
        assertTrue(cardFlashExchangingGoodsToConfront.equals(cardFlashExchangingGoods));
    }

    @Test
    void testEqualsFalse() {
        CardFlashExchangingGoods cardFlashExchangingGoodsToConfront = new CardFlashExchangingGoods("",
                new ExchangingGoods(
                        new Goods(new Points(1,5,3)),
                        1));
        assertFalse(cardFlashExchangingGoodsToConfront.equals(cardFlashExchangingGoods));
    }

    @Test
    void testEqualsDifferent() {
        String obj = "";
        assertFalse(cardFlashExchangingGoods.equals(obj));
    }

    @Test
    void testHashCodeTrue() {
        CardFlashExchangingGoods cardFlashExchangingGoodsToConfront = new CardFlashExchangingGoods("",
                new ExchangingGoods(
                        new Goods(new Points(1,2,3)),
                        2));
        assertEquals(cardFlashExchangingGoodsToConfront.hashCode(), cardFlashExchangingGoods.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        CardFlashExchangingGoods cardFlashExchangingGoodsToConfront = new CardFlashExchangingGoods("",
                new ExchangingGoods(
                        new Goods(new Points(3,2,3)),
                        1));
        assertNotEquals(cardFlashExchangingGoodsToConfront.hashCode(), cardFlashExchangingGoods.hashCode());
    }

    @Test
    void testGetExchangingGoods() {
        ExchangingGoods exchangingGoodsToConfront = new ExchangingGoods(
                new Goods(new Resources(1,2,3,4)), 1);
        cardFlashExchangingGoods.setExchangingGoods(exchangingGoodsToConfront);
        assertEquals(exchangingGoodsToConfront, cardFlashExchangingGoods.getExchangingGoods());
    }

    @Test
    void testGetName() {
        String name = "Lorenzo";
        cardFlashExchangingGoods.setName(name);
        assertEquals(name, cardFlashExchangingGoods.getName());
    }

}