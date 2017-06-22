package it.polimi.ingsw.server.gamelogic.modifiers.rewards.modifiers;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.BasicRewards;

/**
 * CLass that describes the effect of a Leader Card (Santa Rita) that doubles the rewards the Player gets from
 * the Flash Effect
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
