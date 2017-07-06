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

class FixedColouredPawnsValueTest {
    private BoardActionRequirements boardActionRequirements;
    private TowerActionRequirements towerActionRequirements;
    private Player player;

    private FixedColouredPawnsValue fixedColouredPawnsValue;

    @BeforeEach
    void setUp() {
        boardActionRequirements = new BoardActionRequirements(new SpaceActionRequirements(
                ActionType.MARKET, PawnColor.BLACK,
                3, 2, 2, false));

        SpaceActionRequirements spaceActionRequirements = new SpaceActionRequirements(
                ActionType.BLUE_TOWER, PawnColor.ORANGE,
                3, 1, 2, false);
        Goods requiredGoods = new Goods(new Resources(5,4,3,2));

        towerActionRequirements = new TowerActionRequirements(spaceActionRequirements, requiredGoods, new Goods(),
                new Goods(), false, false);

        player = new Player(new PlayerDetails(), new PlayerBoard(new BonusTiles(new Goods(), new Goods()),
                new Goods(new Resources(5,5,5,5))));

        List<Pawn> pawns = new ArrayList<>();
        pawns.add(new Pawn(2, PawnColor.BLACK));
        pawns.add(new Pawn(1, PawnColor.ORANGE));
        player.getPlayerBoard().setPawns(pawns);

        List<PawnColor> pawnColors = new ArrayList<>();
        pawnColors.add(PawnColor.BLACK);
        pawnColors.add(PawnColor.ORANGE);

        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.MARKET);
        actionTypes.add(ActionType.BLUE_TOWER);

        fixedColouredPawnsValue = new FixedColouredPawnsValue(
                new AvailableActions(actionTypes), pawnColors, 5);
    }

    @Test
    void testEqualsTrue1() {
        List<PawnColor> pawnColors = new ArrayList<>();
        pawnColors.add(PawnColor.BLACK);
        pawnColors.add(PawnColor.ORANGE);
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.MARKET);
        actionTypes.add(ActionType.BLUE_TOWER);
        FixedColouredPawnsValue fixedColouredPawnsValueToConfront = new FixedColouredPawnsValue(
                new AvailableActions(actionTypes), pawnColors, 5);
        assertTrue(fixedColouredPawnsValue.equals(fixedColouredPawnsValueToConfront));
    }

    @Test
    void testEqualsTrue2() {
        FixedColouredPawnsValue fixedColouredPawnsValueToConfront = fixedColouredPawnsValue;
        assertTrue(fixedColouredPawnsValue.equals(fixedColouredPawnsValueToConfront));
    }

    @Test
    void testEqualsFalse1() {
        List<PawnColor> pawnColors = new ArrayList<>();
        pawnColors.add(PawnColor.BLACK);
        pawnColors.add(PawnColor.ORANGE);
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.BLUE_TOWER);
        FixedColouredPawnsValue fixedColouredPawnsValueToConfront = new FixedColouredPawnsValue(
                new AvailableActions(actionTypes), pawnColors, 5);
        assertFalse(fixedColouredPawnsValue.equals(fixedColouredPawnsValueToConfront));
    }

    @Test
    void testEqualsFalse2() {
        List<PawnColor> pawnColors = new ArrayList<>();
        pawnColors.add(PawnColor.BLACK);
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.MARKET);
        actionTypes.add(ActionType.BLUE_TOWER);
        FixedColouredPawnsValue fixedColouredPawnsValueToConfront = new FixedColouredPawnsValue(
                new AvailableActions(actionTypes), pawnColors, 5);
        assertFalse(fixedColouredPawnsValue.equals(fixedColouredPawnsValueToConfront));
    }

    @Test
    void testEqualsFalse3() {
        List<PawnColor> pawnColors = new ArrayList<>();
        pawnColors.add(PawnColor.BLACK);
        pawnColors.add(PawnColor.ORANGE);
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.MARKET);
        actionTypes.add(ActionType.BLUE_TOWER);
        FixedColouredPawnsValue fixedColouredPawnsValueToConfront = new FixedColouredPawnsValue(
                new AvailableActions(actionTypes), pawnColors, 7);
        assertFalse(fixedColouredPawnsValue.equals(fixedColouredPawnsValueToConfront));
    }

    @Test
    void testHashCodeTrue() {
        List<PawnColor> pawnColors = new ArrayList<>();
        pawnColors.add(PawnColor.BLACK);
        pawnColors.add(PawnColor.ORANGE);
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.MARKET);
        actionTypes.add(ActionType.BLUE_TOWER);
        FixedColouredPawnsValue fixedColouredPawnsValueToConfront = new FixedColouredPawnsValue(
                new AvailableActions(actionTypes), pawnColors, 5);
        assertEquals(fixedColouredPawnsValue.hashCode(), fixedColouredPawnsValueToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        List<PawnColor> pawnColors = new ArrayList<>();
        pawnColors.add(PawnColor.BLACK);
        pawnColors.add(PawnColor.ORANGE);
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.MARKET);
        actionTypes.add(ActionType.BLUE_TOWER);
        FixedColouredPawnsValue fixedColouredPawnsValueToConfront = new FixedColouredPawnsValue(
                new AvailableActions(actionTypes), pawnColors, 9);
        assertNotEquals(fixedColouredPawnsValue.hashCode(), fixedColouredPawnsValueToConfront.hashCode());
    }

    @Test
    void testModifyRequirementsOfBoardAction() {
        boardActionRequirements = fixedColouredPawnsValue.modifyRequirements(boardActionRequirements);
        assertTrue(boardActionRequirements.hasRequirements(player));
    }

    @Test
    void testModifyRequirementsOfTowerAction() {
        towerActionRequirements = fixedColouredPawnsValue.modifyRequirements(towerActionRequirements);
        assertTrue(towerActionRequirements.hasRequirements(player));
    }

    @Test
    void testGetPawnColors() {
        List<PawnColor> pawnColorsToGet = new ArrayList<>();
        pawnColorsToGet.add(PawnColor.BLACK);
        fixedColouredPawnsValue.setPawnColors(pawnColorsToGet);
        assertEquals(pawnColorsToGet, fixedColouredPawnsValue.getPawnColors());
    }

    @Test
    void testGetPawnsValue() {
        int pawnsValueToGet = 5;
        fixedColouredPawnsValue.setPawnsValue(pawnsValueToGet);
        assertEquals(pawnsValueToGet, fixedColouredPawnsValue.getPawnsValue());
    }
}