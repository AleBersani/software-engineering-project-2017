package it.polimi.ingsw.gamelogic.board;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.enums.GlobalColor;
import it.polimi.ingsw.gamelogic.player.PlayerDetails;
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
        councilPalace.addToPlayerOrder(new PlayerDetails("#0", "Dennis", GlobalColor.BLUE));
        councilPalace.addToPlayerOrder(new PlayerDetails("#1", "Cami", GlobalColor.GREEN));
    }

    @Test
    public void testAddToPlayerOrder() throws Exception {
        councilPalace.addToPlayerOrder(new PlayerDetails("#2", "Fabri", GlobalColor.YELLOW));
        assertEquals(new PlayerDetails("#2", "Fabri", GlobalColor.YELLOW),
                councilPalace.getPlayerOrder().get(2));
    }

    @Test
    public void testAlreadyAddedToPlayerOrder() throws Exception {
        councilPalace.addToPlayerOrder(new PlayerDetails("#0", "Dennis", GlobalColor.BLUE));
        assertEquals(2, councilPalace.getPlayerOrder().stream().count());
    }
}