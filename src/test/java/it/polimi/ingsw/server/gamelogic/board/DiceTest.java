package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.server.gamelogic.enums.DiceColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {
    private Dice dice;

    @BeforeEach
    void setUp() {
        dice = new Dice(DiceColor.BLACK, 3);
    }

    @Test
    void testEqualsTrue1() {
        Dice diceToConfront = new Dice(DiceColor.BLACK, 3);
        assertTrue(dice.equals(diceToConfront));
    }

    @Test
    void testEqualsTrue2() {
        Dice diceToConfront = dice;
        assertTrue(dice.equals(diceToConfront));
    }

    @Test
    void testEqualsFalse1() {
        Dice diceToConfront = new Dice(DiceColor.ORANGE, 3);
        assertFalse(dice.equals(diceToConfront));
    }

    @Test
    void testEqualsFalse2() {
        Dice diceToConfront = new Dice(DiceColor.BLACK, 4);
        assertFalse(dice.equals(diceToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(dice.equals(different));
    }

    @Test
    void testEqualsDifferen2() {
        assertFalse(dice.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        Dice diceToConfront = new Dice(DiceColor.BLACK, 3);
        assertEquals(dice.hashCode(), diceToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        Dice diceToConfront = new Dice(DiceColor.ORANGE, 3);
        assertNotEquals(dice.hashCode(), diceToConfront.hashCode());
    }

    @Test
    void testGetDiceColor() {
        DiceColor colorToGet = DiceColor.ORANGE;
        dice.setDiceColor(colorToGet);
        assertEquals(colorToGet, dice.getDiceColor());
    }

    @Test
    void testGetValue() {
        int diceValue = 5;
        dice.setValue(diceValue);
        assertEquals(diceValue, dice.getValue());
    }
}