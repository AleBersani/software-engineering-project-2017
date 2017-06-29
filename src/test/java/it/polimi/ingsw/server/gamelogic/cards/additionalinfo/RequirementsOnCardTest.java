package it.polimi.ingsw.server.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers.BonusActionValue;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers.NoMarketPlacement;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers.RequirementsModifier;
import it.polimi.ingsw.shared.model.ActionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RequirementsOnCardTest {
    private RequirementsOnCard requirementsOnCard;

    @BeforeEach
    void setUp() {
        requirementsOnCard = new RequirementsOnCard("", new BonusActionValue(new AvailableActions(), 0));
    }

    @Test
    void testEqualsTrue() {
        RequirementsOnCard requirementsOnCardToConfront = new RequirementsOnCard(
                "", new BonusActionValue(new AvailableActions(), 0));
        assertTrue(requirementsOnCard.equals(requirementsOnCardToConfront));
    }

    @Test
    void testEqualsFalse() {
        RequirementsOnCard requirementsOnCardToConfront = new RequirementsOnCard(
                "", new BonusActionValue(new AvailableActions(), 1));
        assertFalse(requirementsOnCard.equals(requirementsOnCardToConfront));
    }

    @Test
    void testEqualsDifferent() {
        String different = "";
        assertFalse(requirementsOnCard.equals(different));
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
}