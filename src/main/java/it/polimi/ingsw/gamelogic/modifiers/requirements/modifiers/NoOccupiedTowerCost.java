package it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.TowerActionRequirements;

/**
 * TODO: JavaDoc
 */
public class NoOccupiedTowerCost extends RequirementsModifier {
    public NoOccupiedTowerCost(AvailableActions availableActions) {
        super(availableActions);
    }

    @Override
    public TowerActionRequirements modifyRequirements(TowerActionRequirements towerActionRequirements) {
        if (availableActions.hasAvailableAction(towerActionRequirements.getActionType())) {
            TowerActionRequirements noOccupiedTowerCostTowerActionModifier = towerActionRequirements;
            noOccupiedTowerCostTowerActionModifier.setOccupiedTowerCost(new Goods());
            return noOccupiedTowerCostTowerActionModifier;
        }
        return towerActionRequirements;
    }
}
