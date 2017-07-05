package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.shared.model.BoardIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductionHarvestSpaceTest {
    private ProductionHarvestSpace productionHarvestSpace;

    @BeforeEach
    void setUp() {
        productionHarvestSpace = new ProductionHarvestSpace(new Space(BoardIdentifier.T_G_3,2),
                2);
    }

    @Test
    void testEqualsTrue() {
        ProductionHarvestSpace productionHarvestSpaceToConfront = new ProductionHarvestSpace(
                new Space(BoardIdentifier.T_G_3,2),
                2);
        ProductionHarvestSpace productionHarvestSpaceToConfront2 = productionHarvestSpace;
        assertTrue(productionHarvestSpace.equals(productionHarvestSpaceToConfront));
        assertTrue(productionHarvestSpace.equals(productionHarvestSpaceToConfront2));
    }

    @Test
    void testEqualsFalse() {
        ProductionHarvestSpace productionHarvestSpaceToConfront = new ProductionHarvestSpace(
                new Space(BoardIdentifier.T_G_3,1),
                2);
        assertFalse(productionHarvestSpaceToConfront.equals(productionHarvestSpace));
    }

    @Test
    void testEqualsDifferent() {
        String obj = "";
        assertFalse(productionHarvestSpace.equals(obj));
        assertFalse(productionHarvestSpace.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        ProductionHarvestSpace productionHarvestSpaceToConfront = new ProductionHarvestSpace(
                new Space(BoardIdentifier.T_G_3,2),
                2);
        assertEquals(productionHarvestSpaceToConfront.hashCode(), productionHarvestSpace.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        ProductionHarvestSpace productionHarvestSpaceToConfront = new ProductionHarvestSpace(
                new Space(BoardIdentifier.T_G_3,1),
                2);
        assertNotEquals(productionHarvestSpaceToConfront.hashCode(), productionHarvestSpace.hashCode());
    }

    @Test
    void testGetSpace() {
        Space spaceToConfront = new Space(BoardIdentifier.T_G_1, 3);
        productionHarvestSpace.setSpace(spaceToConfront);
        assertEquals(spaceToConfront, productionHarvestSpace.getSpace());
    }

    @Test
    void testGetMalusValue() {
        int numberToConfront = 5;
        productionHarvestSpace.setMalusValue(numberToConfront);
        assertEquals(numberToConfront, productionHarvestSpace.getMalusValue());
    }

    @Test
    void testGetNumberOfRequiredPlayers() {
        int numberToConfront = 3;
        productionHarvestSpace.setNumberOfRequiredPlayers(numberToConfront);
        assertEquals(numberToConfront, productionHarvestSpace.getNumberOfRequiredPlayers());
    }
}