package it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers;


import it.polimi.ingsw.gamelogic.enums.PawnColor;
import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.BoardActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.SpaceActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.TowerActionRequirements;

/**
 * TODO: JavaDoc
 */
public class BonusPawnValue extends RequirementsModifier {
    private PawnColor pawnColor;
    private int bonusPawnValue;

    public BonusPawnValue(AvailableActions availableActions, PawnColor pawnColor, int bonusPawnValue) {
        super(availableActions);
        this.pawnColor = pawnColor;
        this.bonusPawnValue = bonusPawnValue;
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
            int actualBonusPawnValue = spaceActionRequirements.getBonusActionValue();
            spaceActionRequirements.setBonusActionValue(bonusPawnValue + actualBonusPawnValue);
        }
    }
}
