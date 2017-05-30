package it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.BoardActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.LeaderRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.TowerActionRequirements;

public class BonusRequirements extends RequirementsModifier {
    private Goods bonus;

    public BonusRequirements(AvailableActions availableActions, Goods bonus) {
        super(availableActions);
        this.bonus = bonus;
    }

    @Override
    public BoardActionRequirements modifyRequirements(BoardActionRequirements boardActionRequirements) {
        return boardActionRequirements;
    }

    @Override
    public LeaderRequirements modifyRequirements(LeaderRequirements leaderRequirements) {
        return leaderRequirements;
    }

    @Override
    public TowerActionRequirements modifyRequirements(TowerActionRequirements towerActionRequirements) {
        if(availableActions.hasAvailableAction(towerActionRequirements.getActionType())) {
            TowerActionRequirements modifier;
            /*
            TODO
             */
        }
        return towerActionRequirements;
    }
}
