package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardActionSpacesTest {
    private BoardActionSpaces boardActionSpaces;

    @BeforeEach
    void setUp() {
        boardActionSpaces = new BoardActionSpaces(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    @Test
    void testEqualsTrue1() {
        BoardActionSpaces boardActionSpacesToConfront = new BoardActionSpaces(new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        assertTrue(boardActionSpaces.equals(boardActionSpacesToConfront));
    }

    @Test
    void testEqualsTrue2() {
        BoardActionSpaces boardActionSpacesToConfront = boardActionSpaces;
        assertTrue(boardActionSpaces.equals(boardActionSpacesToConfront));
    }

    @Test
    void testEqualsFalse1() {
        List<ProductionHarvestSpace> productionHarvestSpaces = new ArrayList<>();
        productionHarvestSpaces.add(new ProductionHarvestSpace(
                new Space(BoardIdentifier.T_G_3,5),
                4));
        BoardActionSpaces boardActionSpacesToConfront = new BoardActionSpaces(productionHarvestSpaces, new ArrayList<>(),
                new ArrayList<>());
        assertFalse(boardActionSpaces.equals(boardActionSpacesToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(boardActionSpaces.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(boardActionSpaces.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        BoardActionSpaces boardActionSpacesToConfront = new BoardActionSpaces(new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        assertTrue(boardActionSpaces.hashCode() == boardActionSpacesToConfront.hashCode());
    }

    @Test
    void testAddToProductionArea() {
        List<ProductionHarvestSpace> productionHarvestSpaces = new ArrayList<>(boardActionSpaces.getProductionArea());
        ProductionHarvestSpace productionHarvestSpaceToAdd = new ProductionHarvestSpace(
                new Space(BoardIdentifier.T_G_3,5),
                4);
        productionHarvestSpaces.add(productionHarvestSpaceToAdd);
        boardActionSpaces.addToProductionArea(productionHarvestSpaceToAdd);
        assertEquals(boardActionSpaces.getProductionArea(), productionHarvestSpaces);
    }

    @Test
    void testAddToHarvestArea() {
        List<ProductionHarvestSpace> productionHarvestSpaces = new ArrayList<>(boardActionSpaces.getHarvestArea());
        ProductionHarvestSpace productionHarvestSpaceToAdd = new ProductionHarvestSpace(
                new Space(BoardIdentifier.T_G_3,5),
                4);
        productionHarvestSpaces.add(productionHarvestSpaceToAdd);
        boardActionSpaces.addToHarvestArea(productionHarvestSpaceToAdd);
        assertEquals(boardActionSpaces.getHarvestArea(), productionHarvestSpaces);
    }

    @Test
    void testAddToMarketArea() {
        List<MarketSpace> marketSpaces = new ArrayList<>(boardActionSpaces.getMarketArea());
        MarketSpace marketSpaceToAdd = new MarketSpace(
                new Space(BoardIdentifier.T_G_3, 2),
                new ExchangingGoods(new Goods(new Points(1,2,3)), 0 ));
        marketSpaces.add(marketSpaceToAdd);
        boardActionSpaces.addToMarketArea(marketSpaceToAdd);
        assertEquals(boardActionSpaces.getMarketArea(), marketSpaces);
    }

    @Test
    void testGetProductionArea() {
        ArrayList<ProductionHarvestSpace> productionHarvestSpaces = new ArrayList<>();
        boardActionSpaces.setProductionArea(productionHarvestSpaces);
        assertEquals(productionHarvestSpaces, boardActionSpaces.getProductionArea());
    }

    @Test
    void testGetHarvestArea() {
        ArrayList<ProductionHarvestSpace> productionHarvestSpaces = new ArrayList<>();
        boardActionSpaces.setHarvestArea(productionHarvestSpaces);
        assertEquals(productionHarvestSpaces, boardActionSpaces.getHarvestArea());
    }

    @Test
    void testGetMarketArea() {
        ArrayList<MarketSpace> marketSpaces = new ArrayList<>();
        assertEquals(marketSpaces, boardActionSpaces.getMarketArea());
    }
}