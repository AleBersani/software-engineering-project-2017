package it.polimi.ingsw.gamelogic.modifiers.endgamerewards.modifiers;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.basics.Points;
import it.polimi.ingsw.gamelogic.basics.Resources;
import it.polimi.ingsw.gamelogic.cards.development.BasicDevelopmentCard;
import it.polimi.ingsw.gamelogic.cards.development.Building;
import it.polimi.ingsw.gamelogic.cards.development.CardInformation;
import it.polimi.ingsw.gamelogic.enums.GeneralColor;
import it.polimi.ingsw.gamelogic.enums.PeriodNumber;
import it.polimi.ingsw.gamelogic.modifiers.endgamerewards.BasicEndGameRewards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LessVictoryBasedOnBuildingsCostsTest {
    private BasicEndGameRewards basicEndGameRewards;

    @BeforeEach
    void setUp() {
        basicEndGameRewards = new BasicEndGameRewards(new Points(5,0,0),
                new Points(10,0,0), new Points(15,0,0));
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