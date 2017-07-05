package it.polimi.ingsw.client.cli.model;

import it.polimi.ingsw.shared.model.BoardIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardSpaceDescriptionLightTest {
    private BoardSpaceDescriptionLight boardSpaceDescriptionLight;

    @BeforeEach
    void setUp() {
        boardSpaceDescriptionLight = new BoardSpaceDescriptionLight(BoardIdentifier.T_G_4, "4C",
                                                                    2,2);
    }

    @Test
    void testEqualsTrue() {
        BoardSpaceDescriptionLight boardSpaceDescriptionLightToConfront = new BoardSpaceDescriptionLight(
                BoardIdentifier.T_G_4, "4C", 2,2);
        BoardSpaceDescriptionLight boardSpaceDescriptionLightToConfront2 = boardSpaceDescriptionLight;
        assertTrue(boardSpaceDescriptionLightToConfront.equals(boardSpaceDescriptionLight));
        assertTrue(boardSpaceDescriptionLight.equals(boardSpaceDescriptionLightToConfront2));
    }

    @Test
    void testEqualsFalse() {
        BoardSpaceDescriptionLight boardSpaceDescriptionLightToConfront = new BoardSpaceDescriptionLight(
                BoardIdentifier.T_G_3, "3C", 2,2);
        assertFalse(boardSpaceDescriptionLightToConfront.equals(boardSpaceDescriptionLight));
    }

    @Test
    void testEqualsDifferent() {
        String obj = "Hello";
        assertFalse(boardSpaceDescriptionLight.equals(obj));
        assertFalse(boardSpaceDescriptionLight.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        BoardSpaceDescriptionLight boardSpaceDescriptionLightToConfront = new BoardSpaceDescriptionLight(
                BoardIdentifier.T_G_4, "4C",
                2,2);
        assertTrue(boardSpaceDescriptionLight.hashCode() == boardSpaceDescriptionLightToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        BoardSpaceDescriptionLight boardSpaceDescriptionLightToConfront2 = new BoardSpaceDescriptionLight(
                BoardIdentifier.T_G_3, "3C",
                2,2);
        assertFalse(boardSpaceDescriptionLight.hashCode() == boardSpaceDescriptionLightToConfront2.hashCode());
    }

    @Test
    void testGetBoardIdentifier() {
        BoardIdentifier boardIdentifierToConfront = BoardIdentifier.HARVEST_1;
        boardSpaceDescriptionLight.setBoardIdentifier(boardIdentifierToConfront);
        assertEquals(boardIdentifierToConfront, boardSpaceDescriptionLight.getBoardIdentifier());
    }

    @Test
    void testGetBonus() {
        String bonusToConfront = "5C";
        boardSpaceDescriptionLight.setBonus(bonusToConfront);
        assertEquals(bonusToConfront, boardSpaceDescriptionLight.getBonus());
    }

    @Test
    void testGetActionValue() {
        int numberToConfront = 3;
        boardSpaceDescriptionLight.setActionValue(numberToConfront);
        assertEquals(numberToConfront, boardSpaceDescriptionLight.getActionValue());
    }

    @Test
    void testGetMalus() {
        int numberToConfront = 3;
        boardSpaceDescriptionLight.setMalus(numberToConfront);
        assertEquals(numberToConfront, boardSpaceDescriptionLight.getMalus());
    }

}