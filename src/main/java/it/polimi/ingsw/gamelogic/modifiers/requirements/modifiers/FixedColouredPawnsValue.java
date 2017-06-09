package it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.gamelogic.enums.PawnColor;
import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.BoardActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.SpaceActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.TowerActionRequirements;

import java.util.List;
import java.util.Objects;

/**
 * Class that represents the effect of a card that fixes the value of every player's pawn
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        FixedColouredPawnsValue that = (FixedColouredPawnsValue) o;
        return pawnsValue == that.pawnsValue &&
                Objects.equals(pawnColors, that.pawnColors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pawnColors, pawnsValue);
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
