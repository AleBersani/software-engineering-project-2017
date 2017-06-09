package it.polimi.ingsw.gamelogic.modifiers.endgamerewards.modifiers;

import it.polimi.ingsw.gamelogic.basics.Points;
import it.polimi.ingsw.gamelogic.basics.Resources;
import it.polimi.ingsw.gamelogic.modifiers.endgamerewards.BasicEndGameRewards;

import java.util.Objects;

/**
 * Describes the effect of an Excommunication Tile that reduces the Player's Victory Points basing on the number of
 * the Player's Resources
 */
public class LessVictoryForResources implements EndGameRewardsModifier {
    private Resources resources;

    public LessVictoryForResources() {
        resources = new Resources();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LessVictoryForResources that = (LessVictoryForResources) o;
        return Objects.equals(resources, that.resources);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resources);
    }

    @Override
    public void modifyEndGameRewards(BasicEndGameRewards basicEndGameRewards) {
        int sumOfResources = resources.getWoods() +
                resources.getStones() +
                resources.getServants() +
                resources.getCoins();
        basicEndGameRewards.setOnePointLessForEveryResource(new Points(sumOfResources, 0, 0));
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }
}
