package it.polimi.ingsw.gamelogic.modifiers.endgamerewards.modifiers;

import it.polimi.ingsw.gamelogic.basics.Points;
import it.polimi.ingsw.gamelogic.modifiers.endgamerewards.BasicEndGameRewards;

/**
 * TODO: JavaDOoc
 */
public class NoVentureCardsEndRewards implements EndGameRewardsModifier {

    public NoVentureCardsEndRewards() {
    }

    @Override
    public void modifyEndGameRewards(BasicEndGameRewards basicEndGameRewards) {
        basicEndGameRewards.setPointsForVentureCards(new Points());
    }
}
