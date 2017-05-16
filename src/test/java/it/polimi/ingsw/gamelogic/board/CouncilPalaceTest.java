package it.polimi.ingsw.gamelogic.board;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class CouncilPalaceTest {
    private CouncilPalace councilPalace;

    @Before
    public void setUp() throws Exception {
        councilPalace = new CouncilPalace(new ExchangingGoods(1), 1);
        councilPalace.addToPlayerOrder("Dennis");
        councilPalace.addToPlayerOrder("Fabri");
    }

    @Test
    public void testAddToPlayerOrder() throws Exception {
        councilPalace.addToPlayerOrder("Cami");
        assertEquals("Cami", councilPalace.getPlayerOrder().get(2));
    }

    @Test
    public void testAlreadyAddedToPlayerOrder() throws Exception {
        councilPalace.addToPlayerOrder("Dennis");
        assertEquals(2, councilPalace.getPlayerOrder().stream().count());
    }
}