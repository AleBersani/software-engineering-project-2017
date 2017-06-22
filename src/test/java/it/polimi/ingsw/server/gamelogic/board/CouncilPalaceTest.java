package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.shared.model.GeneralColor;
import it.polimi.ingsw.server.gamelogic.player.PlayerDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CouncilPalaceTest {
    private CouncilPalace councilPalace;

    @BeforeEach
    void setUp() {
        councilPalace = new CouncilPalace(new ExchangingGoods(1), 1);
        councilPalace.addToPlayerOrder(new PlayerDetails("Dennis", GeneralColor.BLUE));
        councilPalace.addToPlayerOrder(new PlayerDetails("Cami", GeneralColor.GREEN));
    }

   @Test
    void testAddToPlayerOrder() {
        councilPalace.addToPlayerOrder(new PlayerDetails("Fabri", GeneralColor.YELLOW));
        assertEquals(new PlayerDetails("Fabri", GeneralColor.YELLOW),
                councilPalace.getPlayerOrder().get(2));
    }

    @Test
    void testAlreadyAddedToPlayerOrder() {
        councilPalace.addToPlayerOrder(new PlayerDetails("Dennis", GeneralColor.BLUE));
        assertEquals(2, councilPalace.getPlayerOrder().stream().count());
    }

}