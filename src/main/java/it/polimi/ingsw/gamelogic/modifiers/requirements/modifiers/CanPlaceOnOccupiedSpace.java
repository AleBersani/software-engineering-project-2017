package it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.BoardActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.SpaceActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.TowerActionRequirements;

/**
 * Class that describes the possibility for the player to place one of his/hers pawns in an already occupied
 * Action Space
 */
public class CanPlaceOnOccupiedSpace extends RequirementsModifier {
    public CanPlaceOnOccupiedSpace(AvailableActions availableActions) {
        super(availableActions);
    }

    @Override
    public BoardActionRequirements modifyRequirements(BoardActionRequirements boardActionRequirements) {
        if (availableActions.hasAvailableAction(boardActionRequirements.getActionType())) {
            BoardActionRequirements occupiedSpaceBoardActionModifier = boardActionRequirements;
            setOccupiedSpaceFalse(occupiedSpaceBoardActionModifier.getSpaceActionRequirements());
            return occupiedSpaceBoardActionModifier;
        }
        return boardActionRequirements;
    }

    @Override
    public TowerActionRequirements modifyRequirements(TowerActionRequirements towerActionRequirements) {
        if (availableActions.hasAvailableAction(towerActionRequirements.getActionType())) {
            TowerActionRequirements occupiedSpaceTowerActionModifier = towerActionRequirements;
            setOccupiedSpaceFalse(occupiedSpaceTowerActionModifier.getSpaceActionRequirements());
            return occupiedSpaceTowerActionModifier;
        }
        return towerActionRequirements;
    }

    private void setOccupiedSpaceFalse(SpaceActionRequirements spaceActionRequirements) {
        spaceActionRequirements.setOccupied(false);
    }
}
