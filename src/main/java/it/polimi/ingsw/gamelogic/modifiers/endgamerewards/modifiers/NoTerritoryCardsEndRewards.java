package it.polimi.ingsw.gamelogic.modifiers.endgamerewards.modifiers;

import it.polimi.ingsw.gamelogic.basics.Points;
import it.polimi.ingsw.gamelogic.modifiers.endgamerewards.BasicEndGameRewards;

/**
 * Describes the effect of an Excommunication Tile that prevents the Player to collect the Victory Points from the
 * Territory cards
 */
public class NoTerritoryCardsEndRewards implements EndGameRewardsModifier {
    @Override
    public void modifyEndGameRewards(BasicEndGameRewards basicEndGameRewards) {
        basicEndGameRewards.setPointsForTerritoryCards(new Points());
    }
}
