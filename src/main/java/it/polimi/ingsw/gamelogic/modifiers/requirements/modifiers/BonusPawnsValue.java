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
public class BonusPawnsValue extends RequirementsModifier {
    private List<PawnColor> pawnColors;
    private int bonusPawnsValue;

    public BonusPawnsValue(AvailableActions availableActions, List<PawnColor> pawnColors, int bonusPawnsValue) {
        super(availableActions);
        this.pawnColors = pawnColors;
        this.bonusPawnsValue = bonusPawnsValue;
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
            int actualBonusPawnsValue = spaceActionRequirements.getBonusActionValue();
            spaceActionRequirements.setBonusActionValue(bonusPawnsValue + actualBonusPawnsValue);
        }
    }
}
