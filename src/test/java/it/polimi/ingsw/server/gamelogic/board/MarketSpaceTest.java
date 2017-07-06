package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarketSpaceTest {
    private MarketSpace marketSpace;

    @BeforeEach
    void setUp() {
        marketSpace = new MarketSpace(new Space(BoardIdentifier.M_1, 1), new ExchangingGoods(
                new Goods(new Resources(1,1,0,0))), 4);
    }

    @Test
    void testEqualsTrue1() {
        MarketSpace marketSpaceToConfront = new MarketSpace(new Space(BoardIdentifier.M_1, 1),
                new ExchangingGoods(new Goods(new Resources(1,1,0,0))),4);
        assertTrue(marketSpace.equals(marketSpaceToConfront));
    }

    @Test
    void testEqualsTrue2() {
        MarketSpace marketSpaceToConfront = marketSpace;
        assertTrue(marketSpace.equals(marketSpaceToConfront));
    }

    @Test
    void testEqualsFalse1() {
        MarketSpace marketSpaceToConfront = new MarketSpace(new Space(BoardIdentifier.M_2, 1),
                new ExchangingGoods(new Goods(new Resources(1,1,0,0))),4);
        assertFalse(marketSpace.equals(marketSpaceToConfront));
    }

    @Test
    void testEqualsFalse2() {
        MarketSpace marketSpaceToConfront = new MarketSpace(new Space(BoardIdentifier.M_1, 1),
                new ExchangingGoods(new Goods(new Resources(3,1,0,0))),4);
        assertFalse(marketSpace.equals(marketSpaceToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(marketSpace.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(marketSpace.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        MarketSpace marketSpaceToConfront = new MarketSpace(new Space(BoardIdentifier.M_1, 1),
                new ExchangingGoods(new Goods(new Resources(1,1,0,0))),4);
        assertEquals(marketSpace.hashCode(), marketSpaceToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        MarketSpace marketSpaceToConfront = new MarketSpace(new Space(BoardIdentifier.M_2, 1),
                new ExchangingGoods(new Goods(new Resources(1,1,0,0))),4);
        assertNotEquals(marketSpace.hashCode(), marketSpaceToConfront.hashCode());
    }

    @Test
    void getSpace() {
        Space spaceToConfront = new Space(BoardIdentifier.T_G_3, 3);
        marketSpace.setSpace(spaceToConfront);
        assertEquals(marketSpace.getSpace(), spaceToConfront);
    }

    @Test
    void getExchangingGoods() {
        ExchangingGoods exchangingGoodsToConfront = new ExchangingGoods(new Goods(new Points(1,2,3)));
        marketSpace.setExchangingGoods(exchangingGoodsToConfront);
        assertEquals(marketSpace.getExchangingGoods(), exchangingGoodsToConfront);
    }

    @Test
    void getNumberOfRequiredPlayers() {
        int numberToConfront = 4;
        marketSpace.setNumberOfRequiredPlayers(numberToConfront);
        assertEquals(marketSpace.getNumberOfRequiredPlayers(), numberToConfront);
    }
}