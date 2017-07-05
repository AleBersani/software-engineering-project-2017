package it.polimi.ingsw.server.gamelogic.player;

import it.polimi.ingsw.shared.model.PawnColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {
    private Pawn pawn;

    @BeforeEach
    void setUp() {
        pawn = new Pawn(2, PawnColor.ORANGE);
    }

    @Test
    void testEquals() {
        Pawn expectedPawn = new Pawn(2, PawnColor.ORANGE);
        Pawn expectedPawn2 = pawn;
        assertTrue(expectedPawn.equals(pawn));
        assertTrue(expectedPawn2.equals(pawn));
        assertFalse(pawn.equals(null));
        assertFalse(pawn.equals(" "));
    }

    @Test
    void testGetValue() {
        int expectedValue = 4;
        pawn.setValue(expectedValue);
        assertEquals(expectedValue, pawn.getValue());
    }

    @Test
    void testGetPawnColor() {
        PawnColor color = PawnColor.BLACK;
        pawn.setPawnColor(color);
        assertEquals(color, pawn.getPawnColor());
    }

    @Test
    void testIsPlacedOnBoard() {
        pawn.setPlacedOnBoard(true);
        assertEquals(true, pawn.isPlacedOnBoard());
    }

}