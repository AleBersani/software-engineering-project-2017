package it.polimi.ingsw.gamelogic.decorators.rewards;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.decorators.AvailableActions;

/**
 * TODO: JavaDoc
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
