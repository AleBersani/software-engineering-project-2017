package it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.gamelogic.enums.PawnColor;
import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.BoardActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.SpaceActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.TowerActionRequirements;

import java.util.List;

/**
 * TODO: JavaDoc
 */
public class FixedColouredPawnsValue extends RequirementsModifier {
    private List<PawnColor> pawnColors;
    private int pawnsValue;

    public FixedColouredPawnsValue(AvailableActions availableActions, List<PawnColor> pawnColors, int pawnsValue) {
        super(availableActions);
        this.pawnColors = pawnColors;
        this.pawnsValue = pawnsValue;
    }

    @Override
    public BoardActionRequirements modifyRequirements(BoardActionRequirements boardActionRequirements) {
        if (availableActions.hasAvailableAction(boardActionRequirements.getActionType())) {
            BoardActionRequirements fixedColouredPawnsValueBoardActionModifier = boardActionRequirements;
            fixedValueIfSameColorPawn(fixedColouredPawnsValueBoardActionModifier.getSpaceActionRequirements());
            return fixedColouredPawnsValueBoardActionModifier;
        }
        return boardActionRequirements;
    }

    @Override
    public TowerActionRequirements modifyRequirements(TowerActionRequirements towerActionRequirements) {
        if (availableActions.hasAvailableAction(towerActionRequirements.getActionType())) {
            TowerActionRequirements fixedColouredPawnsValueTowerActionModifier = towerActionRequirements;
            fixedValueIfSameColorPawn(fixedColouredPawnsValueTowerActionModifier.getSpaceActionRequirements());
            return fixedColouredPawnsValueTowerActionModifier;
        }
        return towerActionRequirements;
    }

    private void fixedValueIfSameColorPawn(SpaceActionRequirements spaceActionRequirements) {
        if (pawnColors.stream()
                .anyMatch(predicate -> predicate == spaceActionRequirements.getPawnColor())) {
            if (spaceActionRequirements.modifiedActionValue()) {
                if (spaceActionRequirements.getActionValue() < pawnsValue)
                    spaceActionRequirements.setActionValue(pawnsValue);
            }
            else
                spaceActionRequirements.setActionValue(pawnsValue);
        }
    }
}
