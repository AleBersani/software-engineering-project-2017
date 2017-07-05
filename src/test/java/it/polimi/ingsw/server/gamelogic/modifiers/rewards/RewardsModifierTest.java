package it.polimi.ingsw.server.gamelogic.modifiers.rewards;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.modifiers.*;
import it.polimi.ingsw.shared.model.ActionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class RewardsModifierTest {
    private BasicRewards basicRewards;
    private List<ActionType> actionTypes;
    private AvailableActions availableActions;
    private List<RewardsModifier> rewardsModifiers;

    @BeforeEach
    void setUp() {
        Goods basicGoods = new Goods(new Points(5,5,5));
        Goods additionalGoods = new Goods(new Resources(1,1,1,1));
        basicRewards = new BasicRewards(ActionType.MARKET, basicGoods, additionalGoods);

        actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.PRODUCTION);
        actionTypes.add(ActionType.MARKET);

        availableActions = new AvailableActions(actionTypes);

        Goods bonusGoods = new Goods(new Resources(1,2,3,4));
        Goods malusGoods = new Goods(new Points(1,1,1));
        RewardsModifier bonusRewards = new BonusRewards(availableActions, bonusGoods);
        RewardsModifier malusRewards = new MalusRewards(availableActions, malusGoods);
        RewardsModifier doubleRewards = new DoubleRewards(availableActions);
        RewardsModifier noTowerBonusRewards = new NoTowerBonusRewards(availableActions);

        rewardsModifiers = new ArrayList<>();
        rewardsModifiers.add(bonusRewards);
        rewardsModifiers.add(malusRewards);
        rewardsModifiers.add(doubleRewards);
        rewardsModifiers.add(noTowerBonusRewards);
    }

    @Test
    void testCalculateFinalRewards() {
        rewardsModifiers.forEach(rewardsDecorator -> rewardsDecorator.modifyRewards(basicRewards));

        Goods expected = new Goods(
                new Resources(1,2,3,4),
                new Points(9,9,9));

        assertEquals(expected, basicRewards.calculateFinalRewards());
    }

    @Test
    void testEquals() {
        assertFalse(rewardsModifiers.get(0).equals(null));
    }
}