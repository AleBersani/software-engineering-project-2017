package it.polimi.ingsw.server.gamelogic.basics;

import it.polimi.ingsw.shared.model.GeneralColor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardsRequiredTest {
    private CardsRequired cardsRequired;

    @BeforeEach
    void setCardsRequired() {
        cardsRequired = new CardsRequired(4, GeneralColor.GREEN);
    }

    @Test
    void testEquals() {
        CardsRequired result = new CardsRequired(4, GeneralColor.GREEN);
        assertTrue(cardsRequired.equals(result));
    }

    @Test
    void testGetNumberOfCardsRequired() {
        int resultExpected = 4;
        assertEquals(resultExpected, cardsRequired.getNumberOfCardsRequired());
    }

    @Test
    void testSetNumberOfCardsRequired() {
        int previous = cardsRequired.getNumberOfCardsRequired();
        cardsRequired.setNumberOfCardsRequired(5);
        assertFalse(previous == cardsRequired.getNumberOfCardsRequired());
    }

    @Test
    void testGetCardColorRequired() {
        GeneralColor resultExpected = GeneralColor.GREEN;
        assertEquals(resultExpected, cardsRequired.getCardColorRequired());
    }

    @Test
    void testSetCardColorRequired() {
        GeneralColor previous = cardsRequired.getCardColorRequired();
        cardsRequired.setCardColorRequired(GeneralColor.BLUE);
        assertFalse(previous.equals(cardsRequired.getCardColorRequired()));
    }
}