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
    void testEqualsTrue1() {
        RewardsOnCard rewardsOnCardToConfront = new RewardsOnCard("", new DoubleRewards(
                new AvailableActions(ActionType.COUNCIL_PALACE)));
        assertTrue(rewardsOnCardToConfront.equals(rewardsOnCard));
    }

    @Test
    void testEqualsTrue2() {
        RewardsOnCard rewardsOnCardToConfront = rewardsOnCard;
        assertTrue(rewardsOnCardToConfront.equals(rewardsOnCard));
    }

    @Test
    void testEqualsFalse1() {
        RewardsOnCard rewardsOnCardToConfront = new RewardsOnCard("Lorenzo", new DoubleRewards(
                new AvailableActions(ActionType.COUNCIL_PALACE)));
        assertFalse(rewardsOnCardToConfront.equals(rewardsOnCard));
    }

    @Test
    void testEqualsFalse2() {
        RewardsOnCard rewardsOnCardToConfront = new RewardsOnCard("", new DoubleRewards(
                new AvailableActions(ActionType.BLUE_TOWER)));
        assertFalse(rewardsOnCardToConfront.equals(rewardsOnCard));
    }

    @Test
    void testEqualsDifferent1() {
        String obj = "";
        assertFalse(rewardsOnCard.equals(obj));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(rewardsOnCard.equals(null));
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
    void testGetRewardsModifier() {
        RewardsModifier rewardsModifier = new NoTowerBonusRewards(new AvailableActions(ActionType.GREEN_TOWER));
        rewardsOnCard.setRewardsModifier(rewardsModifier);
        assertEquals(rewardsModifier, rewardsOnCard.getRewardsModifier());
    }

    @Test
    void testGetName() {
        String name = "Lorenzo";
        rewardsOnCard.setName(name);
        assertEquals(name, rewardsOnCard.getName());
    }
}