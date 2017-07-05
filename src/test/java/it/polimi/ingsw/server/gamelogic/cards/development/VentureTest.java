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
import static org.junit.jupiter.api.Assertions.assertTrue;

class VentureTest {
    private Venture venture;
    private BasicDevelopmentCard basicDevelopmentCard;
    private Goods endGameRewards;

    @BeforeEach
    void setUp() {
        CardInformation cardInformation = new CardInformation(1, "Name",
                PeriodNumber.FIRST, GeneralColor.PURPLE);
        List<Goods> cardCosts = new ArrayList<>();
        cardCosts.add(new Goods(new Resources(1,2,3,4)));
        basicDevelopmentCard = new BasicDevelopmentCard(cardInformation, cardCosts);
        endGameRewards = new Goods(new Points(5,0,0));

        venture = new Venture(basicDevelopmentCard, endGameRewards, new ArrayList<>());
    }

    @Test
    void testEquals() {
        Venture ventureToConfront = new Venture(basicDevelopmentCard, endGameRewards, new ArrayList<>());
        assertTrue(venture.equals(ventureToConfront));
    }

    @Test
    void testGetEndGameReward() {
        Goods endGameRewardsToConfront = new Goods(new Resources(1,2,3,4));
        venture.setEndGameReward(endGameRewardsToConfront);
        assertEquals(endGameRewardsToConfront, venture.getEndGameReward());
    }

    @Test
    void testGetMinCostRequirements() {
        List<Goods> goodsToConfront = new ArrayList<Goods>(){{
            add(new Goods(new Resources(1,2,3,0)));
            add(new Goods(new Points(1,2,3)));}};
        venture.setMinCostRequirements(goodsToConfront);
        assertEquals(goodsToConfront, venture.getMinCostRequirements());
    }

}