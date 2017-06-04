package it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.TowerActionRequirements;

/**
 * Class that describes the effect of a Leader card that allows the player not to pay if he/she places a pawn on an
 * already occupied tower
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
