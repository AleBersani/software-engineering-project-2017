package it.polimi.ingsw.gamelogic.modifiers.rewards.modifiers;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.rewards.BasicRewards;

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
    public void modifyRewards(BasicRewards basicRewards) {
        if (availableActions.hasAvailableAction(basicRewards.getActionType())) {
            Goods goods = basicRewards.getBonusAndMalus();
            goods.addAll(bonus);
            basicRewards.setBonusAndMalus(goods);
        }
    }
}
