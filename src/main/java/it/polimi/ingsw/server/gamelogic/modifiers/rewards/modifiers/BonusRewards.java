package it.polimi.ingsw.server.gamelogic.modifiers.rewards.modifiers;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.BasicRewards;

import java.util.Objects;

/**
 * Class that describes the Bonus on a certain Action
 */
public class BonusRewards extends RewardsModifier {
    private Goods bonus;

    public BonusRewards(AvailableActions availableActions, Goods bonus) {
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
        BonusRewards that = (BonusRewards) o;
        return Objects.equals(bonus, that.bonus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bonus);
    }

    @Override
    public void modifyRewards(BasicRewards basicRewards) {
        if (availableActions.hasAvailableAction(basicRewards.getActionType())) {
            Goods goods = basicRewards.getBonusAndMalus();
            goods.addAll(bonus);
            basicRewards.setBonusAndMalus(goods);
        }
    }
}
