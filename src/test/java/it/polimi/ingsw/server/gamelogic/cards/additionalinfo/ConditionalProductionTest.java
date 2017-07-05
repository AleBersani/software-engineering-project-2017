package it.polimi.ingsw.server.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.shared.model.GeneralColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConditionalProductionTest {
    private ConditionalProduction conditionalProduction;

    @BeforeEach
    void setUp() {
        conditionalProduction = new ConditionalProduction("", GeneralColor.GREEN);
    }

    @Test
    void testEqualsTrue() {
        ConditionalProduction conditionalProductionToConfront = new ConditionalProduction("", GeneralColor.GREEN);
        assertTrue(conditionalProductionToConfront.equals(conditionalProduction));
    }

    @Test
    void testEqualsFalse() {
        ConditionalProduction conditionalProductionToConfront = new ConditionalProduction("", GeneralColor.YELLOW);
        assertFalse(conditionalProductionToConfront.equals(conditionalProduction));
    }

    @Test
    void testEqualsDifferent() {
        String obj = "";
        assertFalse(conditionalProduction.equals(obj));
    }

    @Test
    void testHashCodeTrue() {
        ConditionalProduction conditionalProductionToConfront = new ConditionalProduction("", GeneralColor.GREEN);
        assertEquals(conditionalProduction.hashCode(), conditionalProductionToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        ConditionalProduction conditionalProductionToConfront = new ConditionalProduction("", GeneralColor.YELLOW);
        assertNotEquals(conditionalProduction.hashCode(), conditionalProductionToConfront.hashCode());
    }

    @Test
    void testGetCardColor() {
        GeneralColor colorToConfront = GeneralColor.PURPLE;
        conditionalProduction.setCardColor(colorToConfront);
        assertEquals(colorToConfront, conditionalProduction.getCardColor());
    }

    @Test
    void testGetName() {
        String name = "Lorenzo";
        conditionalProduction.setName(name);
        assertEquals(name, conditionalProduction.getName());
    }
}