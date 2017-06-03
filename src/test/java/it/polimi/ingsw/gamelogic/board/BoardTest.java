package it.polimi.ingsw.gamelogic.board;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.cards.excommunicationtiles.ExcommunicationTile;
import it.polimi.ingsw.gamelogic.enums.DiceColor;
import it.polimi.ingsw.gamelogic.enums.PeriodNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(new ArrayList<>(), new CouncilPalace(new ExchangingGoods(), 1),
                new BoardActionSpaces(new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }

    @Test
    void testGetDiceValueGivenColor() {
        List<Dice> diceList = new ArrayList<>();
        diceList.add(new Dice(DiceColor.BLACK, 5));
        diceList.add(new Dice(DiceColor.ORANGE, 4));
        diceList.add(new Dice(DiceColor.WHITE, 6));

        board.setDices(diceList);
        int expectedValue = board.getDiceValueGivenColor(DiceColor.ORANGE);

        assertEquals(expectedValue, 4);
    }

    @Test
    void testGetExcommunicationTileGivenPeriod() {
        List<ExcommunicationTile> excommunicationTiles = new ArrayList<>();
        excommunicationTiles.add(new ExcommunicationTile("First", PeriodNumber.FIRST));
        excommunicationTiles.add(new ExcommunicationTile("Second", PeriodNumber.SECOND));
        excommunicationTiles.add(new ExcommunicationTile("Third", PeriodNumber.THIRD));

        board.setExcommunicationTiles(excommunicationTiles);
        String expectedName = board.getExcommunicationTileGivenPeriod(PeriodNumber.THIRD).getExcommunicationTileName();

        assertEquals(expectedName, "Third");
    }
}