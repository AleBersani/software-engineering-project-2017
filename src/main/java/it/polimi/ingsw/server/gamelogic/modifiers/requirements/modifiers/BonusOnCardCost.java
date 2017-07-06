package it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.TowerActionRequirements;

import java.util.Objects;

/**
 * Class that describes when it's given a Bonus on a card cost
 */
public class BonusOnCardCost extends RequirementsModifier {
    private Goods discount;

    public BonusOnCardCost(AvailableActions availableActions, Goods discount) {
        super(availableActions);
        this.discount = discount;
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
        return Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discount);
    }

    @Override
    public TowerActionRequirements modifyRequirements(TowerActionRequirements towerActionRequirements) {
        if (availableActions.hasAvailableAction(towerActionRequirements.getActionType())) {
            TowerActionRequirements bonusOnCardCost = towerActionRequirements;
            Goods actualDiscount = bonusOnCardCost.getDiscount();
            actualDiscount.addAll(discount);
            bonusOnCardCost.setDiscount(actualDiscount);
            return bonusOnCardCost;
        }
        return towerActionRequirements;
    }

    public Goods getDiscount() {
        return discount;
    }

    public void setDiscount(Goods discount) {
        this.discount = discount;
    }
}
