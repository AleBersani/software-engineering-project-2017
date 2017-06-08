package it.polimi.ingsw.gamelogic.modifiers.endgamerewards.modifiers;

import it.polimi.ingsw.gamelogic.modifiers.endgamerewards.BasicEndGameRewards;

/**
 * Interface implemented by the Rewards Modifiers
 */
public interface EndGameRewardsModifier {
    void modifyEndGameRewards(BasicEndGameRewards basicEndGameRewards);
}
