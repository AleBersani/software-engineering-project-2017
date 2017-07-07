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
import static org.junit.jupiter.api.Assertions.assertFalse;
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
    void testEqualsTrue1() {
        Building buildingToConfront = new Building(
                new BasicDevelopmentCard(new CardInformation(1,
                        "Name", PeriodNumber.FIRST, GeneralColor.YELLOW), new ArrayList<>()));
        assertTrue(building.equals(buildingToConfront));
    }

    @Test
    void testEqualsTrue2() {
        Building buildingToConfront = building;
        assertTrue(building.equals(buildingToConfront));
    }

    @Test
    void testEqualsFalse1() {
        Building buildingToConfront = new Building(
                new BasicDevelopmentCard(new CardInformation(5,
                        "Name", PeriodNumber.FIRST, GeneralColor.YELLOW), new ArrayList<>()));
        assertFalse(building.equals(buildingToConfront));
    }

    @Test
    void testEqualsFalse2() {
        Building buildingToConfront = new Building(
                new BasicDevelopmentCard(new CardInformation(1,
                        "Name", PeriodNumber.FIRST, GeneralColor.YELLOW), new ArrayList<>()));
        buildingToConfront.getCosts().add(new Goods(new Points(1,2,3)));
        assertFalse(building.equals(buildingToConfront));
    }

    @Test
    void testEqualsFalse3() {
        Building buildingToConfront = new Building(
                new BasicDevelopmentCard(new CardInformation(1,
                        "Name", PeriodNumber.FIRST, GeneralColor.YELLOW), new ArrayList<>()));
        buildingToConfront.setProductionResult(new ExchangingGoods(3));
        assertFalse(building.equals(buildingToConfront));
    }

    @Test
    void testEqualsFalse4() {
        Building buildingToConfront = new Building(
                new BasicDevelopmentCard(new CardInformation(1,
                        "Name", PeriodNumber.FIRST, GeneralColor.YELLOW), new ArrayList<>()));
        buildingToConfront.setProductionActionValueRequired(4);
        assertFalse(building.equals(buildingToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(building.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(building.equals(null));
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