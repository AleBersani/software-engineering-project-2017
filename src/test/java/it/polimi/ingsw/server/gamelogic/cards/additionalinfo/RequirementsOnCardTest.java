package it.polimi.ingsw.server.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers.BonusActionValue;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers.DoubleServants;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers.RequirementsModifier;
import it.polimi.ingsw.shared.model.ActionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RequirementsOnCardTest {
    private RequirementsOnCard requirementsOnCard;

    @BeforeEach
    void setUp() {
        requirementsOnCard = new RequirementsOnCard("", new BonusActionValue(new AvailableActions(), 0));
    }

    @Test
    void testEqualsTrue1() {
        RequirementsOnCard requirementsOnCardToConfront = new RequirementsOnCard(
                "", new BonusActionValue(new AvailableActions(), 0));
        assertTrue(requirementsOnCard.equals(requirementsOnCardToConfront));
    }

    @Test
    void testEqualsTrue2() {
        RequirementsOnCard requirementsOnCardToConfront = requirementsOnCard;
        assertTrue(requirementsOnCard.equals(requirementsOnCardToConfront));
    }

    @Test
    void testEqualsFalse1() {
        RequirementsOnCard requirementsOnCardToConfront = new RequirementsOnCard(
                "Lorenzo", new BonusActionValue(new AvailableActions(), 0));
        assertFalse(requirementsOnCard.equals(requirementsOnCardToConfront));
    }

    @Test
    void testEqualsFalse2() {
        RequirementsOnCard requirementsOnCardToConfront = new RequirementsOnCard(
                "", new BonusActionValue(new AvailableActions(), 1));
        assertFalse(requirementsOnCard.equals(requirementsOnCardToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(requirementsOnCard.equals(different));
    }

    @Test
    void testEqualsDifferent() {
        assertFalse(requirementsOnCard.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        RequirementsOnCard requirementsOnCardToConfront = new RequirementsOnCard(
                "", new BonusActionValue(new AvailableActions(), 0));
        assertTrue(requirementsOnCard.hashCode() == requirementsOnCardToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        RequirementsOnCard requirementsOnCardToConfront = new RequirementsOnCard(
                "", new BonusActionValue(new AvailableActions(), 1));
        assertFalse(requirementsOnCard.hashCode() == requirementsOnCardToConfront.hashCode());
    }

    @Test
    void testGetRequirementsModifier() {
        List<RequirementsModifier> requirementsModifiersToGet = new ArrayList<>();
        requirementsModifiersToGet.add(new DoubleServants(new AvailableActions(ActionType.BLUE_TOWER)));
        requirementsOnCard.setRequirementsModifiers(requirementsModifiersToGet);
        assertEquals(requirementsModifiersToGet, requirementsOnCard.getRequirementsModifiers());
    }
}