package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.server.gamelogic.enums.DiceColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.DirectoryStream;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {
    private Dice dice;

    @BeforeEach
    void setUp() {
        dice = new Dice(DiceColor.BLACK, 3);
    }

    @Test
    void testEquals() {
        Dice diceToConfront = new Dice(DiceColor.BLACK, 3);
        assertTrue(dice.equals(diceToConfront));
    }
}