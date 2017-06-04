package it.polimi.ingsw.gamelogic.modifiers.endgamerewards.modifiers;

import it.polimi.ingsw.gamelogic.basics.Points;
import it.polimi.ingsw.gamelogic.basics.Resources;
import it.polimi.ingsw.gamelogic.modifiers.endgamerewards.BasicEndGameRewards;

/**
 * TODO: JavaDoc
 */
public class LessVictoryForResources implements EndGameRewardsModifier {
    private Resources resources;

    public LessVictoryForResources(Resources resources) {
        this.resources = resources;
    }

    @Override
    public void modifyEndGameRewards(BasicEndGameRewards basicEndGameRewards) {
        int sumOfResources = resources.getWoods() +
                resources.getStones() +
                resources.getServants() +
                resources.getCoins();
        basicEndGameRewards.setOnePointLessForEveryResource(new Points(sumOfResources, 0, 0));
    }
}
