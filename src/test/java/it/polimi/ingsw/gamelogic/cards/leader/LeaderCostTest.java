package it.polimi.ingsw.gamelogic.cards.leader;

import it.polimi.ingsw.gamelogic.basics.CardsRequired;
import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.basics.Resources;
import it.polimi.ingsw.gamelogic.enums.GeneralColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LeaderCostTest {
    private LeaderCost leaderCost;
    private Goods goods;
    private List<CardsRequired> cardsRequiredList;

    @BeforeEach
    void setUp() {
        goods = new Goods(new Resources(4,4,4, 4));
        cardsRequiredList = new ArrayList<>();
        cardsRequiredList.add(new CardsRequired(2, GeneralColor.GREEN));
        cardsRequiredList.add(new CardsRequired(2, GeneralColor.YELLOW));
        cardsRequiredList.add(new CardsRequired(2, GeneralColor.BLUE));
        cardsRequiredList.add(new CardsRequired(2, GeneralColor.PURPLE));
    }

    @Test
    void testEqualsWithOnlyGoods() {
        leaderCost = new LeaderCost(goods);
        LeaderCost leaderCostToConfront = new LeaderCost(goods);
        assertTrue(leaderCost.equals(leaderCostToConfront));
    }

    @Test
    void testEqualsWithOnlyCardsRequiredList() {
        leaderCost = new LeaderCost(cardsRequiredList);
        LeaderCost leaderCostToConfront = new LeaderCost(cardsRequiredList);
        assertTrue(leaderCost.equals(leaderCostToConfront));
    }

    @Test
    void testEqualsWitBothAttributes() {
        leaderCost = new LeaderCost(goods, cardsRequiredList);
        LeaderCost leaderCostToConfront = new LeaderCost(goods, cardsRequiredList);
        assertTrue(leaderCost.equals(leaderCostToConfront));
    }
}