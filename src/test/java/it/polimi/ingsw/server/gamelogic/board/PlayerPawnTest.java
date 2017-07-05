package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.server.gamelogic.player.PlayerDetails;
import it.polimi.ingsw.shared.model.GeneralColor;
import it.polimi.ingsw.shared.model.PawnColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerPawnTest {
    private PlayerPawn playerPawn;

    @BeforeEach
    void setUp() {
        playerPawn = new PlayerPawn(new PlayerDetails("", GeneralColor.BLUE), PawnColor.BLACK);
    }

    @Test
    void testEqualsTrue() {
        PlayerPawn playerPawnToConfront = new PlayerPawn(new PlayerDetails("", GeneralColor.BLUE),
                PawnColor.BLACK);
        PlayerPawn playerPawnToConfront2 = playerPawn;
        assertTrue(playerPawn.equals(playerPawnToConfront));
        assertTrue(playerPawn.equals(playerPawnToConfront2));
    }

    @Test
    void testEqualsFalse() {
        PlayerPawn playerPawnToConfront = new PlayerPawn(new PlayerDetails("", GeneralColor.GREEN),
                PawnColor.WHITE);
        PlayerPawn playerPawnToConfront2 = new PlayerPawn(new PlayerDetails("", GeneralColor.BLUE),
                PawnColor.ORANGE);
        assertFalse(playerPawn.equals(playerPawnToConfront));
        assertFalse(playerPawn.equals(playerPawnToConfront2));
    }

    @Test
    void testEqualsDifferent() {
        String different = "";
        assertFalse(playerPawn.equals(different));
        assertFalse(playerPawn.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        PlayerPawn playerPawnToConfront = new PlayerPawn(new PlayerDetails("", GeneralColor.BLUE),
                PawnColor.BLACK);
        assertTrue(playerPawn.hashCode() == playerPawnToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        PlayerPawn playerPawnToConfront = new PlayerPawn(new PlayerDetails("", GeneralColor.GREEN),
                PawnColor.WHITE);
        assertFalse(playerPawn.hashCode() == playerPawnToConfront.hashCode());
    }

    @Test
    void isEmpty() {
        playerPawn.setPawnColor(PawnColor.UNCOLORED);
        playerPawn.setPlayerDetails(new PlayerDetails());
        assertTrue(playerPawn.isEmpty());
        playerPawn.setPlayerDetails(new PlayerDetails("Lorenzo", GeneralColor.BLUE));
        assertFalse(playerPawn.isEmpty());
        playerPawn.setPawnColor(PawnColor.ORANGE);
        assertFalse(playerPawn.isEmpty());
    }

    @Test
    void getPlayerDetails() {
        PlayerDetails playerDetailsToGet = new PlayerDetails("", GeneralColor.BLUE);
        assertEquals(playerDetailsToGet, playerPawn.getPlayerDetails());
    }

    @Test
    void getPawnColor() {
        PawnColor pawnColorToGet = PawnColor.BLACK;
        assertEquals(pawnColorToGet, playerPawn.getPawnColor());
    }
}