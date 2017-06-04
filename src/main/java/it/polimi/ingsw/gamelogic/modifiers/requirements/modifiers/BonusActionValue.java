package it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.BoardActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.SpaceActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.TowerActionRequirements;

/**
 * Class that describes when it's given a Bonus value to perform an action
 */
public class BonusActionValue extends RequirementsModifier {
    private int bonusValue;

    public BonusActionValue(AvailableActions availableActions, int bonusValue) {
        super(availableActions);
        this.bonusValue = bonusValue;
    }

    @Override
    public BoardActionRequirements modifyRequirements(BoardActionRequirements boardActionRequirements) {
        if (availableActions.hasAvailableAction(boardActionRequirements.getActionType())) {
            BoardActionRequirements bonusActionValueBoardActionModifier = boardActionRequirements;
            addBonusActionValue(bonusActionValueBoardActionModifier.getSpaceActionRequirements());
            return bonusActionValueBoardActionModifier;
        }
        return boardActionRequirements;
    }

    @Override
    public TowerActionRequirements modifyRequirements(TowerActionRequirements towerActionRequirements) {
        if (availableActions.hasAvailableAction(towerActionRequirements.getActionType())) {
            TowerActionRequirements bonusActionValueTowerActionModifier = towerActionRequirements;
            addBonusActionValue(bonusActionValueTowerActionModifier.getSpaceActionRequirements());
            return bonusActionValueTowerActionModifier;
        }
        return towerActionRequirements;
    }

    private void addBonusActionValue(SpaceActionRequirements spaceActionRequirements) {
        int actualBonusValue = spaceActionRequirements.getBonusActionValue();
        spaceActionRequirements.setBonusActionValue(bonusValue + actualBonusValue);
    }
}