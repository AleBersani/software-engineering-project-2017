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
        BasicEndGameRewards basicEndGameRewardsToConfront = new BasicEndGameRewards(
                new Points(5,0,0), new Points(10,0,0),
                new Points(15,0,0));
        assertTrue(basicEndGameRewards.equals(basicEndGameRewardsToConfront));
    }

    @Test
    void testEqualsFalse() {
        BasicEndGameRewards basicEndGameRewardsToConfront = new BasicEndGameRewards(
                new Points(0,0,0), new Points(0,0,0),
                new Points(0,0,0));
        assertFalse(basicEndGameRewards.equals(basicEndGameRewardsToConfront));
    }

    @Test
    void testHashCodeTrue() {
        BasicEndGameRewards basicEndGameRewardsToConfront = new BasicEndGameRewards(
                new Points(5,0,0), new Points(10,0,0),
                new Points(15,0,0));
        assertTrue(basicEndGameRewards.hashCode() == basicEndGameRewardsToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        BasicEndGameRewards basicEndGameRewardsToConfront = new BasicEndGameRewards(
                new Points(0,0,0), new Points(0,0,0),
                new Points(0,0,0));
        assertFalse(basicEndGameRewards.hashCode() == basicEndGameRewardsToConfront.hashCode());
    }

    @Test
    void testEqualsDifferent() {
        String different = "";
        assertFalse(basicEndGameRewards.equals(different));
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