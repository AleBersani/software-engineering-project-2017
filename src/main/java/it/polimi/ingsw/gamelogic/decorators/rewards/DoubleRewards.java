package it.polimi.ingsw.gamelogic.decorators.rewards;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.decorators.AvailableActions;

/**
 * TODO: JavaDoc
 */
public class DoubleRewards extends RewardsModifier {
    public DoubleRewards(AvailableActions availableActions) {
        super(availableActions);
    }

    @Override
    public void modifyRewards(BasicRewards basicRewards) {
        if (availableActions.hasAvailableAction(basicRewards.getActionType())) {
            Goods copy = basicRewards.getRewardsCopy();
            Goods goods = basicRewards.getBonusAndMalus();
            goods.addAll(copy);
            basicRewards.setBonusAndMalus(goods);
        }
    }
}
