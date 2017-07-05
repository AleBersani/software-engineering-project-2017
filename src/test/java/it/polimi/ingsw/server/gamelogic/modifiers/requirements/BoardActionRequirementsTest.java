package it.polimi.ingsw.server.gamelogic.modifiers.requirements;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.player.*;
import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.PawnColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardActionRequirementsTest {
    private BoardActionRequirements boardActionRequirements;
    private Player player;

    @BeforeEach
    void setUp() {
        boardActionRequirements = new BoardActionRequirements(new SpaceActionRequirements(
                ActionType.MARKET, PawnColor.BLACK,
                4, 3, 1, false));

        player = new Player(new PlayerDetails(),
                new PlayerBoard(new BonusTiles(new Goods(), new Goods()), new Goods()));

        List<Pawn> pawns = new ArrayList<>();
        pawns.add(new Pawn(3, PawnColor.BLACK));
        player.getPlayerBoard().setPawns(pawns);
    }

    @Test
    void testEqualsTrue() {
        BoardActionRequirements boardActionRequirementsToConfront = new BoardActionRequirements(
                new SpaceActionRequirements(ActionType.MARKET, PawnColor.BLACK,
                        4, 3, 1, false));
        Player playerToConfront = new Player(new PlayerDetails(),
                new PlayerBoard(new BonusTiles(new Goods(), new Goods()), new Goods()));
        List<Pawn> pawns = new ArrayList<>();
        pawns.add(new Pawn(3, PawnColor.BLACK));
        playerToConfront.getPlayerBoard().setPawns(pawns);
        assertTrue(boardActionRequirements.equals(boardActionRequirementsToConfront));
        assertTrue(player.equals(playerToConfront));
    }

    @Test
    void testEqualsFalse() {
        BoardActionRequirements boardActionRequirementsToConfront = new BoardActionRequirements(
                new SpaceActionRequirements(ActionType.HARVEST, PawnColor.ORANGE,
                        4, 3, 1, false));
        Player playerToConfront = new Player(new PlayerDetails(),
                new PlayerBoard(new BonusTiles(new Goods(), new Goods()), new Goods()));
        List<Pawn> pawns = new ArrayList<>();
        pawns.add(new Pawn(3, PawnColor.WHITE));
        playerToConfront.getPlayerBoard().setPawns(pawns);
        assertFalse(boardActionRequirements.equals(boardActionRequirementsToConfront));
        assertFalse(player.equals(playerToConfront));
    }

    @Test
    void testEqualsDifferent() {
        String different = "";
        assertFalse(boardActionRequirements.equals(different));
        assertFalse(player.equals(different));
    }

    @Test
    void testHashCodeTrue() {
        BoardActionRequirements boardActionRequirementsToConfront = new BoardActionRequirements(
                new SpaceActionRequirements(ActionType.MARKET, PawnColor.BLACK,
                        4, 3, 1, false));
        Player playerToConfront = new Player(new PlayerDetails(),
                new PlayerBoard(new BonusTiles(new Goods(), new Goods()), new Goods()));
        List<Pawn> pawns = new ArrayList<>();
        pawns.add(new Pawn(3, PawnColor.BLACK));
        playerToConfront.getPlayerBoard().setPawns(pawns);
        assertTrue(boardActionRequirements.hashCode() == boardActionRequirementsToConfront.hashCode());
        assertTrue(player.hashCode() == playerToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        BoardActionRequirements boardActionRequirementsToConfront = new BoardActionRequirements(
                new SpaceActionRequirements(ActionType.HARVEST, PawnColor.ORANGE,
                        4, 3, 1, false));
        Player playerToConfront = new Player(new PlayerDetails(),
                new PlayerBoard(new BonusTiles(new Goods(), new Goods()), new Goods()));
        List<Pawn> pawns = new ArrayList<>();
        pawns.add(new Pawn(3, PawnColor.WHITE));
        playerToConfront.getPlayerBoard().setPawns(pawns);
        assertFalse(boardActionRequirements.hashCode() == boardActionRequirementsToConfront.hashCode());
        assertFalse(player.hashCode() == playerToConfront.hashCode());
    }

    @Test
    void testHasRequirements() {
        assertTrue(boardActionRequirements.hasRequirements(player));
    }

    @Test
    void testHasRequirementsFalse() {
        boardActionRequirements.setMalusValue(1);
        assertFalse(boardActionRequirements.hasRequirements(player));
    }
}