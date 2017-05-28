package it.polimi.ingsw.gamelogic.decorators.rewards;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.decorators.AvailableActions;

import java.util.logging.Logger;

public class BonusRewards extends RewardsModifier {
    private final static Logger LOGGER = Logger.getLogger(BonusRewards.class.getName());

    private Goods bonus;

    public BonusRewards(AvailableActions availableActions, Goods bonus) {
        super(availableActions);
        this.bonus = bonus;
    }

    @Override
    public BasicRewards modifyRewards(BasicRewards basicRewards) {
        if (availableActions.hasAvailableAction(basicRewards.getActionType())) {
            Goods goods = basicRewards.getBonusAndMalus();
            goods.addAll(bonus);
            basicRewards.setBonusAndMalus(goods);
        }
        return basicRewards;
    }
}
