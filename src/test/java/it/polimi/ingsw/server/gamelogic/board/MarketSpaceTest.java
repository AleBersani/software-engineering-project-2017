package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MarketSpaceTest {
    private MarketSpace marketSpace;

    @BeforeEach
    void setUp() {
        marketSpace = new MarketSpace(new Space(BoardIdentifier.M_1, 1), new ExchangingGoods(
                new Goods(new Resources(1,1,0,0))), 4);
    }

    @Test
    void testEquals() {
        MarketSpace marketSpaceToConfront = new MarketSpace(new Space(BoardIdentifier.M_1, 1),
                new ExchangingGoods(new Goods(new Resources(1,1,0,0))),4);
        assertTrue(marketSpace.equals(marketSpaceToConfront));
    }
}