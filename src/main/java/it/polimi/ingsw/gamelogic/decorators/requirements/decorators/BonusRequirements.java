package it.polimi.ingsw.gamelogic.decorators.requirements.decorators;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.decorators.AvailableIdentifiers;
import it.polimi.ingsw.gamelogic.decorators.requirements.BoardActionRequirements;
import it.polimi.ingsw.gamelogic.decorators.requirements.LeaderRequirements;
import it.polimi.ingsw.gamelogic.decorators.requirements.SpaceActionRequirements;
import it.polimi.ingsw.gamelogic.decorators.requirements.TowerActionRequirements;

public class BonusRequirements extends RequirementsModifier {
    private Goods bonus;

    public BonusRequirements(AvailableIdentifiers availableIdentifiers, Goods bonus) {
        super(availableIdentifiers);
        this.bonus = bonus;
    }

    @Override
    public void modifyRequirements(BoardActionRequirements boardActionRequirements) {
        if(availableIdentifiers.hasBoardIdentifier(boardActionRequirements.getBoardIdentifier())) {

        }
    }

    @Override
    public void modifyRequirements(LeaderRequirements leaderRequirements) {

    }

    @Override
    public void modifyRequirements(SpaceActionRequirements spaceActionRequirements) {

    }

    @Override
    public void modifyRequirements(TowerActionRequirements towerActionRequirements) {

    }
}
