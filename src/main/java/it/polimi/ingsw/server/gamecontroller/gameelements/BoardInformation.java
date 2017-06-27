package it.polimi.ingsw.server.gamecontroller.gameelements;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.board.CouncilPalace;
import it.polimi.ingsw.server.gamelogic.board.MarketSpace;
import it.polimi.ingsw.server.gamelogic.board.ProductionHarvestSpace;
import it.polimi.ingsw.server.gamelogic.board.Space;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardInformation {
    private static Map<Space, Goods> greenTower;
    private static Map<Space, Goods> yellowTower;
    private static Map<Space, Goods> blueTower;
    private static Map<Space, Goods> purpleTower;
    private static CouncilPalace councilPalace;
    private static List<ProductionHarvestSpace> harvestArea;
    private static List<ProductionHarvestSpace> productionArea;
    private static List<MarketSpace> marketArea;

    public static void initializeBoardInformationMaps() {
        greenTower = new HashMap<>();
        yellowTower = new HashMap<>();
        blueTower = new HashMap<>();
        purpleTower = new HashMap<>();
        harvestArea = new ArrayList<>();
        productionArea = new ArrayList<>();
        marketArea = new ArrayList<>();
    }

    public static Map<Space, Goods> getGreenTower() {
        return greenTower;
    }

    public static void setGreenTower(Map<Space, Goods> greenTower) {
        BoardInformation.greenTower = greenTower;
    }

    public static Map<Space, Goods> getYellowTower() {
        return yellowTower;
    }

    public static void setYellowTower(Map<Space, Goods> yellowTower) {
        BoardInformation.yellowTower = yellowTower;
    }

    public static Map<Space, Goods> getBlueTower() {
        return blueTower;
    }

    public static void setBlueTower(Map<Space, Goods> blueTower) {
        BoardInformation.blueTower = blueTower;
    }

    public static Map<Space, Goods> getPurpleTower() {
        return purpleTower;
    }

    public static void setPurpleTower(Map<Space, Goods> purpleTower) {
        BoardInformation.purpleTower = purpleTower;
    }

    public static CouncilPalace getCouncilPalace() {
        return councilPalace;
    }

    public static void setCouncilPalace(CouncilPalace councilPalace) {
        BoardInformation.councilPalace = councilPalace;
    }

    public static List<ProductionHarvestSpace> getHarvestArea() {
        return harvestArea;
    }

    public static void setHarvestArea(List<ProductionHarvestSpace> harvestArea) {
        BoardInformation.harvestArea = harvestArea;
    }

    public static List<ProductionHarvestSpace> getProductionArea() {
        return productionArea;
    }

    public static void setProductionArea(List<ProductionHarvestSpace> productionArea) {
        BoardInformation.productionArea = productionArea;
    }

    public static List<MarketSpace> getMarketArea() {
        return marketArea;
    }

    public static void setMarketArea(List<MarketSpace> marketArea) {
        BoardInformation.marketArea = marketArea;
    }
}
