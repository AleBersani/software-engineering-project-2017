package it.polimi.ingsw.client.model;

import it.polimi.ingsw.shared.model.BoardIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardLightTest {
    private BoardLight boardLight;

    @BeforeEach
    void setUp() {
        boardLight = BoardLight.getInstance();
        boardLight.setGreenTower(new ArrayList<>());
        boardLight.setYellowTower(new ArrayList<>());
        boardLight.setBlueTower(new ArrayList<>());
        boardLight.setPurpleTower(new ArrayList<>());
        boardLight.setProduction(new ArrayList<>());
        boardLight.setHarvest(new ArrayList<>());
        boardLight.setMarket(new ArrayList<>());
        boardLight.setCouncilPalaceLight(new CouncilPalaceLight(BoardIdentifier.COUNCIL_PALACE));
    }

    @Test
    void testGetGreenTower() {
        List<TowerSlotLight> greenTowerToSet = new ArrayList<>();
        boardLight.setGreenTower(greenTowerToSet);
        assertEquals(greenTowerToSet, boardLight.getGreenTower());
    }

    @Test
    void testGetYellowTower() {
        List<TowerSlotLight> yellowTowerToSet = new ArrayList<>();
        boardLight.setYellowTower(yellowTowerToSet);
        assertEquals(yellowTowerToSet, boardLight.getYellowTower());
    }

    @Test
    void testGetBlueTower() {
        List<TowerSlotLight> blueTowerToSet = new ArrayList<>();
        boardLight.setBlueTower(blueTowerToSet);
        assertEquals(blueTowerToSet, boardLight.getBlueTower());
    }

    @Test
    void testGetPurpleTower() {
        List<TowerSlotLight> purpleTowerToSet = new ArrayList<>();
        boardLight.setPurpleTower(purpleTowerToSet);
        assertEquals(purpleTowerToSet, boardLight.getPurpleTower());
    }

    @Test
    void testGetProduction() {
        List<SlotLight> productionToSet = new ArrayList<>();
        boardLight.setProduction(productionToSet);
        assertEquals(productionToSet, boardLight.getProduction());
    }

    @Test
    void testGetHarvest() {
        List<SlotLight> harvestToSet = new ArrayList<>();
        boardLight.setHarvest(harvestToSet);
        assertEquals(harvestToSet, boardLight.getHarvest());
    }

    @Test
    void testGetMarket() {
        List<SlotLight> marketToSet = new ArrayList<>();
        boardLight.setMarket(marketToSet);
        assertEquals(marketToSet, boardLight.getMarket());
    }

    @Test
    void testGetCouncilPalaceLight() {
        CouncilPalaceLight councilPalaceLight = new CouncilPalaceLight(BoardIdentifier.COUNCIL_PALACE);
        boardLight.setCouncilPalaceLight(councilPalaceLight);
        assertEquals(councilPalaceLight, boardLight.getCouncilPalaceLight());
    }
}