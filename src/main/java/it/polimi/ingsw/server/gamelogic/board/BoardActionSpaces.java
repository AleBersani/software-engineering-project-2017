package it.polimi.ingsw.server.gamelogic.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class that describes the areas of the board where if the player puts one of his/her pawns he/she can
 * either set an action that has as a result Goods or Points (productionArea and harvestArea),
 * or get Goods directly (marketArea)
 */
public class BoardActionSpaces {
    private List<ProductionHarvestSpace> productionArea;
    private List<ProductionHarvestSpace> harvestArea;
    private List<MarketSpace> marketArea;

    public BoardActionSpaces() {
        productionArea = new ArrayList<>();
        harvestArea = new ArrayList<>();
        marketArea = new ArrayList<>();
    }

    public BoardActionSpaces(List<ProductionHarvestSpace> productionArea,
                             List<ProductionHarvestSpace> harvestArea,
                             List<MarketSpace> marketArea) {
        this.productionArea = productionArea;
        this.harvestArea = harvestArea;
        this.marketArea = marketArea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BoardActionSpaces that = (BoardActionSpaces) o;
        return Objects.equals(getProductionArea(), that.getProductionArea()) &&
                Objects.equals(getHarvestArea(), that.getHarvestArea()) &&
                Objects.equals(getMarketArea(), that.getMarketArea());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductionArea(), getHarvestArea(), getMarketArea());
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
