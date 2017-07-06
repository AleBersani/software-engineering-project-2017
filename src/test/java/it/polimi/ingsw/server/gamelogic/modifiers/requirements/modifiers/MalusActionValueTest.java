package it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.BoardActionRequirements;
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

class MalusActionValueTest {
    private BoardActionRequirements boardActionRequirements;
    private TowerActionRequirements towerActionRequirements;
    private Player player;

    private MalusActionValue malusActionValue;

    @BeforeEach
    void setUp() {
        boardActionRequirements = new BoardActionRequirements(new SpaceActionRequirements(
                ActionType.MARKET, PawnColor.BLACK,
                3, 2, 1, false));

        SpaceActionRequirements spaceActionRequirements = new SpaceActionRequirements(
                ActionType.BLUE_TOWER, PawnColor.ORANGE,
                3, 1, 0, false);
        Goods requiredGoods = new Goods(new Resources(5,4,3,2));

        towerActionRequirements = new TowerActionRequirements(spaceActionRequirements, requiredGoods, new Goods(),
                new Goods(), false, false);

        player = new Player(new PlayerDetails(), new PlayerBoard(new BonusTiles(new Goods(), new Goods()),
                new Goods(new Resources(5,5,5,5))));

        List<Pawn> pawns = new ArrayList<>();
        pawns.add(new Pawn(2, PawnColor.BLACK));
        pawns.add(new Pawn(1, PawnColor.ORANGE));
        player.getPlayerBoard().setPawns(pawns);

        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.BLUE_TOWER);

        malusActionValue = new MalusActionValue(
                new AvailableActions(actionTypes), 2);
    }

    @Test
    void testEqualsTrue1() {
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.BLUE_TOWER);
        MalusActionValue malusActionValueToConfront = new MalusActionValue(
                new AvailableActions(actionTypes), 2);
        assertTrue(malusActionValue.equals(malusActionValueToConfront));
    }

    @Test
    void testEqualsTrue2() {
        MalusActionValue malusActionValueToConfront = malusActionValue;
        assertTrue(malusActionValue.equals(malusActionValueToConfront));
    }

    @Test
    void testEqualsFalse1() {
        MalusActionValue malusActionValueToConfront = new MalusActionValue(new AvailableActions(ActionType.GREEN_TOWER), 2);
        assertFalse(malusActionValue.equals(malusActionValueToConfront));
    }

    @Test
    void testEqualsFalse2() {
        MalusActionValue malusActionValueToConfront = new MalusActionValue(
                new AvailableActions(ActionType.BLUE_TOWER), 3);
        assertFalse(malusActionValue.equals(malusActionValueToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(malusActionValue.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(malusActionValue.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        MalusActionValue malusActionValueToConfront = new MalusActionValue(
                new AvailableActions(ActionType.BLUE_TOWER), 2);
        assertEquals(malusActionValue.hashCode(), malusActionValueToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        MalusActionValue malusActionValueToConfront = new MalusActionValue(
                new AvailableActions(ActionType.BLUE_TOWER), 4);
        assertNotEquals(malusActionValue.hashCode(), malusActionValueToConfront.hashCode());
    }

    @Test
    void testModifyRequirementsOfBoardAction() {
        boardActionRequirements = malusActionValue.modifyRequirements(boardActionRequirements);
        assertTrue(boardActionRequirements.hasRequirements(player));
    }

    @Test
    void testModifyRequirementsOfTowerAction() {
        towerActionRequirements = malusActionValue.modifyRequirements(towerActionRequirements);
        assertFalse(towerActionRequirements.hasRequirements(player));
    }

    @Test
    void testGetMalusValue() {
        int numberToGet = 3;
        malusActionValue.setMalusValue(numberToGet);
        assertEquals(numberToGet, malusActionValue.getMalusValue());
    }
}