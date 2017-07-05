package it.polimi.ingsw.server.gamelogic.actions;

import it.polimi.ingsw.server.gamelogic.actions.description.LeaderAction;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderCost;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.LeaderRequirements;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.BasicRewards;
import it.polimi.ingsw.shared.model.ActionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void testEquals() {
        Action actionToConfront = action = new Action(new LeaderAction(ActionType.LEADER_ACTIVATION, ""),
                new LeaderRequirements(ActionType.LEADER_ACTIVATION, "", new LeaderCost(new Goods
                        (new Resources(1, 2, 3, 0)))), basicRewards);
        assertTrue(action.equals(actionToConfront));
    }
}