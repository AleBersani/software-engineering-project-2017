package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.cards.excommunicationtiles.ExcommunicationTile;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderInformation;
import it.polimi.ingsw.server.gamelogic.enums.DiceColor;
import it.polimi.ingsw.server.gamelogic.enums.LeaderCategory;
import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import it.polimi.ingsw.shared.model.GeneralColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(new ArrayList<>(), new CouncilPalace(new ExchangingGoods(), 1),
                new BoardActionSpaces(new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }

    @Test
    void testEqualsTrue1() {
        Board boardToConfront = new Board(new ArrayList<>(), new CouncilPalace(new ExchangingGoods(), 1),
                new BoardActionSpaces(new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        assertTrue(board.equals(boardToConfront));
    }

    @Test
    void testEqualsTrue2() {
        Board boardToConfront = board;
        assertTrue(board.equals(boardToConfront));
    }

    @Test
    void testEqualsFalse1() {
        Board boardToConfront = new Board(new ArrayList<>(), new CouncilPalace(new ExchangingGoods( new Goods(
                new Points(1,2,3)),1), 1),
                new BoardActionSpaces(new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        assertFalse(board.equals(boardToConfront));
    }

    @Test
    void testEqualsFalse2() {
        List<Tower> towersToAdd = new ArrayList<>();
        towersToAdd.add(new Tower(GeneralColor.GREEN, new ArrayList<>()));
        Board boardToConfront = new Board(towersToAdd, new CouncilPalace(new ExchangingGoods(), 1),
                new BoardActionSpaces(new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        assertFalse(board.equals(boardToConfront));
    }

    @Test
    void testEqualsFalse3() {
        List<ProductionHarvestSpace> productionHarvestSpacesToAdd = new ArrayList<>();
        productionHarvestSpacesToAdd.add(new ProductionHarvestSpace(new Space(BoardIdentifier.T_G_3,
                3), 3));
        Board boardToConfront = new Board(new ArrayList<>(), new CouncilPalace(new ExchangingGoods(), 1),
                new BoardActionSpaces(productionHarvestSpacesToAdd, new ArrayList<>(), new ArrayList<>()));
        assertFalse(board.equals(boardToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(board.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(board.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        Board boardToConfront = new Board(new ArrayList<>(), new CouncilPalace(new ExchangingGoods(), 1),
                new BoardActionSpaces(new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        assertEquals(board.hashCode(), boardToConfront.hashCode());
    }

    @Test
    void testHashCode() {
        Board boardToConfront = new Board(new ArrayList<>(), new CouncilPalace(new ExchangingGoods(), 5),
                new BoardActionSpaces(new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        assertNotEquals(board.hashCode(), boardToConfront.hashCode());
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

    @Test
    void testGetTowers() {
        List<Tower> towersToGet = new ArrayList<>();
        towersToGet.add(new Tower(GeneralColor.GREEN, new ArrayList<>()));
        board.setTowers(towersToGet);
        assertEquals(towersToGet, board.getTowers());
    }

    @Test
    void testGetCouncilPalace() {
        CouncilPalace councilPalaceToGet = new CouncilPalace(new ExchangingGoods(
                new Points(1,2,3), 2), 2);
        board.setCouncilPalace(councilPalaceToGet);
        assertEquals(councilPalaceToGet, board.getCouncilPalace());
    }

    @Test
    void testGetBoardActionSpaces() {
        List<ProductionHarvestSpace> productionHarvestSpacesToAdd = new ArrayList<>();
        productionHarvestSpacesToAdd.add(new ProductionHarvestSpace(new Space(BoardIdentifier.T_G_3,
                3), 3));
        BoardActionSpaces boardActionSpacesToGet = new BoardActionSpaces(productionHarvestSpacesToAdd,
                new ArrayList<>(), new ArrayList<>());
        board.setBoardActionSpaces(boardActionSpacesToGet);
        assertEquals(boardActionSpacesToGet, board.getBoardActionSpaces());
    }

    @Test
    void testGetExcommunicationTiles() {
        List<ExcommunicationTile> excommunicationTilesToGet = new ArrayList<>();
        excommunicationTilesToGet.add(new ExcommunicationTile("1.2", PeriodNumber.FIRST));
        board.setExcommunicationTiles(excommunicationTilesToGet);
        assertEquals(excommunicationTilesToGet, board.getExcommunicationTiles());
    }

    @Test
    void testGetDices() {
        List<Dice> dicesToGet = new ArrayList<>();
        dicesToGet.add(new Dice(DiceColor.BLACK, 2));
        board.setDices(dicesToGet);
        assertEquals(dicesToGet, board.getDices());
    }

    @Test
    void testGetLeaderInformation() {
        List<LeaderInformation> leaderInformationListToGet = new ArrayList<>();
        leaderInformationListToGet.add(new LeaderInformation("Lorenzo", LeaderCategory.PERMANENT));
        board.setLeaderInformationList(leaderInformationListToGet);
        assertEquals(leaderInformationListToGet, board.getLeaderInformationList());
    }
}