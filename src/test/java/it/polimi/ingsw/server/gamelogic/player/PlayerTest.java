package it.polimi.ingsw.server.gamelogic.player;

import it.polimi.ingsw.server.gamelogic.actions.description.ActionDescription;
import it.polimi.ingsw.server.gamelogic.actions.description.BasicAction;
import it.polimi.ingsw.server.gamelogic.actions.description.BoardAction;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderCard;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderInformation;
import it.polimi.ingsw.server.gamelogic.enums.LeaderCategory;
import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers.NoBonusGoodsOnTower;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers.RequirementsModifier;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.modifiers.DoubleRewards;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.modifiers.RewardsModifier;
import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import it.polimi.ingsw.shared.model.GeneralColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {


    private Player player;

    @BeforeEach
    void setUp() {
        PlayerDetails playerDetails = new PlayerDetails("", GeneralColor.BLUE);
        BonusTiles bonusTiles = new BonusTiles(new Goods(new Resources(1,2,3,0),
                new Points(1,2,3)),
                new Goods(new Resources(1,3,4,0)));
        PlayerBoard playerBoard = new PlayerBoard(bonusTiles, new Goods());
        player = new Player(playerDetails, playerBoard);
    }

    @Test
    void testEqualsTrue1() {
        PlayerDetails playerDetails = new PlayerDetails("", GeneralColor.BLUE);
        BonusTiles bonusTiles = new BonusTiles(new Goods(new Resources(1,2,3,0),
                new Points(1,2,3)),
                new Goods(new Resources(1,3,4,0)));
        PlayerBoard playerBoard = new PlayerBoard(bonusTiles, new Goods());
        Player playerToConfront = new Player(playerDetails, playerBoard);
        assertTrue(playerToConfront.equals(player));
    }

    @Test
    void testEqualsTrue2() {
        Player playerToConfront = player;
        assertTrue(player.equals(playerToConfront));
    }

    @Test
    void testEqualsFalse1() {
        PlayerDetails playerDetails = new PlayerDetails("", GeneralColor.GREEN);
        BonusTiles bonusTiles = new BonusTiles(new Goods(new Resources(1,2,3,0),
                new Points(1,2,3)),
                new Goods(new Resources(1,3,4,0)));
        PlayerBoard playerBoard = new PlayerBoard(bonusTiles, new Goods());
        Player playerToConfront = new Player(playerDetails, playerBoard);
        assertFalse(playerToConfront.equals(player));
    }

    @Test
    void testEqualsFalse2() {
        PlayerDetails playerDetails = new PlayerDetails("", GeneralColor.BLUE);
        BonusTiles bonusTiles = new BonusTiles(new Goods(new Resources(1,1,1,1),
                new Points(1,1,1)),
                new Goods(new Resources(1,1,1,1)));
        PlayerBoard playerBoard = new PlayerBoard(bonusTiles, new Goods());
        Player playerToConfront = new Player(playerDetails, playerBoard);
        assertFalse(playerToConfront.equals(player));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(player.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(player.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        PlayerDetails playerDetails = new PlayerDetails("", GeneralColor.BLUE);
        BonusTiles bonusTiles = new BonusTiles(new Goods(new Resources(1,2,3,0),
                new Points(1,2,3)),
                new Goods(new Resources(1,3,4,0)));
        PlayerBoard playerBoard = new PlayerBoard(bonusTiles, new Goods());
        Player playerToConfront = new Player(playerDetails, playerBoard);
        assertTrue(playerToConfront.hashCode() == player.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        PlayerDetails playerDetails = new PlayerDetails("", GeneralColor.GREEN);
        BonusTiles bonusTiles = new BonusTiles(new Goods(new Resources(1,1,1,1),
                new Points(1,1,1)),
                new Goods(new Resources(1,1,1,1)));
        PlayerBoard playerBoard = new PlayerBoard(bonusTiles, new Goods());
        Player playerToConfront = new Player(playerDetails, playerBoard);
        assertFalse(player.hashCode() == playerToConfront.hashCode());
    }

    @Test
    void testGetPlayerGoods() {
        Goods goodsToGet = new Goods(new Points(1,2,3));
        player.setPlayerGoods(goodsToGet);
        assertEquals(goodsToGet, player.getPlayerGoods());
    }

    @Test
    void testAddRequirementsModifier() {
        List<RequirementsModifier> requirementsModifiersToConfront = new ArrayList<>(player.getRequirementsModifiers());
        RequirementsModifier requirementsModifierToAdd = new NoBonusGoodsOnTower(
                new AvailableActions(ActionType.GREEN_TOWER));
        requirementsModifiersToConfront.add(requirementsModifierToAdd);
        player.addRequirementsModifier(requirementsModifierToAdd);
        assertEquals(requirementsModifiersToConfront, player.getRequirementsModifiers());
    }

    @Test
    void testAddRewardsModifier() {
        List<RewardsModifier> rewardsModifiersToConfront = new ArrayList<>(player.getRewardsModifiers());
        RewardsModifier rewardsModifierToAdd = new DoubleRewards(
                new AvailableActions(ActionType.GREEN_TOWER));
        rewardsModifiersToConfront.add(rewardsModifierToAdd);
        player.addRewardsModifier(rewardsModifierToAdd);
        assertEquals(rewardsModifiersToConfront, player.getRewardsModifiers());
    }

    @Test
    void testGetPlayerOrderWeight() {
        int numberToConfront = 5;
        player.getPlayerCardsEffects().setPlayerOrderWeight(numberToConfront);
        assertEquals(numberToConfront, player.getPlayerOrderWeight());
    }

    @Test
    void testGetPlayerDetails() {
        PlayerDetails playerDetailsToGet = new PlayerDetails("", GeneralColor.GREEN);
        player.setPlayerDetails(playerDetailsToGet);
        assertEquals(playerDetailsToGet, player.getPlayerDetails());
    }

    @Test
    void testGetLeaderCards() {
        List<LeaderCard> leaderCardsToGet = new ArrayList<>();
        leaderCardsToGet.add(new LeaderCard(new LeaderInformation("Lorenzo", LeaderCategory.PERMANENT),
                new ArrayList<>()));
        player.setLeaderCards(leaderCardsToGet);
        assertEquals(leaderCardsToGet, player.getLeaderCards());
    }

    @Test
    void testGetPossibleActionsForTurn() {
        List<ActionDescription> actionDescriptionsToGet = new ArrayList<>();
        actionDescriptionsToGet.add(new BoardAction(
                new BasicAction(ActionType.GREEN_TOWER, BoardIdentifier.T_G_3, 3)));
        player.setPossibleActionsForTurn(actionDescriptionsToGet);
        assertEquals(actionDescriptionsToGet, player.getPossibleActionsForTurn());
    }

    @Test
    void testCardFlashAction() {
        assertEquals(Optional.empty(), player.getCardFlashActions());
    }

    @Test
    void testGetPlayerBoard() {
        BonusTiles bonusTiles = new BonusTiles(new Goods(new Resources(4,1,1,1),
                new Points(1,1,6)),
                new Goods(new Resources(1,1,1,1)));
        PlayerBoard playerBoardToConfront = new PlayerBoard(bonusTiles, new Goods());
        player.setPlayerBoard(playerBoardToConfront);
        assertEquals(playerBoardToConfront, player.getPlayerBoard());
    }

    @Test
    void testPlayerCardsEffects() {
        int numberToConfront = 12;
        PlayerCardsEffects playerCardsEffects = new PlayerCardsEffects();
        playerCardsEffects.setPlayerOrderWeight(numberToConfront);
        player.setPlayerCardsEffects(playerCardsEffects);
        assertEquals(playerCardsEffects, player.getPlayerCardsEffects());
    }

}