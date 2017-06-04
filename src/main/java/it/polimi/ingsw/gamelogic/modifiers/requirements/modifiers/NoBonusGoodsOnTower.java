package it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.TowerActionRequirements;

/**
 * Class that represents the effect of an Excommunication Tile that prevents the player to collect the Goods from
 * the Tower slots
 */
public class NoBonusGoodsOnTower extends RequirementsModifier {
    public NoBonusGoodsOnTower(AvailableActions availableActions) {
        super(availableActions);
    }

    @Override
    public TowerActionRequirements modifyRequirements(TowerActionRequirements towerActionRequirements) {
        if (availableActions.hasAvailableAction(towerActionRequirements.getActionType())) {
            TowerActionRequirements noBonusTowerActionModifier = towerActionRequirements;
            noBonusTowerActionModifier.setBonusGoods(new Goods());
            return noBonusTowerActionModifier;
        }
        return towerActionRequirements;
    }
}