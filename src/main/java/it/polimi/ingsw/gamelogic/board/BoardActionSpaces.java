package it.polimi.ingsw.gamelogic.board;

import java.util.List;

/**
 * Class that describes the areas of the board where if the player puts one of his/her pawns he/she can
 * either set an action that has as a result Goods or Points (productionArea and harvestArea),
 * or get Goods directly (marketArea)
 */
public class BoardActionSpaces {
    private List<ProductionHarvestSpace> productionArea;
    private List<ProductionHarvestSpace> harvestArea;
    private List<MarketSpace> marketArea;

    public BoardActionSpaces(List<ProductionHarvestSpace> productionArea,
                             List<ProductionHarvestSpace> harvestArea,
                             List<MarketSpace> marketArea) {
        this.productionArea = productionArea;
        this.harvestArea = harvestArea;
        this.marketArea = marketArea;
    }

    /**
     * Adds a single productionArea
     * @param production
     */
    public void addToProductionArea(ProductionHarvestSpace production) {
        productionArea.add(production);
    }

    /**
     * Adds a single harvestArea
     * @param harvest
     */
    public void addToHarvestArea(ProductionHarvestSpace harvest) {
        harvestArea.add(harvest);
    }

    /**
     * Adds a single marketArea
     * @param market
     */
    public void addToMarketArea(MarketSpace market) {
        marketArea.add(market);
    }

    public List<ProductionHarvestSpace> getProductionArea() {
        return productionArea;
    }

    public void setProductionArea(List<ProductionHarvestSpace> productionArea) {
        this.productionArea = productionArea;
    }

    public List<ProductionHarvestSpace> getHarvestArea() {
        return harvestArea;
    }

    public void setHarvestArea(List<ProductionHarvestSpace> harvestArea) {
        this.harvestArea = harvestArea;
    }

    public List<MarketSpace> getMarketArea() {
        return marketArea;
    }

    public void setMarketArea(List<MarketSpace> marketArea) {
        this.marketArea = marketArea;
    }
}
