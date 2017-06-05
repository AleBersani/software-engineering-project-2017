package it.polimi.ingsw.gamelogic.modifiers.requirements;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.cards.leader.LeaderCard;
import it.polimi.ingsw.gamelogic.cards.leader.LeaderCost;
import it.polimi.ingsw.gamelogic.cards.leader.LeaderInformation;
import it.polimi.ingsw.gamelogic.enums.ActionType;
import it.polimi.ingsw.gamelogic.enums.LeaderCategory;
import it.polimi.ingsw.gamelogic.enums.PawnColor;
import it.polimi.ingsw.gamelogic.player.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LeaderRequirementsTest {
    private LeaderRequirements leaderRequirements;
    private Player player;

    @BeforeEach
    void setUp() {
        leaderRequirements = new LeaderRequirements(ActionType.LEADER_ACTIVATION, "Lorenzo il Magnifico",
                new LeaderCost(new Goods()));

        player = new Player(new PlayerDetails(),
                new PlayerBoard(new BonusTiles(new Goods(), new Goods()), new Goods()));

        List<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(new LeaderInformation("Lorenzo il Magnifico", LeaderCategory.PERMANENT),
                new ArrayList<>()));
        player.setLeaderCards(leaderCards);
    }

    @Test
    void testHasRequirements() {
        // assertTrue(leaderRequirements.hasRequirements(player));
    }
}