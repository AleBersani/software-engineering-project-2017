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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BonusPawnValueTest {
    private BoardActionRequirements boardActionRequirements;
    private TowerActionRequirements towerActionRequirements;
    private Player player;

    private BonusPawnValue bonusPawnValue;

    @BeforeEach
    void setUp() {
        boardActionRequirements = new BoardActionRequirements(new SpaceActionRequirements(
                ActionType.MARKET, PawnColor.BLACK,
                5, 2, 0, false));

        SpaceActionRequirements spaceActionRequirements = new SpaceActionRequirements(
                ActionType.BLUE_TOWER, PawnColor.BLACK,
                5, 2, 0, false);
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
        actionTypes.add(ActionType.MARKET);
        actionTypes.add(ActionType.BLUE_TOWER);
        bonusPawnValue = new BonusPawnValue(new AvailableActions(actionTypes), PawnColor.BLACK, 4);
    }

    @Test
    void testEqualsTrue1() {
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.MARKET);
        actionTypes.add(ActionType.BLUE_TOWER);
        BonusPawnValue bonusPawnValueToConfront = new BonusPawnValue(new AvailableActions(actionTypes),
                PawnColor.BLACK, 4);
        assertTrue(bonusPawnValue.equals(bonusPawnValueToConfront));
    }

    @Test
    void testEqualsTrue2() {
        BonusPawnValue bonusPawnValueToConfront = bonusPawnValue;
        assertTrue(bonusPawnValue.equals(bonusPawnValueToConfront));
    }

    @Test
    void testEqualsFalse1() {
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.BLUE_TOWER);
        BonusPawnValue bonusPawnValueToConfront = new BonusPawnValue(new AvailableActions(actionTypes),
                PawnColor.BLACK, 4);
        assertFalse(bonusPawnValue.equals(bonusPawnValueToConfront));
    }

    @Test
    void testEqualsFalse2() {
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.MARKET);
        actionTypes.add(ActionType.BLUE_TOWER);
        BonusPawnValue bonusPawnValueToConfront = new BonusPawnValue(new AvailableActions(actionTypes),
                PawnColor.WHITE, 4);
        assertFalse(bonusPawnValue.equals(bonusPawnValueToConfront));
    }

    @Test
    void testEqualsFalse3() {
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.MARKET);
        actionTypes.add(ActionType.BLUE_TOWER);
        BonusPawnValue bonusPawnValueToConfront = new BonusPawnValue(new AvailableActions(actionTypes),
                PawnColor.BLACK, 5);
        assertFalse(bonusPawnValue.equals(bonusPawnValueToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(bonusPawnValue.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(bonusPawnValue.equals(null));
    }

    @Test
    void testModifyRequirementsOfBoardAction() {
        boardActionRequirements = bonusPawnValue.modifyRequirements(boardActionRequirements);
        assertTrue(boardActionRequirements.hasRequirements(player));
    }

    @Test
    void testModifyRequirementsOfTowerAction() {
        towerActionRequirements = bonusPawnValue.modifyRequirements(towerActionRequirements);
        assertTrue(towerActionRequirements.hasRequirements(player));
    }

    @Test
    void testGetPawnColor() {
        PawnColor colorToGet = PawnColor.ORANGE;
        bonusPawnValue.setPawnColor(colorToGet);
        assertEquals(colorToGet, bonusPawnValue.getPawnColor());
    }

    @Test
    void testGetPawnValue() {
        int pawnValueToGet = 6;
        bonusPawnValue.setAddedPawnValue(pawnValueToGet);
        assertEquals(pawnValueToGet, bonusPawnValue.getAddedPawnValue());
    }
}