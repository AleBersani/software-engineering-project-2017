package it.polimi.ingsw.gamelogic.modifiers.endgamerewards.modifiers;

import it.polimi.ingsw.gamelogic.basics.Points;
import it.polimi.ingsw.gamelogic.cards.development.Building;
import it.polimi.ingsw.gamelogic.modifiers.endgamerewards.BasicEndGameRewards;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes th effect of an Excommunication Tile that reduces the Player's Victory points basing on the costs
 * of the Player's Building cards
 */
public class LessVictoryBasedOnBuildingsCosts implements EndGameRewardsModifier {
    private List<Building> buildings;

    public LessVictoryBasedOnBuildingsCosts() {
        buildings = new ArrayList<>();
    }

    @Override
    public void modifyEndGameRewards(BasicEndGameRewards basicEndGameRewards) {
        int woods = buildings.stream()
                .map(building -> building.getCosts().get(0).getResources().getWoods())
                .reduce(0, (x, y) -> x + y);
        int stones = buildings.stream()
                .map(building -> building.getCosts().get(0).getResources().getStones())
                .reduce(0, (x, y) -> x + y);
        basicEndGameRewards
                .setOnePointLessForEveryWoodAndStoneOnBuilding(new Points(woods + stones, 0, 0));
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }
}
