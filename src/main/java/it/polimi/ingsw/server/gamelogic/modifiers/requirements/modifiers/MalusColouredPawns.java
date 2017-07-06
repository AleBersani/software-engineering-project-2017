package it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.BoardActionRequirements;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.SpaceActionRequirements;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.TowerActionRequirements;
import it.polimi.ingsw.shared.model.PawnColor;

import java.util.List;
import java.util.Objects;

/**
 * Class that represents the effect of an Excommunication Tile that reduces the value of the player's coloured pawns
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        MalusColouredPawns that = (MalusColouredPawns) o;
        return value == that.value &&
                Objects.equals(pawnColors, that.pawnColors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pawnColors, value);
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
            int actualBonusValue = spaceActionRequirements.getActionValueModifier();
            spaceActionRequirements.setActionValueModifier(actualBonusValue - value);
        }
    }

    public List<PawnColor> getPawnColors() {
        return pawnColors;
    }

    public void setPawnColors(List<PawnColor> pawnColors) {
        this.pawnColors = pawnColors;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
