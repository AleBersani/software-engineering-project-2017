package it.polimi.ingsw.server.gamelogic.actions;

import it.polimi.ingsw.server.gamelogic.actions.description.ActionDescription;
import it.polimi.ingsw.server.gamelogic.actions.description.BasicAction;
import it.polimi.ingsw.server.gamelogic.actions.description.LeaderAction;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderCost;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.LeaderRequirements;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.TowerActionRequirements;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.BasicRewards;
import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActionTest {
    private Action action;
    private List<BasicRewards> basicRewards;

    @BeforeEach
    void setUp() {
        basicRewards = new ArrayList<BasicRewards>(){{add(new BasicRewards(ActionType.LEADER_ACTIVATION, new Goods(
                new Resources(1,2,3,0))));}};
        action = new Action(new LeaderAction(ActionType.LEADER_ACTIVATION, ""),
                new LeaderRequirements(ActionType.LEADER_ACTIVATION, "", new LeaderCost(new Goods
                        (new Resources(1, 2, 3, 0)))), basicRewards);
    }

    @Test
    void testEqualsTrue() {
        Action actionToConfront = new Action(new LeaderAction(ActionType.LEADER_ACTIVATION, ""),
                new LeaderRequirements(ActionType.LEADER_ACTIVATION, "", new LeaderCost(new Goods
                        (new Resources(1, 2, 3, 0)))), basicRewards);
        assertTrue(action.equals(actionToConfront));
    }

    @Test
    void testEqualsFalse() {
        Action actionToConfront = new Action(new LeaderAction(ActionType.LEADER_ACTIVATION, ""),
                new LeaderRequirements(ActionType.LEADER_ACTIVATION, "", new LeaderCost(new Goods
                        (new Resources(1, 1, 1, 1)))), basicRewards);
        assertFalse(action.equals(actionToConfront));
    }

    @Test
    void testEqualsDifferent() {
        String different = "";
        assertFalse(action.equals(different));
    }

    @Test
    void testEqualsNull() {
        assertFalse(action.equals(null));
    }

    @Test
    void testEqualsObject() {
        Action actionToConfront = action;
        assertTrue(action.equals(actionToConfront));
    }

    @Test
    void testHashCodeTrue() {
        Action actionToConfront = new Action(new LeaderAction(ActionType.LEADER_ACTIVATION, ""),
                new LeaderRequirements(ActionType.LEADER_ACTIVATION, "", new LeaderCost(new Goods
                        (new Resources(1, 2, 3, 0)))), basicRewards);
        assertEquals(action.hashCode(), actionToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        Action actionToConfront = new Action(new LeaderAction(ActionType.LEADER_ACTIVATION, ""),
                new LeaderRequirements(ActionType.LEADER_ACTIVATION, "", new LeaderCost(new Goods
                        (new Resources(1, 1, 1, 1)))), basicRewards);
        assertNotEquals(action.hashCode(), actionToConfront.hashCode());
    }

    @Test
    void testGetRequiredRequirements() {
        LeaderRequirements requirementsToSet = new LeaderRequirements(ActionType.LEADER_ACTIVATION,
                "", new LeaderCost(new Goods(new Points(1,2,3))));
        action.setRequiredRequirements(requirementsToSet);
        assertEquals(requirementsToSet, action.getRequiredRequirements());
    }

    @Test
    void testGetBasicRewardsList() {
        List<BasicRewards> basicRewardsToSet = new ArrayList<>();
        basicRewardsToSet.add(new BasicRewards(ActionType.BLUE_TOWER, new Goods(
                new Points(1,2,3))));
        action.setBasicRewardsList(basicRewardsToSet);
        assertEquals(basicRewardsToSet, action.getBasicRewardsList());
    }
}