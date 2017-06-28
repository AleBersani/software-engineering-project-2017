package it.polimi.ingsw.server.gamelogic.cards.leader;

import it.polimi.ingsw.client.model.Card;
import it.polimi.ingsw.server.gamelogic.basics.CardsRequired;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.enums.LeaderCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LeaderCardTest {
    private LeaderCard leaderCard;

    @BeforeEach
    void setUp() {
        LeaderInformation leaderInformation = new LeaderInformation("Lorenzo Il Magnifico",
                                                                    LeaderCategory.CONSUMABLE);
        List<LeaderCost> leaderCosts = new ArrayList<LeaderCost>(){{add(new LeaderCost(new Goods(new Resources(1,1,2,3)),
                                                                                        new ArrayList<CardsRequired>()));}};
        leaderCard = new LeaderCard(leaderInformation, leaderCosts);
    }

    @Test
    void testEquals() {
        LeaderCard leaderCardToConfront;
        LeaderInformation leaderInformation = new LeaderInformation("Lorenzo Il Magnifico",
                LeaderCategory.CONSUMABLE);
        List<LeaderCost> leaderCosts = new ArrayList<LeaderCost>(){{add(new LeaderCost(new Goods(
                new Resources(1,1,2,3)),
                new ArrayList<>()));}};
        leaderCardToConfront = new LeaderCard(leaderInformation, leaderCosts);
        assertTrue(leaderCardToConfront.equals(leaderCard));
    }

    @Test
    void testGetLeaderName() {
        String name = "Francesco Sforza";
        leaderCard.getLeaderInformation().setName(name);
        assertEquals(name, leaderCard.getLeaderName());
    }

    @Test
    void testGetLeaderCategory() {
        leaderCard.getLeaderInformation().setLeaderCategory(LeaderCategory.PERMANENT);
        assertEquals(LeaderCategory.PERMANENT, leaderCard.getLeaderCategory());
    }

    @Test
    void testGetLeaderInformation() {
        LeaderInformation leaderInformationToConfront;
        leaderInformationToConfront = new LeaderInformation("Pico Della Mirandola",
                LeaderCategory.PERMANENT);
        leaderCard.setLeaderInformation(leaderInformationToConfront);
        assertEquals(leaderInformationToConfront, leaderCard.getLeaderInformation());
    }

    @Test
    void testGetLeaderCosts() {
        List<LeaderCost> leaderCostsToConfront = new ArrayList<LeaderCost>(){{add(new LeaderCost(new Goods(
                new Resources(2,1,5,3)),
                new ArrayList<>()));}};
        leaderCard.setLeaderCosts(leaderCostsToConfront);
        assertEquals(leaderCostsToConfront, leaderCard.getLeaderCosts());
    }

    @Test
    void testIsPlacedOnBoard() {
        leaderCard.setPlacedOnBoard(true);
        assertEquals(true, leaderCard.isPlacedOnBoard());
    }

    @Test
    void testIsPlayable() {
        leaderCard.setPlayable(false);
        assertEquals(false, leaderCard.isPlayable());
    }
}