package it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.TowerActionRequirements;

/**
 * Class that describes the effect of a Leader card that allows the player to pick as many territory cards he/she
 * wants without caring about the number of Military points
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