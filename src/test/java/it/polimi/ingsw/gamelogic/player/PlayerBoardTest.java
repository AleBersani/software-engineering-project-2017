package it.polimi.ingsw.gamelogic.player;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.shared.model.PawnColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerBoardTest {
    private PlayerBoard playerBoard;

    @BeforeEach
    void setUp() {
        playerBoard = new PlayerBoard(new BonusTiles(new Goods(), new Goods()), new Goods());

        List<Pawn> pawns = new ArrayList<>();
        pawns.add(new Pawn(4, PawnColor.BLACK));
        pawns.add(new Pawn(5, PawnColor.ORANGE));
        pawns.add(new Pawn(6, PawnColor.WHITE));
        pawns.add(new Pawn(0, PawnColor.NEUTRAL));

        playerBoard.setPawns(pawns);
    }

    @Test
    void testGetPawnValueGivenColor() {
        assertEquals(6, playerBoard.getPawnValueGivenColor(PawnColor.WHITE));
    }

    @Test
    void testGetPawnGivenColor() {
        Pawn expectedPawn = new Pawn(0, PawnColor.NEUTRAL);
        assertEquals(expectedPawn, playerBoard.getPawnGivenColor(PawnColor.NEUTRAL).get());
    }

}