package it.polimi.ingsw.server.gamecontroller.gameelements;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.board.CouncilPalace;
import it.polimi.ingsw.gamelogic.board.MarketSpace;
import it.polimi.ingsw.gamelogic.board.ProductionHarvestSpace;
import it.polimi.ingsw.gamelogic.board.Space;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardInformation {
    public static Map<Space, Goods> greenTower;
    public static Map<Space, Goods> yellowTower;
    public static Map<Space, Goods> blueTower;
    public static Map<Space, Goods> purpleTower;
    public static CouncilPalace councilPalace;
    public static List<ProductionHarvestSpace> harvestArea;
    public static List<ProductionHarvestSpace> productionArea;
    public static List<MarketSpace> marketArea;

    public static void initializeBoardInformationMaps() {
        greenTower = new HashMap<>();
        yellowTower = new HashMap<>();
        blueTower = new HashMap<>();
        purpleTower = new HashMap<>();
        harvestArea = new ArrayList<>();
        productionArea = new ArrayList<>();
        marketArea = new ArrayList<>();
    }
}
