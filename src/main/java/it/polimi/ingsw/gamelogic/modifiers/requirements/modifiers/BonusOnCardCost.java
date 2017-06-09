package it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.TowerActionRequirements;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        BonusOnCardCost that = (BonusOnCardCost) o;
        return Objects.equals(bonus, that.bonus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bonus);
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
