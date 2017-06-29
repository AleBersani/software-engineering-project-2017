package it.polimi.ingsw.server.gamelogic.board;

import com.sun.javafx.image.impl.ByteGrayAlpha;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardActionSpacesTest {
    private BoardActionSpaces boardActionSpaces;

    @BeforeEach
    void setUp() {
        boardActionSpaces = new BoardActionSpaces(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    @Test
    void testEqualsTrue() {
        BoardActionSpaces boardActionSpacesToConfront = new BoardActionSpaces(new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        assertTrue(boardActionSpaces.equals(boardActionSpacesToConfront));
    }

    @Test
    void testHashCodeTrue() {
        BoardActionSpaces boardActionSpacesToConfront = new BoardActionSpaces(new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        assertTrue(boardActionSpaces.hashCode() == boardActionSpacesToConfront.hashCode());
    }

    @Test
    void testGetProductionArea() {
        ArrayList<ProductionHarvestSpace> productionHarvestSpaces = new ArrayList<>();
        assertEquals(productionHarvestSpaces, boardActionSpaces.getProductionArea());
        assertEquals(productionHarvestSpaces, boardActionSpaces.getHarvestArea());
    }

    @Test
    void testGetMarketArea() {
        ArrayList<MarketSpace> marketSpaces = new ArrayList<>();
        assertEquals(marketSpaces, boardActionSpaces.getMarketArea());
    }
}