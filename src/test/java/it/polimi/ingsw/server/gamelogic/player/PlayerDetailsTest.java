package it.polimi.ingsw.server.gamelogic.player;

import it.polimi.ingsw.shared.model.GeneralColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerDetailsTest {
    private PlayerDetails playerDetails;

    @BeforeEach
    void setUp() {
        playerDetails = new PlayerDetails("", GeneralColor.BLUE);
    }

    @Test
    void testEquals() {
        PlayerDetails playerDetailsToConfront = new PlayerDetails("", GeneralColor.BLUE);
        assertEquals(playerDetailsToConfront, playerDetails);
    }

    @Test
    void testGetPlayerName() {
        playerDetails.setPlayerName("Mario");
        assertEquals("Mario", playerDetails.getPlayerName());
    }

    @Test
    void testGetPlayerColor() {
        playerDetails.setPlayerColor(GeneralColor.BLUE);
        assertEquals(GeneralColor.BLUE, playerDetails.getPlayerColor());
    }
}