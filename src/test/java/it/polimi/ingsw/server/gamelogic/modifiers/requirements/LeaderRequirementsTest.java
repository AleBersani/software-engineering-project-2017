package it.polimi.ingsw.server.gamelogic.modifiers.requirements;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderCard;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderCost;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderInformation;
import it.polimi.ingsw.server.gamelogic.enums.LeaderCategory;
import it.polimi.ingsw.server.gamelogic.player.BonusTiles;
import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.server.gamelogic.player.PlayerBoard;
import it.polimi.ingsw.server.gamelogic.player.PlayerDetails;
import it.polimi.ingsw.shared.model.ActionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LeaderRequirementsTest {
    private LeaderRequirements leaderRequirements;
    private Player player;

    @BeforeEach
    void setUp() {
        leaderRequirements = new LeaderRequirements(ActionType.LEADER_PLACEMENT, "Lorenzo il Magnifico",
                new LeaderCost(new Goods()));

        player = new Player(new PlayerDetails(),
                new PlayerBoard(new BonusTiles(new Goods(), new Goods()), new Goods()));

        List<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(new LeaderInformation("Lorenzo il Magnifico", LeaderCategory.PERMANENT),
                new ArrayList<>()));
        player.setLeaderCards(leaderCards);
    }

    @Test
    void testEqualsTrue1() {
        LeaderRequirements leaderRequirementsToConfront = new LeaderRequirements(ActionType.LEADER_PLACEMENT,
                "Lorenzo il Magnifico", new LeaderCost(new Goods()));
        assertTrue(leaderRequirements.equals(leaderRequirementsToConfront));
    }

    @Test
    void testEqualsTrue2() {
        LeaderRequirements leaderRequirementsToConfront = leaderRequirements;
        assertTrue(leaderRequirements.equals(leaderRequirementsToConfront));
    }

    @Test
    void testEqualsFalse1() {
        LeaderRequirements leaderRequirementsToConfront = new LeaderRequirements(ActionType.LEADER_ACTIVATION,
                "Lorenzo il Magnifico", new LeaderCost(new Goods()));
        assertFalse(leaderRequirements.equals(leaderRequirementsToConfront));
    }

    @Test
    void testEqualsFalse2() {
        LeaderRequirements leaderRequirementsToConfront = new LeaderRequirements(ActionType.LEADER_PLACEMENT,
                "Lorenzo", new LeaderCost(new Goods()));
        assertFalse(leaderRequirements.equals(leaderRequirementsToConfront));
    }

    @Test
    void testEqualsFalse3() {
        LeaderRequirements leaderRequirementsToConfront = new LeaderRequirements(ActionType.LEADER_ACTIVATION,
                "Lorenzo il Magnifico", new LeaderCost(new Goods(new Points(1,2,3))));
        assertFalse(leaderRequirements.equals(leaderRequirementsToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(leaderRequirements.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(leaderRequirements.equals(null));
    }

    @Test
    void testHasRequirements() {
        assertTrue(leaderRequirements.hasRequirements(player));
    }

    @Test
    void testHasRequirementsWhenPlayerDoesntHaveCard() {
        leaderRequirements.setLeaderName("Lucrezia Borgia");
        assertFalse(leaderRequirements.hasRequirements(player));
    }

    @Test
    void testHasRequirementsWhenPlayerCantPay() {
        leaderRequirements.setLeaderCost(new LeaderCost(new Goods(new Points(10 ,0,0))));
        assertFalse(leaderRequirements.hasRequirements(player));
    }

    @Test
    void getActionType() {
        ActionType actionTypeToGet = ActionType.BLUE_TOWER;
        leaderRequirements.setActionType(actionTypeToGet);
        assertEquals(actionTypeToGet, leaderRequirements.getActionType());
    }

    @Test
    void getLeaderName() {
        String nameToGet = "Lorenzo";
        leaderRequirements.setLeaderName(nameToGet);
        assertEquals(nameToGet, leaderRequirements.getLeaderName());
    }

    @Test
    void getLeaderCost() {
        LeaderCost leaderCostToGet = new LeaderCost(new Goods(new Points(1,2,3)));
        leaderRequirements.setLeaderCost(leaderCostToGet);
        assertEquals(leaderCostToGet, leaderRequirements.getLeaderCost());
    }
}