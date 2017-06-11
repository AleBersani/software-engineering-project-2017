package it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.BoardActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.SpaceActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.TowerActionRequirements;
import it.polimi.ingsw.shared.model.PawnColor;

import java.util.List;
import java.util.Objects;

/**
 * Class that represents an additional value for every player's pawn
 */
public class BonusPawnsValue extends RequirementsModifier {
    private List<PawnColor> pawnColors;
    private int addedPawnsValue;

    public BonusPawnsValue(AvailableActions availableActions, List<PawnColor> pawnColors, int addedPawnsValue) {
        super(availableActions);
        this.pawnColors = pawnColors;
        this.addedPawnsValue = addedPawnsValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        BonusPawnsValue that = (BonusPawnsValue) o;
        return addedPawnsValue == that.addedPawnsValue &&
                Objects.equals(pawnColors, that.pawnColors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pawnColors, addedPawnsValue);
    }

    @Override
    public BoardActionRequirements modifyRequirements(BoardActionRequirements boardActionRequirements) {
        if (availableActions.hasAvailableAction(boardActionRequirements.getActionType())) {
            BoardActionRequirements bonusPawnBoardActionModifier = boardActionRequirements;
            bonusValueIfSameColorPawn(bonusPawnBoardActionModifier.getSpaceActionRequirements());
            return bonusPawnBoardActionModifier;
        }
        return boardActionRequirements;
    }

    @Override
    public TowerActionRequirements modifyRequirements(TowerActionRequirements towerActionRequirements) {
        if (availableActions.hasAvailableAction(towerActionRequirements.getActionType())) {
            TowerActionRequirements bonusPawnTowerActionModifier = towerActionRequirements;
            bonusValueIfSameColorPawn(bonusPawnTowerActionModifier.getSpaceActionRequirements());
            return bonusPawnTowerActionModifier;
        }
        return towerActionRequirements;
    }

    private void bonusValueIfSameColorPawn(SpaceActionRequirements spaceActionRequirements) {
        if (pawnColors.stream()
                .anyMatch(predicate -> predicate == spaceActionRequirements.getPawnColor())) {
            int actualBonusPawnsValue = spaceActionRequirements.getActionValueModifier();
            spaceActionRequirements.setActionValueModifier(addedPawnsValue + actualBonusPawnsValue);
        }
    }
}
