package it.polimi.ingsw.gamelogic.modifiers.rewards.modifiers;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.rewards.BasicRewards;

/**
 * CLass that describes an effect that reduces the Player's Goods
 */
public class MalusRewards extends RewardsModifier {
    private Goods malus;

    public MalusRewards(AvailableActions availableActions, Goods malus) {
        super(availableActions);
        this.malus = malus;
    }

    @Override
    public void modifyRewards(BasicRewards basicRewards) {
        if (availableActions.hasAvailableAction(basicRewards.getActionType())) {
            Goods goods = basicRewards.getBonusAndMalus();
            goods.subtractAll(malus);
            basicRewards.setBonusAndMalus(goods);
        }
    }
}
