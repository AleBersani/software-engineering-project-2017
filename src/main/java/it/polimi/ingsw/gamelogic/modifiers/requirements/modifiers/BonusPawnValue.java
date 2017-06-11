package it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers;


import it.polimi.ingsw.gamelogic.enums.PawnColor;
import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.BoardActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.SpaceActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.TowerActionRequirements;

import java.util.Objects;

/**
 * Class that represents an additional value for just one of the player's pawn
 */
public class BonusPawnValue extends RequirementsModifier {
    private PawnColor pawnColor;
    private int addedPawnValue;

    public BonusPawnValue(AvailableActions availableActions, PawnColor pawnColor, int addedPawnValue) {
        super(availableActions);
        this.pawnColor = pawnColor;
        this.addedPawnValue = addedPawnValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        BonusPawnValue that = (BonusPawnValue) o;
        return addedPawnValue == that.addedPawnValue &&
                pawnColor == that.pawnColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pawnColor, addedPawnValue);
    }

    @Override
    public BoardActionRequirements modifyRequirements(BoardActionRequirements boardActionRequirements) {
        if (availableActions.hasAvailableAction(boardActionRequirements.getActionType())) {
            BoardActionRequirements bonusPawnsBoardActionModifier = boardActionRequirements;
            bonusValueIfSameColorPawn(bonusPawnsBoardActionModifier.getSpaceActionRequirements());
            return bonusPawnsBoardActionModifier;
        }
        return boardActionRequirements;
    }

    @Override
    public TowerActionRequirements modifyRequirements(TowerActionRequirements towerActionRequirements) {
        if (availableActions.hasAvailableAction(towerActionRequirements.getActionType())) {
            TowerActionRequirements bonusPawnsTowerActionModifier = towerActionRequirements;
            bonusValueIfSameColorPawn(bonusPawnsTowerActionModifier.getSpaceActionRequirements());
            return bonusPawnsTowerActionModifier;
        }
        return towerActionRequirements;
    }

    private void bonusValueIfSameColorPawn(SpaceActionRequirements spaceActionRequirements) {
        if (pawnColor == spaceActionRequirements.getPawnColor()) {
            int actualBonusPawnValue = spaceActionRequirements.getActionValueModifier();
            spaceActionRequirements.setActionValueModifier(addedPawnValue + actualBonusPawnValue);
        }
    }
}
