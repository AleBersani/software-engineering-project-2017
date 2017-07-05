package it.polimi.ingsw.server.gamelogic.cards.development;

import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

class TerritoryTest {
    private Territory territory;

    @BeforeEach
    void setUp() {
        List<Goods> costCard = new ArrayList<Goods>(){{add(new Goods(
                new Resources(0, 0, 0, 4),
                new Points(2, 0, 0)));}};
        BasicDevelopmentCard card = new BasicDevelopmentCard(new CardInformation(25, "Zecca",
                PeriodNumber.FIRST, GeneralColor.GREEN), costCard);
        territory = new Territory(card, 2, new ExchangingGoods());
    }

    @Test
    void testEquals() {
        List<Goods> costCard = new ArrayList<Goods>(){{add(new Goods(
                new Resources(0, 0, 0, 4),
                new Points(2, 0, 0)));}};
        BasicDevelopmentCard card = new BasicDevelopmentCard(new CardInformation(25, "Zecca",
                PeriodNumber.FIRST, GeneralColor.GREEN), costCard);
        Territory cardToConfront = new Territory(card, 2, new ExchangingGoods());
        assertTrue(cardToConfront.equals(territory));
    }

    @Test
    void testGetHarvestActionValueRequired() {
        int numberToConfront = 4;
        territory.setHarvestActionValueRequired(numberToConfront);
        assertEquals(numberToConfront, territory.getHarvestActionValueRequired());
    }

    @Test
    void testGetHarvestResult() {
        ExchangingGoods exchangingGoodsToConfront = new ExchangingGoods(
                new Goods(new Points(1,2,3)), 2);
        territory.setHarvestResult(exchangingGoodsToConfront);
        assertEquals(exchangingGoodsToConfront, territory.getHarvestResult());
    }

    @Test
    void testGetCardInformation() {
        CardInformation cardsInformationToConfront = new CardInformation(26, "Bosco",
                PeriodNumber.SECOND, GeneralColor.GREEN);
        territory.getBasicDevelopmentCard().setCardInformation(cardsInformationToConfront);
        assertEquals(cardsInformationToConfront, territory.getCardInformation());
    }

    @Test
    void testGetCosts() {
        List<Goods> costCard = new ArrayList<Goods>(){{add(new Goods(
                new Resources(2, 0, 0, 4),
                new Points(2, 2, 0)));}};
        territory.getBasicDevelopmentCard().setCosts(costCard);
        assertEquals(costCard, territory.getCosts());
    }
}