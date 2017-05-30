package it.polimi.ingsw.gamelogic.decorators.rewards;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.decorators.AvailableActions;

/**
 * TODO: JavaDoc
 */
public class NoTowerBonusRewards extends RewardsModifier {
    public NoTowerBonusRewards(AvailableActions availableActions) {
        super(availableActions);
    }

    @Override
    public void modifyRewards(BasicRewards basicRewards) {
        if (availableActions.hasAvailableAction(basicRewards.getActionType())) {
            basicRewards.setAdditionalRewards(new Goods());
        }
    }
}
