package it.polimi.ingsw.gamelogic.modifiers.endgamerewards.modifiers;

import it.polimi.ingsw.gamelogic.basics.Points;
import it.polimi.ingsw.gamelogic.modifiers.endgamerewards.BasicEndGameRewards;

/**
 * TODO: JavaDoc
 */
public class LessVictoryBasedOnMilitary implements EndGameRewardsModifier {
    private Points playerMilitary;

    public LessVictoryBasedOnMilitary() {
        playerMilitary = new Points();
    }

    @Override
    public void modifyEndGameRewards(BasicEndGameRewards basicEndGameRewards) {
        int militaryPoints = playerMilitary.getMilitary();
        basicEndGameRewards.setOnePointLessForEveryMilitaryPoints(new Points(militaryPoints, 0,0));
    }

    public void setPlayerMilitary(Points playerMilitary) {
        this.playerMilitary = playerMilitary;
    }
}
