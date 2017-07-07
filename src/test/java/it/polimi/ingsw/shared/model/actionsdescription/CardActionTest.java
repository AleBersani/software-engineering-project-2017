package it.polimi.ingsw.shared.model.actionsdescription;

import it.polimi.ingsw.server.gamelogic.actionsdescription.CardAction;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardActionTest {
    private CardAction cardAction;

    @BeforeEach
    void setUp() {
        cardAction = new CardAction(new BasicAction(ActionType.BLUE_TOWER, BoardIdentifier.T_B_1, 1));
    }

    @Test
    void testEqualsTrue1() {
        CardAction cardActionToConfront = new CardAction(new BasicAction(ActionType.BLUE_TOWER, BoardIdentifier.T_B_1,
                1));
        assertTrue(cardAction.equals(cardActionToConfront));
    }

    @Test
    void testEqualsTrue2() {
        CardAction cardActionToConfront = cardAction;
        assertTrue(cardAction.equals(cardActionToConfront));
    }

    @Test
    void testEqualsFalse1() {
        CardAction cardActionToConfront = new CardAction(
                new BasicAction(ActionType.GREEN_TOWER, BoardIdentifier.T_B_1, 2));
        assertFalse(cardAction.equals(cardActionToConfront));
    }

    @Test
    void testEqualsFalse2() {
        CardAction cardActionToConfront = new CardAction(
                new BasicAction(ActionType.BLUE_TOWER, BoardIdentifier.T_B_1, 1), 2);
        assertFalse(cardAction.equals(cardActionToConfront));
    }

    @Test
    void testEqualsFalse3() {
        CardAction cardActionToConfront = new CardAction(
                new BasicAction(ActionType.BLUE_TOWER, BoardIdentifier.T_B_1, 1),
                new Goods(new Points(1,2,3)));
        assertFalse(cardAction.equals(cardActionToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(cardAction.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(cardAction.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        CardAction cardActionToConfront = new CardAction(new BasicAction(ActionType.BLUE_TOWER, BoardIdentifier.T_B_1,
                1));
        assertTrue(cardAction.hashCode() == cardActionToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        CardAction cardActionToConfront = new CardAction(new BasicAction(ActionType.GREEN_TOWER, BoardIdentifier.T_B_1,
                2));
        assertFalse(cardAction.hashCode() == cardActionToConfront.hashCode());
    }

    @Test
    void testGetBasicAction() {
        cardAction.setBasicAction(new BasicAction(ActionType.YELLOW_TOWER, BoardIdentifier.T_Y_1, 3));
        assertEquals(new BasicAction(ActionType.YELLOW_TOWER, BoardIdentifier.T_Y_1, 3),
                cardAction.getBasicAction());
    }

    @Test
    void testGetNumberOfServants() {
        cardAction.setNumberOfServants(3);
        assertEquals(3, cardAction.getNumberOfServants());
    }

    @Test
    void testGetDiscount() {
        cardAction.setDiscount(new Goods());
        assertEquals(new Goods(), cardAction.getDiscount());
    }
}