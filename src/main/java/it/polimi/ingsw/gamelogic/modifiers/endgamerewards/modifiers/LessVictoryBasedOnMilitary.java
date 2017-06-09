package it.polimi.ingsw.gamelogic.modifiers.endgamerewards.modifiers;

import it.polimi.ingsw.gamelogic.basics.Points;
import it.polimi.ingsw.gamelogic.modifiers.endgamerewards.BasicEndGameRewards;

import java.util.Objects;

/**
 * Describes the effect of an Excommunication Tile that reduces the Player's Victory Points basing on the number of
 * the Player's Military Points
 */
public class LessVictoryBasedOnMilitary implements EndGameRewardsModifier {
    private Points playerMilitary;

    public LessVictoryBasedOnMilitary() {
        playerMilitary = new Points();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LessVictoryBasedOnMilitary that = (LessVictoryBasedOnMilitary) o;
        return Objects.equals(playerMilitary, that.playerMilitary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerMilitary);
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
