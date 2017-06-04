package it.polimi.ingsw.gamelogic.modifiers.endgamerewards.modifiers;

import it.polimi.ingsw.gamelogic.basics.Points;
import it.polimi.ingsw.gamelogic.modifiers.endgamerewards.BasicEndGameRewards;

/**
 * TODO: JavaDoc
 */
public class NoTerritoryCardsEndRewards implements EndGameRewardsModifier {
    @Override
    public void modifyEndGameRewards(BasicEndGameRewards basicEndGameRewards) {
        basicEndGameRewards.setPointsForTerritoryCards(new Points());
    }
}
