package it.polimi.ingsw.gamelogic.board;


import java.util.List;

public class BoardActionSpaces {
    private List<ActionSpace> productionArea;
    private List<ActionSpace> harvestArea;
    private List<ActionSpace> marketArea;

    public BoardActionSpaces(List<ActionSpace> productionArea,
                             List<ActionSpace> harvestArea,
                             List<ActionSpace> marketArea) {
        this.productionArea = productionArea;
        this.harvestArea = harvestArea;
        this.marketArea = marketArea;
    }

    public List<ActionSpace> getProductionArea() {
        return productionArea;
    }

    public void setProductionArea(List<ActionSpace> productionArea) {
        this.productionArea = productionArea;
    }

    public List<ActionSpace> getHarvestArea() {
        return harvestArea;
    }

    public void setHarvestArea(List<ActionSpace> harvestArea) {
        this.harvestArea = harvestArea;
    }

    public List<ActionSpace> getMarketArea() {
        return marketArea;
    }

    public void setMarketArea(List<ActionSpace> marketArea) {
        this.marketArea = marketArea;
    }
}
