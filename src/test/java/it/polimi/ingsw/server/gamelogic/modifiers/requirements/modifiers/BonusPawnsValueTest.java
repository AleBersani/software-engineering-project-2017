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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BonusPawnsValueTest {
    private BoardActionRequirements boardActionRequirements;
    private TowerActionRequirements towerActionRequirements;
    private Player player;

    private BonusPawnsValue bonusPawnsValue;

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

        List<PawnColor> pawnColors = new ArrayList<>();
        pawnColors.add(PawnColor.BLACK);
        pawnColors.add(PawnColor.ORANGE);

        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.MARKET);
        actionTypes.add(ActionType.BLUE_TOWER);
        bonusPawnsValue = new BonusPawnsValue(new AvailableActions(actionTypes), pawnColors, 2);
    }

    @Test
    void testEqualsTrue1() {
        List<PawnColor> pawnColors = new ArrayList<>();
        pawnColors.add(PawnColor.BLACK);
        pawnColors.add(PawnColor.ORANGE);
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.MARKET);
        actionTypes.add(ActionType.BLUE_TOWER);
        BonusPawnsValue bonusPawnsValueToConfront = new BonusPawnsValue(new AvailableActions(actionTypes),
                pawnColors, 2);
        assertTrue(bonusPawnsValue.equals(bonusPawnsValueToConfront));
    }

    @Test
    void testEqualsTrue2() {
        BonusPawnsValue bonusPawnsValueToConfront = bonusPawnsValue;
        assertTrue(bonusPawnsValue.equals(bonusPawnsValueToConfront));
    }

    @Test
    void testEqualsFalse1() {
        List<PawnColor> pawnColors = new ArrayList<>();
        pawnColors.add(PawnColor.BLACK);
        pawnColors.add(PawnColor.ORANGE);
        BonusPawnsValue bonusPawnsValueToConfront = new BonusPawnsValue(new AvailableActions(),
                pawnColors, 2);
        assertFalse(bonusPawnsValue.equals(bonusPawnsValueToConfront));
    }

    @Test
    void testEqualsFalse2() {
        List<PawnColor> pawnColors = new ArrayList<>();
        pawnColors.add(PawnColor.BLACK);
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.MARKET);
        actionTypes.add(ActionType.BLUE_TOWER);
        BonusPawnsValue bonusPawnsValueToConfront = new BonusPawnsValue(new AvailableActions(actionTypes),
                pawnColors, 2);
        assertFalse(bonusPawnsValue.equals(bonusPawnsValueToConfront));
    }

    @Test
    void testEqualsFalse3() {
        List<PawnColor> pawnColors = new ArrayList<>();
        pawnColors.add(PawnColor.BLACK);
        pawnColors.add(PawnColor.ORANGE);
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.MARKET);
        actionTypes.add(ActionType.BLUE_TOWER);
        BonusPawnsValue bonusPawnsValueToConfront = new BonusPawnsValue(new AvailableActions(actionTypes),
                pawnColors, 5);
        assertFalse(bonusPawnsValue.equals(bonusPawnsValueToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(bonusPawnsValue.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(bonusPawnsValue.equals(null));
    }

    @Test
    void testModifyRequirementsOfBoardAction() {
        boardActionRequirements = bonusPawnsValue.modifyRequirements(boardActionRequirements);
        assertTrue(boardActionRequirements.hasRequirements(player));
    }

    @Test
    void testModifyRequirementsOfTowerAction() {
        towerActionRequirements = bonusPawnsValue.modifyRequirements(towerActionRequirements);
        assertTrue(towerActionRequirements.hasRequirements(player));
    }
}