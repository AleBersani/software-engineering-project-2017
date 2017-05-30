package it.polimi.ingsw.gamelogic.board;

import java.util.List;

/**
 * TODO: JavaDoc
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
     * TODO: JavaDoc
     * @param production
     */
    public void addToProductionArea(ProductionHarvestSpace production) {
        productionArea.add(production);
    }

    /**
     * TODO: JavaDoc
     * @param harvest
     */
    public void addToHarvestArea(ProductionHarvestSpace harvest) {
        harvestArea.add(harvest);
    }

    /**
     * TODO: JavaDoc
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
