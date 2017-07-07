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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BasicDevelopmentCardTest {
    private BasicDevelopmentCard basicDevelopmentCard;

    @BeforeEach
    void setUp() {
        CardInformation cardInformation = new CardInformation(1, "Bosco",
                PeriodNumber.FIRST, GeneralColor.PURPLE);
        List<Goods> cardCosts = new ArrayList<>();
        cardCosts.add(new Goods(new Resources(1,2,3,4)));
        basicDevelopmentCard = new BasicDevelopmentCard(cardInformation, cardCosts);
    }

    @Test
    void testEqualsTrue1() {
        CardInformation cardInformation = new CardInformation(1, "Bosco",
                PeriodNumber.FIRST, GeneralColor.PURPLE);
        List<Goods> cardCosts = new ArrayList<>();
        cardCosts.add(new Goods(new Resources(1,2,3,4)));
        BasicDevelopmentCard basicDevelopmentCardToConfront = new BasicDevelopmentCard(cardInformation, cardCosts);
        assertTrue(basicDevelopmentCardToConfront.equals(basicDevelopmentCard));
    }

    @Test
    void testEqualsTrue2() {
        BasicDevelopmentCard basicDevelopmentCardToConfront = basicDevelopmentCard;
        assertTrue(basicDevelopmentCardToConfront.equals(basicDevelopmentCard));
    }

    @Test
    void testEqualsFalse1() {
        CardInformation cardInformation = new CardInformation(1, "Bosco",
                PeriodNumber.FIRST, GeneralColor.GREEN);
        List<Goods> cardCosts = new ArrayList<>();
        cardCosts.add(new Goods(new Resources(1,2,3,4)));
        BasicDevelopmentCard basicDevelopmentCardToConfront = new BasicDevelopmentCard(cardInformation, cardCosts);
        assertFalse(basicDevelopmentCard.equals(basicDevelopmentCardToConfront));
    }

    @Test
    void testEqualsFalse2() {
        CardInformation cardInformation = new CardInformation(1, "Bosco",
                PeriodNumber.FIRST, GeneralColor.PURPLE);
        List<Goods> cardCosts = new ArrayList<>();
        cardCosts.add(new Goods(new Resources(6,2,3,4)));
        BasicDevelopmentCard basicDevelopmentCardToConfront = new BasicDevelopmentCard(cardInformation, cardCosts);
        assertFalse(basicDevelopmentCard.equals(basicDevelopmentCardToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(basicDevelopmentCard.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(basicDevelopmentCard.equals(null));
    }

    @Test
    void testGetCardInformation() {
        CardInformation cardInformationToConfront = new CardInformation(4, "Zecca",
                PeriodNumber.SECOND, GeneralColor.GREEN);
        basicDevelopmentCard.setCardInformation(cardInformationToConfront);
        assertEquals(cardInformationToConfront, basicDevelopmentCard.getCardInformation());
    }

    @Test
    void testGetCosts() {
        List<Goods> goodsListToConfront = new ArrayList<>();
        goodsListToConfront.add(new Goods(new Resources(2,2,5,0),
                                new Points(1,2,3)));
        basicDevelopmentCard.setCosts(goodsListToConfront);
        assertEquals(goodsListToConfront, basicDevelopmentCard.getCosts());
    }
}