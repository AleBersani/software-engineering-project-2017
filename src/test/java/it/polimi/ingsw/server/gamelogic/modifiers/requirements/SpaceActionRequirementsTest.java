package it.polimi.ingsw.server.gamelogic.modifiers.requirements;

import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.PawnColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by camilladottino on 29/06/17.
 */
class SpaceActionRequirementsTest {
    private SpaceActionRequirements spaceActionRequirements;

    @BeforeEach
    void setUp() {
        spaceActionRequirements = new SpaceActionRequirements(ActionType.HARVEST, PawnColor.BLACK, 1,
                1, 0, false);
    }

    @Test
    void testEqualsTrue() {
        SpaceActionRequirements spaceActionRequirementsToConfront = new SpaceActionRequirements(ActionType.HARVEST,
                PawnColor.BLACK, 1,1, 0, false);
        assertTrue(spaceActionRequirements.equals(spaceActionRequirementsToConfront));
    }

    @Test
    void testEqualsFalse() {
        SpaceActionRequirements spaceActionRequirementsToConfront = new SpaceActionRequirements(ActionType.BLUE_TOWER,
                PawnColor.WHITE, 1,1, 0, false);
        assertFalse(spaceActionRequirements.equals(spaceActionRequirementsToConfront));
    }

    @Test
    void testEqualsDifferent() {
        String different = "";
        assertFalse(spaceActionRequirements.equals(different));
    }

    @Test
    void testHashCodeTrue() {
        SpaceActionRequirements spaceActionRequirementsToConfront = new SpaceActionRequirements(ActionType.HARVEST,
                PawnColor.BLACK, 1,1, 0, false);
        assertTrue(spaceActionRequirements.hashCode() == spaceActionRequirementsToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        SpaceActionRequirements spaceActionRequirementsToConfront = new SpaceActionRequirements(ActionType.BLUE_TOWER,
                PawnColor.WHITE, 1,1, 0, false);
        assertFalse(spaceActionRequirements.hashCode() == spaceActionRequirementsToConfront.hashCode());
    }
}