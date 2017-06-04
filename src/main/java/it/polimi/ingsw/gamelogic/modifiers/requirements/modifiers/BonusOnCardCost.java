package it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.TowerActionRequirements;

/**
 * Class that describes when it's given a Bonus on a card cost
 */
public class BonusOnCardCost extends RequirementsModifier {
    private Goods bonus;

    public BonusOnCardCost(AvailableActions availableActions, Goods bonus) {
        super(availableActions);
        this.bonus = bonus;
    }

    @Override
    public TowerActionRequirements modifyRequirements(TowerActionRequirements towerActionRequirements) {
        if (availableActions.hasAvailableAction(towerActionRequirements.getActionType())) {
            TowerActionRequirements bonusOnCardCost = towerActionRequirements;
            Goods actualDiscount = bonusOnCardCost.getDiscount();
            actualDiscount.addAll(bonus);
            bonusOnCardCost.setDiscount(actualDiscount);
            return bonusOnCardCost;
        }
        return towerActionRequirements;
    }
}
