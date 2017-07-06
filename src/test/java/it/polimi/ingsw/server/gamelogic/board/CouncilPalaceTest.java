package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.server.gamelogic.player.PlayerDetails;
import it.polimi.ingsw.shared.model.GeneralColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CouncilPalaceTest {
    private CouncilPalace councilPalace;

    @BeforeEach
    void setUp() {
        councilPalace = new CouncilPalace(new ExchangingGoods(1), 1);
        councilPalace.addToPlayerOrder(new PlayerDetails("Dennis", GeneralColor.BLUE));
        councilPalace.addToPlayerOrder(new PlayerDetails("Cami", GeneralColor.GREEN));
    }

    @Test
    void testEqualsTrue1() {
        CouncilPalace councilPalaceToConfront = new CouncilPalace(
                new ExchangingGoods(1), 1);
        councilPalaceToConfront.addToPlayerOrder(new PlayerDetails("Dennis", GeneralColor.BLUE));
        councilPalaceToConfront.addToPlayerOrder(new PlayerDetails("Cami", GeneralColor.GREEN));
        assertTrue(councilPalace.equals(councilPalaceToConfront));
    }

    @Test
    void testEqualsTrue2() {
        CouncilPalace councilPalaceToConfront = councilPalace;
        assertTrue(councilPalace.equals(councilPalaceToConfront));
    }

    @Test
    void testEqualsFalse1() {
        CouncilPalace councilPalaceToConfront = new CouncilPalace(
                new ExchangingGoods(5), 1);
        councilPalaceToConfront.addToPlayerOrder(new PlayerDetails("Dennis", GeneralColor.BLUE));
        councilPalaceToConfront.addToPlayerOrder(new PlayerDetails("Cami", GeneralColor.GREEN));
        assertFalse(councilPalace.equals(councilPalaceToConfront));
    }

    @Test
    void testEqualsFalse2() {
        CouncilPalace councilPalaceToConfront = new CouncilPalace(
                new ExchangingGoods(1), 2);
        councilPalaceToConfront.addToPlayerOrder(new PlayerDetails("Dennis", GeneralColor.BLUE));
        councilPalaceToConfront.addToPlayerOrder(new PlayerDetails("Cami", GeneralColor.GREEN));
        assertFalse(councilPalace.equals(councilPalaceToConfront));
    }

    @Test
    void testEqualsFalse3() {
        CouncilPalace councilPalaceToConfront = new CouncilPalace(
                new ExchangingGoods(1), 1);
        councilPalaceToConfront.addToPlayerOrder(new PlayerDetails("Dennis", GeneralColor.BLUE));
        councilPalaceToConfront.addToPlayerOrder(new PlayerDetails("Lorenzo", GeneralColor.GREEN));
        assertFalse(councilPalace.equals(councilPalaceToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(councilPalace.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(councilPalace.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        CouncilPalace councilPalaceToConfront = new CouncilPalace(
                new ExchangingGoods(1), 1);
        councilPalaceToConfront.addToPlayerOrder(new PlayerDetails("Dennis", GeneralColor.BLUE));
        councilPalaceToConfront.addToPlayerOrder(new PlayerDetails("Cami", GeneralColor.GREEN));
        assertEquals(councilPalace.hashCode(), councilPalaceToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        CouncilPalace councilPalaceToConfront = new CouncilPalace(
                new ExchangingGoods(1), 1);
        councilPalaceToConfront.addToPlayerOrder(new PlayerDetails("Lorenzo", GeneralColor.BLUE));
        councilPalaceToConfront.addToPlayerOrder(new PlayerDetails("Cami", GeneralColor.GREEN));
        assertNotEquals(councilPalace.hashCode(), councilPalaceToConfront.hashCode());
    }

   @Test
    void testAddToPlayerOrder() {
        councilPalace.addToPlayerOrder(new PlayerDetails("Fabri", GeneralColor.YELLOW));
        assertEquals(new PlayerDetails("Fabri", GeneralColor.YELLOW),
                councilPalace.getPlayerOrder().get(2));
    }

    @Test
    void testAlreadyAddedToPlayerOrder() {
        councilPalace.addToPlayerOrder(new PlayerDetails("Dennis", GeneralColor.BLUE));
        assertEquals(2, councilPalace.getPlayerOrder().stream().count());
    }
}