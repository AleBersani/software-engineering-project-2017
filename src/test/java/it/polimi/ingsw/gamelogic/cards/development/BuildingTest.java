package it.polimi.ingsw.gamelogic.cards.development;

import it.polimi.ingsw.gamelogic.enums.GeneralColor;
import it.polimi.ingsw.gamelogic.enums.PeriodNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;

class BuildingTest {
    private Building building;

    @BeforeEach
    void setUp() {
        building = new Building(
                new DevelopmentCard(new CardInformation(1,
                        "Name",
                        PeriodNumber.FIRST,
                        GeneralColor.YELLOW), new ArrayList<>()));
    }

    @Test
    public void testOptionalReturn() {
        assertFalse(building.getAdditionalCardInfo().isPresent());
    }
}