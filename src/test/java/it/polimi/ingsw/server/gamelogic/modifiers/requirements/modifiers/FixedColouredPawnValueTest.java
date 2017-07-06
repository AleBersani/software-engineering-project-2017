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

class FixedColouredPawnValueTest {
    private BoardActionRequirements boardActionRequirements;
    private TowerActionRequirements towerActionRequirements;
    private Player player;

    private FixedColouredPawnValue fixedColouredPawnValue;

    @BeforeEach
    void setUp() {
        boardActionRequirements = new BoardActionRequirements(new SpaceActionRequirements(
                ActionType.MARKET, PawnColor.BLACK,
                3, 2, 0, false));

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
        actionTypes.add(ActionType.MARKET);

        fixedColouredPawnValue = new FixedColouredPawnValue(
                new AvailableActions(actionTypes), PawnColor.BLACK, 5);
    }

    @Test
    void testEqualsTrue1() {
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.BLUE_TOWER);
        actionTypes.add(ActionType.MARKET);
        FixedColouredPawnValue fixedColouredPawnValueToConfront = new FixedColouredPawnValue(
                new AvailableActions(actionTypes), PawnColor.BLACK, 5);
        assertTrue(fixedColouredPawnValue.equals(fixedColouredPawnValueToConfront));
    }

    @Test
    void testEqualsTrue2() {
        FixedColouredPawnValue fixedColouredPawnValueToConfront = fixedColouredPawnValue;
        assertTrue(fixedColouredPawnValue.equals(fixedColouredPawnValueToConfront));
    }

    @Test
    void testEqualsFalse1() {
        FixedColouredPawnValue fixedColouredPawnValueToConfront = new FixedColouredPawnValue(
                new AvailableActions(ActionType.BLUE_TOWER), PawnColor.BLACK, 5);
        assertFalse(fixedColouredPawnValue.equals(fixedColouredPawnValueToConfront));
    }

    @Test
    void testEqualsFalse2() {
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.BLUE_TOWER);
        actionTypes.add(ActionType.MARKET);
        FixedColouredPawnValue fixedColouredPawnValueToConfront = new FixedColouredPawnValue(
                new AvailableActions(actionTypes), PawnColor.ORANGE, 5);
        assertFalse(fixedColouredPawnValue.equals(fixedColouredPawnValueToConfront));
    }

    @Test
    void testEqualsFalse3() {
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.BLUE_TOWER);
        actionTypes.add(ActionType.MARKET);
        FixedColouredPawnValue fixedColouredPawnValueToConfront = new FixedColouredPawnValue(
                new AvailableActions(actionTypes), PawnColor.BLACK, 6);
        assertFalse(fixedColouredPawnValue.equals(fixedColouredPawnValueToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(fixedColouredPawnValue.equals(different));
    }

    @Test
    void testDifferent2() {
        assertFalse(fixedColouredPawnValue.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.BLUE_TOWER);
        actionTypes.add(ActionType.MARKET);
        FixedColouredPawnValue fixedColouredPawnValueToConfront = new FixedColouredPawnValue(
                new AvailableActions(actionTypes), PawnColor.BLACK, 5);
        assertEquals(fixedColouredPawnValue.hashCode(), fixedColouredPawnValueToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        FixedColouredPawnValue fixedColouredPawnValueToConfront = new FixedColouredPawnValue(
                new AvailableActions(ActionType.BLUE_TOWER), PawnColor.BLACK, 5);
        assertNotEquals(fixedColouredPawnValue.hashCode(), fixedColouredPawnValueToConfront.hashCode());
    }

    @Test
    void testModifyRequirementsOfBoardAction() {
        boardActionRequirements = fixedColouredPawnValue.modifyRequirements(boardActionRequirements);
        assertTrue(boardActionRequirements.hasRequirements(player));
    }

    @Test
    void testModifyRequirementsOfTowerAction() {
        towerActionRequirements = fixedColouredPawnValue.modifyRequirements(towerActionRequirements);
        assertFalse(towerActionRequirements.hasRequirements(player));
    }

    @Test
    void testGetPawnColor() {
        PawnColor colorToGet = PawnColor.WHITE;
        fixedColouredPawnValue.setPawnColor(colorToGet);
        assertEquals(colorToGet, fixedColouredPawnValue.getPawnColor());
    }

    @Test
    void testGetPawnValue() {
        int pawnValueToGet = 8;
        fixedColouredPawnValue.setPawnValue(pawnValueToGet);
        assertEquals(pawnValueToGet, fixedColouredPawnValue.getPawnValue());
    }
}