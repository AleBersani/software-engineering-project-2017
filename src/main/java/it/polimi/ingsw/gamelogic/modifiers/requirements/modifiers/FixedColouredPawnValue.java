package it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.gamelogic.enums.PawnColor;
import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.BoardActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.SpaceActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.TowerActionRequirements;

/**
 * TODO: JavaDoc
 */
public class FixedColouredPawnValue extends RequirementsModifier {
    private PawnColor pawnColor;
    private int pawnValue;

    public FixedColouredPawnValue(AvailableActions availableActions, PawnColor pawnColor, int pawnValue) {
        super(availableActions);
        this.pawnColor = pawnColor;
        this.pawnValue = pawnValue;
    }

    @Override
    public BoardActionRequirements modifyRequirements(BoardActionRequirements boardActionRequirements) {
        if (availableActions.hasAvailableAction(boardActionRequirements.getActionType())) {
            BoardActionRequirements fixedColouredPawnValueBoardActionModifier = boardActionRequirements;
            fixedValueIfSameColorPawn(fixedColouredPawnValueBoardActionModifier.getSpaceActionRequirements());
            return fixedColouredPawnValueBoardActionModifier;
        }
        return boardActionRequirements;
    }

    @Override
    public TowerActionRequirements modifyRequirements(TowerActionRequirements towerActionRequirements) {
        if (availableActions.hasAvailableAction(towerActionRequirements.getActionType())) {
            TowerActionRequirements fixedColouredPawnValueTowerActionModifier = towerActionRequirements;
            fixedValueIfSameColorPawn(fixedColouredPawnValueTowerActionModifier.getSpaceActionRequirements());
            return fixedColouredPawnValueTowerActionModifier;
        }
        return towerActionRequirements;
    }

    private void fixedValueIfSameColorPawn(SpaceActionRequirements spaceActionRequirements) {
        if (pawnColor == spaceActionRequirements.getPawnColor()) {
            if (spaceActionRequirements.modifiedActionValue()) {
                if (spaceActionRequirements.getActionValue() < pawnValue)
                    spaceActionRequirements.setActionValue(pawnValue);
            }
            else
                spaceActionRequirements.setActionValue(pawnValue);
        }
    }
}
