package it.polimi.ingsw.server.gamelogic.cards.development;

import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;
import it.polimi.ingsw.shared.model.GeneralColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardInformationTest {
    private CardInformation cardInformation;

    @BeforeEach
    void setUp() {
        cardInformation = new CardInformation(3, "Zecca", PeriodNumber.FIRST, GeneralColor.GREEN);
    }

    @Test
    void testEquals() {
        CardInformation cardInformationToConfront = new CardInformation(3, "Zecca",
                PeriodNumber.FIRST, GeneralColor.GREEN);
        assertTrue(cardInformationToConfront.equals(cardInformation));
    }

    @Test
    void testGetNumber() {
        int numberToConfront = 4;
        cardInformation.setNumber(numberToConfront);
        assertEquals(numberToConfront, cardInformation.getNumber());
    }

    @Test
    void testGetName() {
        String nameToConfront = "Bosco";
        cardInformation.setName(nameToConfront);
        assertEquals(nameToConfront, cardInformation.getName());
    }

    @Test
    void testGetPeriod() {
        PeriodNumber periodNumberToConfront = PeriodNumber.THIRD;
        cardInformation.setPeriod(periodNumberToConfront);
        assertEquals(periodNumberToConfront, cardInformation.getPeriod());
    }

    @Test
    void testGetCardColor() {
        GeneralColor colorToConfront = GeneralColor.BLUE;
        cardInformation.setCardColor(colorToConfront);
        assertEquals(colorToConfront, cardInformation.getCardColor());
    }

}