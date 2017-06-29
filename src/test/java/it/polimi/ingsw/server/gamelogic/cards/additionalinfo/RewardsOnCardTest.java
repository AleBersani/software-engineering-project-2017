package it.polimi.ingsw.server.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.modifiers.DoubleRewards;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.modifiers.NoTowerBonusRewards;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.modifiers.RewardsModifier;
import it.polimi.ingsw.shared.model.ActionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RewardsOnCardTest {
    private RewardsOnCard rewardsOnCard;

    @BeforeEach
    void setUp() {
        rewardsOnCard = new RewardsOnCard("", new DoubleRewards(new AvailableActions(ActionType.COUNCIL_PALACE)));
    }

    @Test
    void testEqualsTrue() {
        RewardsOnCard rewardsOnCardToConfront = new RewardsOnCard("", new DoubleRewards(
                new AvailableActions(ActionType.COUNCIL_PALACE)));
        assertTrue(rewardsOnCardToConfront.equals(rewardsOnCard));
    }

    @Test
    void testEqualsFalse() {
        RewardsOnCard rewardsOnCardToConfront = new RewardsOnCard("", new DoubleRewards(
                new AvailableActions(ActionType.BLUE_TOWER)));
        assertFalse(rewardsOnCardToConfront.equals(rewardsOnCard));
    }

    @Test
    void testEqualsDifferent() {
        String obj = "";
        assertFalse(rewardsOnCard.equals(obj));
    }

    @Test
    void testHashCodeTrue() {
        RewardsOnCard rewardsOnCardToConfront = new RewardsOnCard("", new DoubleRewards(
                new AvailableActions(ActionType.COUNCIL_PALACE)));
        assertEquals(rewardsOnCardToConfront.hashCode(), rewardsOnCard.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        RewardsOnCard rewardsOnCardToConfront = new RewardsOnCard("", new DoubleRewards(
                new AvailableActions(ActionType.BLUE_TOWER)));
        assertNotEquals(rewardsOnCardToConfront.hashCode(), rewardsOnCard.hashCode());
    }

    @Test
    void getRewardsModifier() {
        RewardsModifier rewardsModifier = new NoTowerBonusRewards(new AvailableActions(ActionType.GREEN_TOWER));
        rewardsOnCard.setRewardsModifier(rewardsModifier);
        assertEquals(rewardsModifier, rewardsOnCard.getRewardsModifier());
    }

    @Test
    void getName() {
        String name = "Lorenzo";
        rewardsOnCard.setName(name);
        assertEquals(name, rewardsOnCard.getName());
    }

}