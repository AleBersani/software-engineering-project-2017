package it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.TowerActionRequirements;

/**
 * TODO: JavaDoc
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
