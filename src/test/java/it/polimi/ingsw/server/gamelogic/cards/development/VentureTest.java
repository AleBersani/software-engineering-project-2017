package it.polimi.ingsw.server.gamelogic.cards.development;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;
import it.polimi.ingsw.shared.model.GeneralColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VentureTest {
    private Venture venture;
    private BasicDevelopmentCard basicDevelopmentCard;
    private Goods endGameRewards;

    @BeforeEach
    void setUp() {
        CardInformation cardInformation = new CardInformation(1, "Name",
                PeriodNumber.FIRST, GeneralColor.PURPLE);
        List<Goods> cardCosts = new ArrayList<>();
        cardCosts.add(new Goods(new Resources(1,2,3,4)));
        basicDevelopmentCard = new BasicDevelopmentCard(cardInformation, cardCosts);
        endGameRewards = new Goods(new Points(5,0,0));

        venture = new Venture(basicDevelopmentCard, endGameRewards, new ArrayList<>());
    }

    @Test
    void testEqualsTrue1() {
        Venture ventureToConfront = new Venture(basicDevelopmentCard, endGameRewards, new ArrayList<>());
        assertTrue(venture.equals(ventureToConfront));
    }

    @Test
    void testEqualsTrue2() {
        Venture ventureToConfront = venture;
        assertTrue(venture.equals(ventureToConfront));
    }

    @Test
    void testEqualsFalse1() {
        CardInformation cardInformation = new CardInformation(1, "Name",
                PeriodNumber.SECOND, GeneralColor.PURPLE);
        basicDevelopmentCard = new BasicDevelopmentCard(cardInformation, new ArrayList<>());
        endGameRewards = new Goods(new Points(5,0,0));
        Venture ventureToConfront = new Venture(basicDevelopmentCard, endGameRewards, new ArrayList<>());
        assertFalse(venture.equals(ventureToConfront));
    }

    @Test
    void testEqualsFalse2() {
        CardInformation cardInformation = new CardInformation(1, "Name",
                PeriodNumber.FIRST, GeneralColor.PURPLE);
        basicDevelopmentCard = new BasicDevelopmentCard(cardInformation, new ArrayList<>());
        endGameRewards = new Goods(new Points(7,0,0));
        Venture ventureToConfront = new Venture(basicDevelopmentCard, endGameRewards, new ArrayList<>());
        assertFalse(venture.equals(ventureToConfront));
    }

    @Test
    void testEqualsFalse3() {
        CardInformation cardInformation = new CardInformation(1, "Name",
                PeriodNumber.FIRST, GeneralColor.PURPLE);
        basicDevelopmentCard = new BasicDevelopmentCard(cardInformation, new ArrayList<>());
        endGameRewards = new Goods(new Points(5,0,0));
        List<Goods> goods = new ArrayList<>();
        goods.add(new Goods(new Points(1,2,3)));
        Venture ventureToConfront = new Venture(basicDevelopmentCard, endGameRewards, goods);
        assertFalse(venture.equals(ventureToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(venture.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(venture.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        Venture ventureToConfront = new Venture(basicDevelopmentCard, endGameRewards, new ArrayList<>());
        assertEquals(venture.hashCode(), ventureToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        List<Goods> goods = new ArrayList<>();
        goods.add(new Goods(new Points(1,2,3)));
        Venture ventureToConfront = new Venture(basicDevelopmentCard, endGameRewards, goods);
        assertNotEquals(venture.hashCode(), ventureToConfront.hashCode());
    }

    @Test
    void testGetEndGameReward() {
        Goods endGameRewardsToConfront = new Goods(new Resources(1,2,3,4));
        venture.setEndGameReward(endGameRewardsToConfront);
        assertEquals(endGameRewardsToConfront, venture.getEndGameReward());
    }

    @Test
    void testGetMinCostRequirements() {
        List<Goods> goodsToConfront = new ArrayList<Goods>(){{
            add(new Goods(new Resources(1,2,3,0)));
            add(new Goods(new Points(1,2,3)));}};
        venture.setMinCostRequirements(goodsToConfront);
        assertEquals(goodsToConfront, venture.getMinCostRequirements());
    }
}