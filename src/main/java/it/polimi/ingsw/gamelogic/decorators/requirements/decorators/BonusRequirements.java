package it.polimi.ingsw.gamelogic.decorators.requirements.decorators;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.decorators.AvailableActions;
import it.polimi.ingsw.gamelogic.decorators.AvailableIdentifiers;
import it.polimi.ingsw.gamelogic.decorators.requirements.BoardActionRequirements;
import it.polimi.ingsw.gamelogic.decorators.requirements.LeaderRequirements;
import it.polimi.ingsw.gamelogic.decorators.requirements.SpaceActionRequirements;
import it.polimi.ingsw.gamelogic.decorators.requirements.TowerActionRequirements;

public class BonusRequirements extends RequirementsModifier {
    private Goods bonus;

    public BonusRequirements(AvailableActions availableActions, Goods bonus) {
        super(availableActions);
        this.bonus = bonus;
    }

    @Override
    public void modifyRequirements(BoardActionRequirements boardActionRequirements) {
        /*

         */
    }

    @Override
    public void modifyRequirements(LeaderRequirements leaderRequirements) {
        /*

         */
    }

    @Override
    public void modifyRequirements(TowerActionRequirements towerActionRequirements) {
        if(availableActions.hasAvailableAction(towerActionRequirements.getActionType())) {

        }
    }
}
