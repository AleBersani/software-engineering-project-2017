package it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.BoardActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.SpaceActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.TowerActionRequirements;

/**
 * Class that represents the effect of an Excommunication Tile that reduces the value of an Action
 */
public class MalusActionValue extends RequirementsModifier {
    private int malusValue;

    public MalusActionValue(AvailableActions availableActions, int value) {
        super(availableActions);
        this.malusValue = value;
    }

    @Override
    public BoardActionRequirements modifyRequirements(BoardActionRequirements boardActionRequirements) {
        if (availableActions.hasAvailableAction(boardActionRequirements.getActionType())) {
            BoardActionRequirements malusBoardActionModifier = boardActionRequirements;
            addBonusActionValue(malusBoardActionModifier.getSpaceActionRequirements());
            return malusBoardActionModifier;
        }
        return boardActionRequirements;
    }

    @Override
    public TowerActionRequirements modifyRequirements(TowerActionRequirements towerActionRequirements) {
        if (availableActions.hasAvailableAction(towerActionRequirements.getActionType())) {
            TowerActionRequirements malusTowerActionModifier = towerActionRequirements;
            addBonusActionValue(malusTowerActionModifier.getSpaceActionRequirements());
            return malusTowerActionModifier;
        }
        return towerActionRequirements;
    }

    private void addBonusActionValue(SpaceActionRequirements spaceActionRequirements) {
        int actualBonusValue = spaceActionRequirements.getBonusActionValue();
        spaceActionRequirements.setBonusActionValue(actualBonusValue - malusValue);
    }
}
