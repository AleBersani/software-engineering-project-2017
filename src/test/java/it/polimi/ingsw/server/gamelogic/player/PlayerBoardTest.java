package it.polimi.ingsw.server.gamelogic.player;


import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.cards.development.*;
import it.polimi.ingsw.server.gamelogic.cards.development.Character;
import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;
import it.polimi.ingsw.shared.model.GeneralColor;
import it.polimi.ingsw.shared.model.PawnColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void testFirstConstructor() {
        PlayerBoard playerBoard1 = new PlayerBoard(new Goods(new Points(1,2,3)));
        PlayerBoard playerBoard2 = new PlayerBoard(new Goods(new Points(1,2,3)));
        assertEquals(playerBoard1, playerBoard2);
    }

    @Test
    void testEquals() {
        PlayerBoard playerBoardToConfront;
        playerBoardToConfront = new PlayerBoard(new BonusTiles(new Goods(), new Goods()), new Goods());
        List<Pawn> pawns = new ArrayList<>();
        pawns.add(new Pawn(4, PawnColor.BLACK));
        pawns.add(new Pawn(5, PawnColor.ORANGE));
        pawns.add(new Pawn(6, PawnColor.WHITE));
        pawns.add(new Pawn(0, PawnColor.NEUTRAL));
        playerBoardToConfront.setPawns(pawns);
        assertTrue(playerBoardToConfront.equals(playerBoard));
    }

    @Test
    void testGetProductionBonus() {
        Goods goodsToConfront = new Goods();
        assertEquals(goodsToConfront, playerBoard.getProductionBonus());
    }

    @Test
    void testGetHarvestBonus() {
        Goods goodsToConfront = new Goods();
        assertEquals(goodsToConfront, playerBoard.getHarvestBonus());
    }

    @Test
    void testGetNumberOfTerritories() {
        List<Goods> costCard = new ArrayList<Goods>(){{add(new Goods(
                new Resources(0, 0, 0, 4),
                new Points(2, 0, 0)));}};
        BasicDevelopmentCard card = new BasicDevelopmentCard(new CardInformation(25, "Zecca",
                PeriodNumber.FIRST, GeneralColor.GREEN), costCard);
        playerBoard.getDeck().getTerritories().add(new Territory(card, 1,
                new ExchangingGoods()));
        int numberToConfront = 1;
        assertEquals(numberToConfront, playerBoard.getNumberOfTerritories());
    }

    @Test
    void testGetNumberOfBuildings() {
        List<Goods> costCard = new ArrayList<Goods>(){{add(new Goods(
                new Resources(0, 0, 0, 4),
                new Points(2, 0, 0)));}};
        BasicDevelopmentCard card = new BasicDevelopmentCard(new CardInformation(25, "Zecca",
                PeriodNumber.FIRST, GeneralColor.YELLOW), costCard);
        playerBoard.getDeck().getBuildings().add(new Building(card, 1,
                new ExchangingGoods()));
        int numberToConfront = 1;
        assertEquals(numberToConfront, playerBoard.getNumberOfBuildings());
    }

    @Test
    void testGetNumberOfCharacters() {
        List<Goods> costCard = new ArrayList<Goods>(){{add(new Goods(
                new Resources(0, 0, 0, 4),
                new Points(2, 0, 0)));}};
        BasicDevelopmentCard card = new BasicDevelopmentCard(new CardInformation(25, "Zecca",
                PeriodNumber.FIRST, GeneralColor.BLUE), costCard);
        playerBoard.getDeck().getCharacters().add(new Character(card));
        int numberToConfront = 1;
        assertEquals(numberToConfront, playerBoard.getNumberOfCharacters());
    }

    @Test
    void testGetNumberOfVentures() {
        List<Goods> costCard = new ArrayList<Goods>(){{add(new Goods(
                new Resources(0, 0, 0, 4),
                new Points(2, 0, 0)));}};
        BasicDevelopmentCard card = new BasicDevelopmentCard(new CardInformation(25, "Zecca",
                PeriodNumber.FIRST, GeneralColor.PURPLE), costCard);
        List<Goods> goodsList = new ArrayList<Goods>(){{add(new Goods());}};
        playerBoard.getDeck().getVentures().add(new Venture(card, new Goods(), goodsList));
        int numberToConfront = 1;
        assertEquals(numberToConfront, playerBoard.getNumberOfVentures());
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

    @Test
    void testGetBonusTiles() {
        BonusTiles expectedBonusTiles = new BonusTiles(new Goods(new Resources(1,1,1,0)),
                                                        new Goods(new Resources(1,2,3,0)));
        playerBoard.setBonusTiles(expectedBonusTiles);
        assertEquals(expectedBonusTiles, playerBoard.getBonusTiles());
    }

    @Test
    void testGetGoods() {
        Goods expectedGoods = new Goods(new Resources(1,2,3,0));
        playerBoard.setGoods(expectedGoods);
        assertEquals(expectedGoods, playerBoard.getGoods());
    }

    @Test
    void testGetDeck() {
        Deck expectedDeck = new Deck();
        List<Goods> costCard = new ArrayList<Goods>(){{add(new Goods(
                new Resources(0, 0, 0, 4),
                new Points(2, 0, 0)));}};
        BasicDevelopmentCard card = new BasicDevelopmentCard(new CardInformation(25, "Zecca",
                PeriodNumber.FIRST, GeneralColor.GREEN), costCard);
        expectedDeck.getTerritories().add(new Territory(card, 1, new ExchangingGoods()));
        playerBoard.setDeck(expectedDeck);
        assertEquals(expectedDeck, playerBoard.getDeck());
    }

    @Test
    void testGetPawns() {
        List<Pawn> expectedPawns = new ArrayList<>();
        expectedPawns.add(new Pawn(4, PawnColor.BLACK));
        expectedPawns.add(new Pawn(5, PawnColor.ORANGE));
        expectedPawns.add(new Pawn(6, PawnColor.WHITE));
        expectedPawns.add(new Pawn(0, PawnColor.NEUTRAL));
        assertEquals(expectedPawns, playerBoard.getPawns());
    }

}