package it.polimi.ingsw.server.gamelogic.modifiers.endgamerewards.modifiers;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.cards.development.BasicDevelopmentCard;
import it.polimi.ingsw.server.gamelogic.cards.development.Building;
import it.polimi.ingsw.server.gamelogic.cards.development.CardInformation;
import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;
import it.polimi.ingsw.server.gamelogic.modifiers.endgamerewards.BasicEndGameRewards;
import it.polimi.ingsw.shared.model.GeneralColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LessVictoryBasedOnBuildingsCostsTest {
    private BasicEndGameRewards basicEndGameRewards;

    @BeforeEach
    void setUp() {
        basicEndGameRewards = new BasicEndGameRewards(new Points(5,0,0),
                new Points(10,0,0), new Points(15,0,0));
    }

    @Test
    void testEqualsTrue() {
        LessVictoryBasedOnBuildingsCosts lessVictoryBasedOnBuildingsCosts = new LessVictoryBasedOnBuildingsCosts();
        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(new Goods(new Resources(1,2,3,4)));

        List<Building> buildings = new ArrayList<>();
        buildings.add(new Building(new BasicDevelopmentCard(
                new CardInformation(1, "", PeriodNumber.FIRST, GeneralColor.YELLOW), goodsList)));
        lessVictoryBasedOnBuildingsCosts.setBuildings(buildings);
        LessVictoryBasedOnBuildingsCosts lessVictoryBasedOnBuildingsCostsToConfront =
                new LessVictoryBasedOnBuildingsCosts();
        lessVictoryBasedOnBuildingsCostsToConfront.setBuildings(buildings);
        LessVictoryBasedOnBuildingsCosts lessVictoryBasedOnBuildingsCostsToConfront2 = lessVictoryBasedOnBuildingsCosts;
        assertTrue(lessVictoryBasedOnBuildingsCosts.equals(lessVictoryBasedOnBuildingsCostsToConfront));
        assertTrue(lessVictoryBasedOnBuildingsCosts.equals(lessVictoryBasedOnBuildingsCostsToConfront2));
    }

    @Test
    void testEqualsFalse() {
        LessVictoryBasedOnBuildingsCosts lessVictoryBasedOnBuildingsCosts = new LessVictoryBasedOnBuildingsCosts();
        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(new Goods(new Resources(1,2,3,4)));

        List<Building> buildings = new ArrayList<>();
        buildings.add(new Building(new BasicDevelopmentCard(
                new CardInformation(1, "", PeriodNumber.FIRST, GeneralColor.YELLOW), goodsList)));
        lessVictoryBasedOnBuildingsCosts.setBuildings(buildings);
        buildings.get(0).getCardInformation().setNumber(4);
        LessVictoryBasedOnBuildingsCosts lessVictoryBasedOnBuildingsCostsToConfront =
                new LessVictoryBasedOnBuildingsCosts();
        assertFalse(lessVictoryBasedOnBuildingsCosts.equals(lessVictoryBasedOnBuildingsCostsToConfront));
    }

    @Test
    void testEqualsDifferent() {
        LessVictoryBasedOnBuildingsCosts lessVictoryBasedOnBuildingsCosts = new LessVictoryBasedOnBuildingsCosts();
        String different = "";
        assertFalse(lessVictoryBasedOnBuildingsCosts.equals(different));
        assertFalse(lessVictoryBasedOnBuildingsCosts.equals(null));
    }


    @Test
    void testHashCodeTrue() {
        LessVictoryBasedOnBuildingsCosts lessVictoryBasedOnBuildingsCosts = new LessVictoryBasedOnBuildingsCosts();
        LessVictoryBasedOnBuildingsCosts lessVictoryBasedOnBuildingsCostsToConfront =
                new LessVictoryBasedOnBuildingsCosts();
        assertTrue(lessVictoryBasedOnBuildingsCosts.hashCode() ==
                lessVictoryBasedOnBuildingsCostsToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        LessVictoryBasedOnBuildingsCosts lessVictoryBasedOnBuildingsCosts = new LessVictoryBasedOnBuildingsCosts();
        LessVictoryBasedOnBuildingsCosts lessVictoryBasedOnBuildingsCostsToConfront =
                new LessVictoryBasedOnBuildingsCosts();
        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(new Goods(new Resources(1,2,3,4)));
        List<Building> buildings = new ArrayList<>();
        buildings.add(new Building(new BasicDevelopmentCard(
                new CardInformation(1, "", PeriodNumber.FIRST, GeneralColor.YELLOW), goodsList)));
        lessVictoryBasedOnBuildingsCosts.setBuildings(buildings);
        assertFalse(lessVictoryBasedOnBuildingsCosts.hashCode() ==
                lessVictoryBasedOnBuildingsCostsToConfront.hashCode());
    }

    @Test
    void testModifyEndGameRewards() {
        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(new Goods(new Resources(1,2,3,4)));

        List<Building> buildings = new ArrayList<>();
        buildings.add(new Building(new BasicDevelopmentCard(
                new CardInformation(1, "", PeriodNumber.FIRST, GeneralColor.YELLOW), goodsList)));
        buildings.add(new Building(new BasicDevelopmentCard(
                new CardInformation(2, "", PeriodNumber.SECOND, GeneralColor.YELLOW), goodsList)));
        buildings.add(new Building(new BasicDevelopmentCard(
                new CardInformation(3, "", PeriodNumber.THIRD, GeneralColor.YELLOW), goodsList)));

        LessVictoryBasedOnBuildingsCosts modifier = new LessVictoryBasedOnBuildingsCosts();
        modifier.setBuildings(buildings);
        modifier.modifyEndGameRewards(basicEndGameRewards);

        assertEquals(new Points(21,0,0), basicEndGameRewards.calculateFinalEndGameRewards());
    }
}