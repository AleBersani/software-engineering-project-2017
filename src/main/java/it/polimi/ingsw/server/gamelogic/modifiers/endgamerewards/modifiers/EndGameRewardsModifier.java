package it.polimi.ingsw.server.gamelogic.modifiers.endgamerewards.modifiers;

import it.polimi.ingsw.server.gamelogic.modifiers.endgamerewards.BasicEndGameRewards;

/**
 * Interface implemented by the Rewards Modifiers
 */
@FunctionalInterface
public interface EndGameRewardsModifier {
    void modifyEndGameRewards(BasicEndGameRewards basicEndGameRewards);
}
