import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Tower> towers;
    private List<ExcommunicationTile> excommunicationTiles;
    private CouncilPalace councilPalace;
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
    public int getDiceValueWithColor(String color) {
        for (Dice T : this.dices)
            if(T.getColor().equals(color))
                return T.getValue();
    }

    public ActionSpace getFreeProductionArea() {
        return this.getFreeActionSpace(this.productionArea);
    }

    public ActionSpace getFreeHarvestArea() {
        return this.getFreeActionSpace(this.harvestArea);
    }

    public ActionSpace getFreeMarketArea() {
        return this.getFreeActionSpace(this.marketArea);
    }

    private ActionSpace getFreeActionSpace(List<ActionSpace> actionSpaces) {
        for( ActionSpace T : actionSpaces )
            if(!T.isAlreadyTaken())
                return T;
        return null;
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
