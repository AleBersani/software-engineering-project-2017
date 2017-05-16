package it.polimi.ingsw.gamelogic.board;

import it.polimi.ingsw.gamelogic.cards.ExcommunicationTile;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Tower> towers;
    private List<ExcommunicationTile> excommunicationTiles;
    private CouncilPalace councilPalace;
    /*
    TODO: wrapper for List<ActionSpace>
     */
    private List<ActionSpace> productionArea;
    private List<ActionSpace> harvestArea;
    private List<ActionSpace> marketArea;
    private List<Dice> dices;


    public Board(List<Tower> towers, CouncilPalace councilPalace,
                 List<ActionSpace> productionArea, List<ActionSpace> harvestArea, List<ActionSpace> marketArea) {
        this.towers = towers;
        excommunicationTiles = new ArrayList<>();
        this.councilPalace = councilPalace;
        this.productionArea = productionArea;
        this.harvestArea = harvestArea;
        this.marketArea = marketArea;
        dices = new ArrayList<>();
    }

    /**
     * It returns the value associated to a specific coloured Dice
     **/
    /*
    TODO: color is an ENUM
     */
    public int getDiceValueWithColor(String color) {
        for (Dice T : dices)
            if(T.getColor().equals(color))
                return T.getValue();
        /*
        TODO: check return statement. Return 0 was missing
         */
        return 0;
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public void setTowers(List<Tower> towers) {
        this.towers = towers;
    }

    public List<ExcommunicationTile> getExcommunicationTiles() {
        return excommunicationTiles;
    }

    public void setExcommunicationTiles(List<ExcommunicationTile> excommunicationTiles) {
        this.excommunicationTiles = excommunicationTiles;
    }

    public CouncilPalace getCouncilPalace() {
        return councilPalace;
    }

    public void setCouncilPalace(CouncilPalace councilPalace) {
        this.councilPalace = councilPalace;
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

    public List<Dice> getDices() {
        return dices;
    }

    public void setDices(List<Dice> dices) {
        this.dices = dices;
    }
}
