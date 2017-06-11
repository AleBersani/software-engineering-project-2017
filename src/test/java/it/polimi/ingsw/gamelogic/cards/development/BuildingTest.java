package it.polimi.ingsw.gamelogic.cards.development;

import it.polimi.ingsw.gamelogic.enums.GeneralColor;
import it.polimi.ingsw.gamelogic.enums.PeriodNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
    public void testEqual() {
        Building buildingToConfront = new Building(
                new BasicDevelopmentCard(new CardInformation(1,
                        "Name", PeriodNumber.FIRST, GeneralColor.YELLOW), new ArrayList<>()));
        assertTrue(building.equals(buildingToConfront));
    }
}