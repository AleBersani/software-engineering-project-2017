package it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.SpaceActionRequirements;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.TowerActionRequirements;
import it.polimi.ingsw.server.gamelogic.player.*;
import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.PawnColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BonusOnCardCostTest {
    private TowerActionRequirements towerActionRequirements;
    private Player player;

    private BonusOnCardCost bonusOnCardCost;

    @BeforeEach
    void setUp() {
        SpaceActionRequirements spaceActionRequirements = new SpaceActionRequirements(
                ActionType.BLUE_TOWER, PawnColor.BLACK,
                3, 3, 1, false);
        Goods requiredGoods = new Goods(new Resources(6,5,4,3));

        towerActionRequirements = new TowerActionRequirements(spaceActionRequirements, requiredGoods, new Goods(),
                new Goods(), false, false);

        player = new Player(new PlayerDetails(), new PlayerBoard(new BonusTiles(new Goods(), new Goods()),
                new Goods(new Resources(5,5,5,5))));

        List<Pawn> pawns = new ArrayList<>();
        pawns.add(new Pawn(3, PawnColor.BLACK));
        player.getPlayerBoard().setPawns(pawns);
        bonusOnCardCost = new BonusOnCardCost(new AvailableActions(ActionType.BLUE_TOWER),
                new Goods(new Resources(1,1,1,1)));
    }

    @Test
    void testEqualsTrue1() {
        BonusOnCardCost bonusOnCardCostToConfront = new BonusOnCardCost(new AvailableActions(ActionType.BLUE_TOWER),
                new Goods(new Resources(1,1,1,1)));
        assertTrue(bonusOnCardCost.equals(bonusOnCardCostToConfront));
    }

    @Test
    void testEqualsTrue2() {
        BonusOnCardCost bonusOnCardCostToConfront = bonusOnCardCost;
        assertTrue(bonusOnCardCost.equals(bonusOnCardCostToConfront));
    }

    @Test
    void testEqualsFalse1() {
        BonusOnCardCost bonusOnCardCostToConfront = new BonusOnCardCost(new AvailableActions(ActionType.HARVEST),
                new Goods(new Resources(1,1,1,1)));
        assertFalse(bonusOnCardCost.equals(bonusOnCardCostToConfront));
    }

    @Test
    void testEqualsFalse2() {
        BonusOnCardCost bonusOnCardCostToConfront = new BonusOnCardCost(new AvailableActions(ActionType.BLUE_TOWER),
                new Goods(new Resources(3,4,1,1)));
        assertFalse(bonusOnCardCost.equals(bonusOnCardCostToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        bonusOnCardCost = new BonusOnCardCost(new AvailableActions(ActionType.BLUE_TOWER),
                new Goods(new Resources(1,1,1,1)));
        String different = "";
        assertFalse(bonusOnCardCost.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        bonusOnCardCost = new BonusOnCardCost(new AvailableActions(ActionType.BLUE_TOWER),
                new Goods(new Resources(1,1,1,1)));
        assertFalse(bonusOnCardCost.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        BonusOnCardCost bonusOnCardCostToConfront = new BonusOnCardCost(new AvailableActions(ActionType.BLUE_TOWER),
                new Goods(new Resources(1,1,1,1)));
        assertEquals(bonusOnCardCost.hashCode(), bonusOnCardCostToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        BonusOnCardCost bonusOnCardCostToConfront = new BonusOnCardCost(new AvailableActions(ActionType.HARVEST),
                new Goods(new Resources(2,1,3,1)));
        assertNotEquals(bonusOnCardCost.hashCode(), bonusOnCardCostToConfront.hashCode());
    }

    @Test
    void testModifyRequirements() {
        bonusOnCardCost = new BonusOnCardCost(new AvailableActions(ActionType.BLUE_TOWER),
                new Goods(new Resources(1,1,1,1)));
        towerActionRequirements = bonusOnCardCost.modifyRequirements(towerActionRequirements);

        assertTrue(towerActionRequirements.hasRequirements(player));
    }

    @Test
    void testModifyRequirementsInsufficientBonus() {
        bonusOnCardCost = new BonusOnCardCost(new AvailableActions(ActionType.BLUE_TOWER),
                new Goods(new Resources(0,1,1,1)));
        towerActionRequirements = bonusOnCardCost.modifyRequirements(towerActionRequirements);

        assertFalse(towerActionRequirements.hasRequirements(player));
    }

    @Test
    void testGetBonus() {
        Goods discountToGet = new Goods(new Points(1,2,3));
        bonusOnCardCost.setDiscount(discountToGet);
        assertEquals(discountToGet, bonusOnCardCost.getDiscount());
    }
}