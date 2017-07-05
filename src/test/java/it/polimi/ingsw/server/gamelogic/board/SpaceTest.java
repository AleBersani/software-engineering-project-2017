package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.server.gamelogic.player.PlayerDetails;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import it.polimi.ingsw.shared.model.GeneralColor;
import it.polimi.ingsw.shared.model.PawnColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpaceTest {
    private Space space;

    @BeforeEach
    void setUp() {
        space = new Space(BoardIdentifier.T_G_4, 2);
    }

    @Test
    void testEqualsTrue() {
        Space spaceToConfront = new Space(BoardIdentifier.T_G_4, 2);
        Space spaceToConfront2 = space;
        assertTrue(space.equals(spaceToConfront));
        assertTrue(space.equals(spaceToConfront2));
    }

    @Test
    void testEqualsFalse() {
        Space spaceToConfront = new Space(BoardIdentifier.T_G_3, 2);
        spaceToConfront.setAlreadyTaken(true);
        spaceToConfront.setPlayerPawn(new PlayerPawn(new PlayerDetails("Lorenzo", GeneralColor.BLUE),
                PawnColor.ORANGE));
        assertFalse(spaceToConfront.equals(space));
    }

    @Test
    void testEqualsDifferent() {
        String obj = "";
        assertFalse(space.equals(obj));
        assertFalse(space.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        Space spaceToConfront = new Space(BoardIdentifier.T_G_4, 2);
        assertEquals(spaceToConfront.hashCode(), space.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        Space spaceToConfront = new Space(BoardIdentifier.T_G_3, 2);
        assertNotEquals(spaceToConfront.hashCode(), space.hashCode());
    }

    @Test
    void testGetBoardIdentifier() {
        BoardIdentifier boardIdentifierToConfront = BoardIdentifier.COUNCIL_PALACE;
        space.setBoardIdentifier(boardIdentifierToConfront);
        assertEquals(boardIdentifierToConfront, space.getBoardIdentifier());
    }

    @Test
    void testGetRequestedValue() {
        int numberToConfront = 3;
        space.setRequestedValue(3);
        assertEquals(numberToConfront, space.getRequestedValue());
    }

    @Test
    void testGetPlayerPawn() {
        PlayerDetails playerDetails = new PlayerDetails("", GeneralColor.BLUE);
        PlayerPawn playerPawn = new PlayerPawn(playerDetails, PawnColor.ORANGE);
        space.setPlayerPawn(playerPawn);
        assertEquals(playerPawn, space.getPlayerPawn());
    }

    @Test
    void testIsAlreadyTaken() {
        boolean taken = true;
        space.setAlreadyTaken(taken);
        assertEquals(taken, space.isAlreadyTaken());
    }
}