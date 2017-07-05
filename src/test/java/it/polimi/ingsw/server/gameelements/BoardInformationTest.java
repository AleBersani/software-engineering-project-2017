package it.polimi.ingsw.server.gameelements;

import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.board.*;
import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers.BonusActionValue;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BoardInformationTest {
    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetGreenTower() {
        Map<Space, Goods> mapToSet = new HashMap<>();
        mapToSet.put(new Space(BoardIdentifier.T_G_1, 2),
                new Goods(new Points(1,2,3)));
        BoardInformation.setGreenTower(mapToSet);
        assertEquals(mapToSet, BoardInformation.getGreenTower());

    }

    @Test
    void testGetYellowTower() {
        Map<Space, Goods> mapToSet = new HashMap<>();
        mapToSet.put(new Space(BoardIdentifier.T_Y_1, 2),
                new Goods(new Points(1,2,3)));
        BoardInformation.setYellowTower(mapToSet);
        assertEquals(mapToSet, BoardInformation.getYellowTower());
    }

    @Test
    void testGetBlueTower() {
        Map<Space, Goods> mapToSet = new HashMap<>();
        mapToSet.put(new Space(BoardIdentifier.T_B_1, 2),
                new Goods(new Points(1,2,3)));
        BoardInformation.setBlueTower(mapToSet);
        assertEquals(mapToSet, BoardInformation.getBlueTower());
    }

    @Test
    void testGetPurpleTower() {
        Map<Space, Goods> mapToSet = new HashMap<>();
        mapToSet.put(new Space(BoardIdentifier.T_P_1, 2),
                new Goods(new Points(1,2,3)));
        BoardInformation.setPurpleTower(mapToSet);
        assertEquals(mapToSet, BoardInformation.getPurpleTower());
    }

    @Test
    void testGetCouncilPalace() {
        CouncilPalace councilPalaceToSet = new CouncilPalace(new ExchangingGoods(
                new Goods(new Points(1,2,3)), 1), 1);
        BoardInformation.setCouncilPalace(councilPalaceToSet);
        assertEquals(councilPalaceToSet, BoardInformation.getCouncilPalace());
    }

    @Test
    void testGetFaithToVictoryPoints() {
        Map<Integer, Integer> faithToVictoryToSet = new HashMap<>();
        faithToVictoryToSet.put(1,2);
        BoardInformation.setFaithToVictoryPoints(faithToVictoryToSet);
        assertEquals(faithToVictoryToSet, BoardInformation.getFaithToVictoryPoints());
    }

    @Test
    void testGetFaithPointsToAvoidExcommunication() {
        Map<PeriodNumber, Integer> faithToVictoryToAvoidExcommunicationToSet = new HashMap<>();
        faithToVictoryToAvoidExcommunicationToSet.put(PeriodNumber.FIRST,2);
        BoardInformation.setFaithPointsToAvoidExcommunication(faithToVictoryToAvoidExcommunicationToSet);
        assertEquals(faithToVictoryToAvoidExcommunicationToSet, BoardInformation.getFaithPointsToAvoidExcommunication());
    }

    @Test
    void testGetHarvestArea() {
        List<ProductionHarvestSpace> harvestSpacesToSet = new ArrayList<>();
        harvestSpacesToSet.add(new ProductionHarvestSpace(
                new Space(BoardIdentifier.HARVEST_1, 1), 1, 2));
        BoardInformation.setHarvestArea(harvestSpacesToSet);
        assertEquals(harvestSpacesToSet, BoardInformation.getHarvestArea());
    }

    @Test
    void testGetProductionArea() {
        List<ProductionHarvestSpace> productionSpacesToSet = new ArrayList<>();
        productionSpacesToSet.add(new ProductionHarvestSpace(
                new Space(BoardIdentifier.PRODUCTION_1, 1), 1, 2));
        BoardInformation.setProductionArea(productionSpacesToSet);
        assertEquals(productionSpacesToSet, BoardInformation.getProductionArea());
    }

    @Test
    void testGetMarketArea() {
        List<MarketSpace> marketSpacesToSet = new ArrayList<>();
        marketSpacesToSet.add(new MarketSpace(new Space(BoardIdentifier.M_1, 1), new ExchangingGoods(
                        new Points(1,2,3), 1), 2));
        BoardInformation.setMarketArea(marketSpacesToSet);
        assertEquals(marketSpacesToSet, BoardInformation.getMarketArea());
    }

    @Test
    void testGetBonusTiles() {
        Map<String, List<ExchangingGoods>> bonusTileToSet = new HashMap<>();
        List<ExchangingGoods> exchangingGoodsToPut = new ArrayList<>();
        exchangingGoodsToPut.add(new ExchangingGoods(new Points(1,2,3),2));
        bonusTileToSet.put("", exchangingGoodsToPut);
        BoardInformation.setBonusTiles(bonusTileToSet);
        assertEquals(bonusTileToSet, BoardInformation.getBonusTiles());
    }
}