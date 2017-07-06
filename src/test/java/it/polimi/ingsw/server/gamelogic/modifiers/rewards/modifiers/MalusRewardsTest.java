package it.polimi.ingsw.server.gamelogic.modifiers.rewards.modifiers;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.shared.model.ActionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MalusRewardsTest {
    private MalusRewards malusRewards;

    @BeforeEach
    void setUp() {
        malusRewards = new MalusRewards(new AvailableActions(ActionType.BLUE_TOWER),
                new Goods(new Points(1,2,3)));
    }

    @Test
    void testEqualsTrue1() {
        MalusRewards malusRewardsToConfront = new MalusRewards(new AvailableActions(ActionType.BLUE_TOWER),
                new Goods(new Points(1,2,3)));
        assertTrue(malusRewards.equals(malusRewardsToConfront));
    }

    @Test
    void testEqualsTrue2() {
        MalusRewards malusRewardsToConfront = malusRewards;
        assertTrue(malusRewards.equals(malusRewardsToConfront));
    }

    @Test
    void testEqualsFalse1() {
        MalusRewards malusRewardsToConfront = new MalusRewards(new AvailableActions(ActionType.GREEN_TOWER),
                new Goods(new Points(1,2,3)));
        assertFalse(malusRewards.equals(malusRewardsToConfront));
    }

    @Test
    void testEqualsFalse2() {
        MalusRewards malusRewardsToConfront = new MalusRewards(new AvailableActions(ActionType.BLUE_TOWER),
                new Goods());
        assertFalse(malusRewards.equals(malusRewardsToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(malusRewards.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(malusRewards.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        MalusRewards malusRewardsToConfront = new MalusRewards(new AvailableActions(ActionType.BLUE_TOWER),
                new Goods(new Points(1,2,3)));
        assertEquals(malusRewards.hashCode(), malusRewardsToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        MalusRewards malusRewardsToConfront = new MalusRewards(new AvailableActions(ActionType.BLUE_TOWER),
                new Goods(new Points(4,2,5)));
        assertNotEquals(malusRewards.hashCode(), malusRewardsToConfront.hashCode());
    }

    @Test
    void testGetMalus() {
        Goods malusToGet = new Goods(new Resources(1,2,3,4));
        malusRewards.setMalus(malusToGet);
        assertEquals(malusToGet, malusRewards.getMalus());
    }
}