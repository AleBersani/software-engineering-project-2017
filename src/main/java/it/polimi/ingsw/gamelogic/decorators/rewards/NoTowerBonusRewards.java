package it.polimi.ingsw.gamelogic.decorators.rewards;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.decorators.AvailableActions;

import java.util.logging.Logger;

public class NoTowerBonusRewards extends RewardsModifier {
    private final static Logger LOGGER = Logger.getLogger(NoTowerBonusRewards.class.getName());

    public NoTowerBonusRewards(AvailableActions availableActions) {
        super(availableActions);
    }

    @Override
    public BasicRewards modifyRewards(BasicRewards basicRewards) {
        if (availableActions.hasAvailableAction(basicRewards.getActionType())) {
            basicRewards.setAdditionalRewards(new Goods());
        }
        return basicRewards;
    }
}
