package it.polimi.ingsw.server.gamelogic.player;

import it.polimi.ingsw.shared.model.GeneralColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDetailsTest {
    private PlayerDetails playerDetails;

    @BeforeEach
    void setUp() {
        playerDetails = new PlayerDetails("", GeneralColor.BLUE);
    }

    @Test
    void testEquals() {
        PlayerDetails playerDetailsToConfront = new PlayerDetails("", GeneralColor.BLUE);
        PlayerDetails playerDetailsToConfront2 = new PlayerDetails("Lorenzo", GeneralColor.BLUE);
        PlayerDetails playerDetailsToConfront3 = playerDetails;
        assertTrue(playerDetailsToConfront.equals(playerDetails));
        assertTrue(playerDetails.equals(playerDetailsToConfront3));
        assertFalse(playerDetails.equals(playerDetailsToConfront2));
        assertFalse(playerDetails.equals(" "));
        assertFalse(playerDetails.equals(null));
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

    @Test
    void testIsEmpty() {
        assertFalse(playerDetails.isEmpty());
        playerDetails.setPlayerColor(GeneralColor.UNDEFINED);
        assertTrue(playerDetails.isEmpty());
    }
}