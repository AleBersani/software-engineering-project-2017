package it.polimi.ingsw.server.gamelogic.modifiers.rewards.modifiers;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.BasicRewards;

/**
 * Class that describes the effect of an Excommunication Tile that prevents the Player to collect the instantGoods
 * of the Tower slots
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
