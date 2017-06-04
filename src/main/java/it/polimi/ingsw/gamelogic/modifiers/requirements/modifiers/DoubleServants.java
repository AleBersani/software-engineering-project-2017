package it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.BoardActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.SpaceActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.TowerActionRequirements;

/**
 * TODO: JavaDoc
 */
public class DoubleServants extends RequirementsModifier {
    public DoubleServants(AvailableActions availableActions) {
        super(availableActions);
    }

    @Override
    public BoardActionRequirements modifyRequirements(BoardActionRequirements boardActionRequirements) {
        if (availableActions.hasAvailableAction(boardActionRequirements.getActionType())) {
            BoardActionRequirements doubleServantsBoardActionModifier = boardActionRequirements;
            doubleServants(doubleServantsBoardActionModifier.getSpaceActionRequirements());
            return doubleServantsBoardActionModifier;
        }
        return boardActionRequirements;
    }

    @Override
    public TowerActionRequirements modifyRequirements(TowerActionRequirements towerActionRequirements) {
        if (availableActions.hasAvailableAction(towerActionRequirements.getActionType())) {
            TowerActionRequirements doubleServantsTowerActionModifier = towerActionRequirements;
            doubleServants(doubleServantsTowerActionModifier.getSpaceActionRequirements());
            return doubleServantsTowerActionModifier;
        }
        return towerActionRequirements;
    }

    private void doubleServants(SpaceActionRequirements spaceActionRequirements) {
        int numberOfServants = spaceActionRequirements.getNumberOfServants();
        spaceActionRequirements.setNumberOfServants(numberOfServants / 2);
    }
}
