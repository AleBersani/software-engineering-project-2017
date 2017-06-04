package it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.TowerActionRequirements;

/**
 * TODO: JavaDoc
 */
public class NoMilitaryForTerritories extends RequirementsModifier {
    public NoMilitaryForTerritories(AvailableActions availableActions) {
        super(availableActions);
    }

    @Override
    public TowerActionRequirements modifyRequirements(TowerActionRequirements towerActionRequirements) {
        if (availableActions.hasAvailableAction(towerActionRequirements.getActionType())) {
            TowerActionRequirements noMilitaryForTerritoriesTowerActionModifier = towerActionRequirements;
            noMilitaryForTerritoriesTowerActionModifier.setPlayerHasEnoughMilitaryPoints(true);
            return noMilitaryForTerritoriesTowerActionModifier;
        }
        return towerActionRequirements;
    }
}
