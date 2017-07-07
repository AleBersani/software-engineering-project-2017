package it.polimi.ingsw.server.gamelogic.basics;

import it.polimi.ingsw.shared.model.GeneralColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardsRequiredTest {
    private CardsRequired cardsRequired;

    @BeforeEach
    void setUp() {
        cardsRequired = new CardsRequired(4, GeneralColor.GREEN);
    }

    @Test
    void testEqualsTrue1() {
        CardsRequired result = new CardsRequired(4, GeneralColor.GREEN);
        assertTrue(cardsRequired.equals(result));
    }

    @Test
    void testEqualsTrue2() {
        CardsRequired result = cardsRequired;
        assertTrue(cardsRequired.equals(result));
    }

    @Test
    void testEqualsFalse1() {
        CardsRequired result = new CardsRequired(6, GeneralColor.GREEN);
        assertFalse(cardsRequired.equals(result));
    }

    @Test
    void testEqualsFalse2() {
        CardsRequired result = new CardsRequired(4, GeneralColor.BLUE);
        assertFalse(cardsRequired.equals(result));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(cardsRequired.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(cardsRequired.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        CardsRequired result = new CardsRequired(4, GeneralColor.GREEN);
        assertEquals(cardsRequired.hashCode(), result.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        CardsRequired result = new CardsRequired(8, GeneralColor.GREEN);
        assertNotEquals(cardsRequired.hashCode(), result.hashCode());
    }

    @Test
    void testGetNumberOfCardsRequired() {
        int resultExpected = 7;
        cardsRequired.setNumberOfCardsRequired(resultExpected);
        assertEquals(resultExpected, cardsRequired.getNumberOfCardsRequired());
    }

    @Test
    void testGetCardColorRequired() {
        GeneralColor resultExpected = GeneralColor.BLUE;
        cardsRequired.setCardColorRequired(resultExpected);
        assertEquals(resultExpected, cardsRequired.getCardColorRequired());
    }
}