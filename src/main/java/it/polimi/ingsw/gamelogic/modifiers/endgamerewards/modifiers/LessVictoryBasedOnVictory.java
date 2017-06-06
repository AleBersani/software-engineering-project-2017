package it.polimi.ingsw.gamelogic.modifiers.endgamerewards.modifiers;

import it.polimi.ingsw.gamelogic.basics.Points;
import it.polimi.ingsw.gamelogic.modifiers.endgamerewards.BasicEndGameRewards;

/**
 * TODO: JavaDoc
 */
public class LessVictoryBasedOnVictory implements EndGameRewardsModifier {
    private Points playerVictory;

    public LessVictoryBasedOnVictory() {
        playerVictory = new Points();
    }

    @Override
    public void modifyEndGameRewards(BasicEndGameRewards basicEndGameRewards) {
        int victoryPoints = playerVictory.getVictory();
        int pointsToSubtract = victoryPoints / 5;
        basicEndGameRewards.setOnePointLessForEveryFiveVictoryPoints(new Points(pointsToSubtract, 0, 0));
    }

    public void setPlayerVictory(Points playerVictory) {
        this.playerVictory = playerVictory;
    }
}
