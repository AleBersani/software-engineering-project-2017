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

class BuildingTest {
    private Building building;

    @BeforeEach
    void setUp() {
        building = new Building(
                new BasicDevelopmentCard(new CardInformation(1,
                        "Name", PeriodNumber.FIRST, GeneralColor.YELLOW), new ArrayList<>()));
    }

    @Test
    void testEquals() {
        Building buildingToConfront = new Building(
                new BasicDevelopmentCard(new CardInformation(1,
                        "Name", PeriodNumber.FIRST, GeneralColor.YELLOW), new ArrayList<>()));
        assertTrue(building.equals(buildingToConfront));
    }

    @Test
    void testGetProductionActionValueRequired() {
        int numberToConfront = 4;
        building.setProductionActionValueRequired(numberToConfront);
        assertEquals(numberToConfront, building.getProductionActionValueRequired());
    }

    @Test
    void testGetProductionResult() {
        ExchangingGoods exchangingGoodsToConfront = new ExchangingGoods(new Goods(
                new Points(1,2,3)), 2);
        building.setProductionResult(exchangingGoodsToConfront);
        assertEquals(exchangingGoodsToConfront, building.getProductionResult());
    }

    @Test
    void testGetCardInformation() {
        CardInformation cardsInformationToConfront = new CardInformation(26, "Bosco",
                PeriodNumber.SECOND, GeneralColor.GREEN);
        building.getBasicDevelopmentCard().setCardInformation(cardsInformationToConfront);
        assertEquals(cardsInformationToConfront, building.getCardInformation());
    }

    @Test
    void testGetCosts() {
        List<Goods> costCard = new ArrayList<Goods>(){{add(new Goods(
                new Resources(2, 0, 0, 4),
                new Points(2, 2, 0)));}};
        building.getBasicDevelopmentCard().setCosts(costCard);
        assertEquals(costCard, building.getCosts());
    }
}