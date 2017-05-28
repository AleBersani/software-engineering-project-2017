package it.polimi.ingsw.gamelogic.decorators.rewards;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.decorators.AvailableActions;

import java.util.logging.Logger;

public class MalusRewards extends RewardsModifier {
    private final static Logger LOGGER = Logger.getLogger(MalusRewards.class.getName());

    private Goods malus;

    public MalusRewards(AvailableActions availableActions, Goods malus) {
        super(availableActions);
        this.malus = malus;
    }

    @Override
    public BasicRewards modifyRewards(BasicRewards basicRewards) {
        if (availableActions.hasAvailableAction(basicRewards.getActionType())) {
            Goods goods = basicRewards.getBonusAndMalus();
            goods.subtractAll(malus);
            basicRewards.setBonusAndMalus(goods);
        }
        return basicRewards;
    }
}
