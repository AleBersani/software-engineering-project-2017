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
public class MalusColouredPawns extends RequirementsModifier {
    private List<PawnColor> pawnColors;
    private int value;

    public MalusColouredPawns(AvailableActions availableActions, List<PawnColor> pawnColors, int value) {
        super(availableActions);
        this.pawnColors = pawnColors;
        this.value = value;
    }

    @Override
    public BoardActionRequirements modifyRequirements(BoardActionRequirements boardActionRequirements) {
        if (availableActions.hasAvailableAction(boardActionRequirements.getActionType())) {
            BoardActionRequirements malusColouredPawnsBoardActionModifier = boardActionRequirements;
            bonusValueIfSameColorPawn(malusColouredPawnsBoardActionModifier.getSpaceActionRequirements());
            return malusColouredPawnsBoardActionModifier;
        }
        return boardActionRequirements;
    }

    @Override
    public TowerActionRequirements modifyRequirements(TowerActionRequirements towerActionRequirements) {
        if (availableActions.hasAvailableAction(towerActionRequirements.getActionType())) {
            TowerActionRequirements malusColouredPawnsTowerActionModifier = towerActionRequirements;
            bonusValueIfSameColorPawn(malusColouredPawnsTowerActionModifier.getSpaceActionRequirements());
            return malusColouredPawnsTowerActionModifier;
        }
        return towerActionRequirements;
    }

    private void bonusValueIfSameColorPawn(SpaceActionRequirements spaceActionRequirements) {
        if (pawnColors.stream()
                .anyMatch(predicate -> predicate == spaceActionRequirements.getPawnColor())) {
            int actualBonusValue = spaceActionRequirements.getBonusActionValue();
            spaceActionRequirements.setBonusActionValue(actualBonusValue - value);
        }
    }
}
