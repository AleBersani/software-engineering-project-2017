package it.polimi.ingsw.gamelogic.decorators.rewards;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.decorators.AvailableActions;

import java.util.logging.Logger;

public class DoubleRewards extends RewardsModifier {
    private final static Logger LOGGER = Logger.getLogger(DoubleRewards.class.getName());

    public DoubleRewards(AvailableActions availableActions) {
        super(availableActions);
    }

    @Override
    public BasicRewards modifyRewards(BasicRewards basicRewards) {
        if (availableActions.hasAvailableAction(basicRewards.getActionType())) {
            Goods copy = basicRewards.getRewardsCopy();
            Goods goods = basicRewards.getBonusAndMalus();
            goods.addAll(copy);
            basicRewards.setBonusAndMalus(goods);
        }
        return basicRewards;
    }
}
