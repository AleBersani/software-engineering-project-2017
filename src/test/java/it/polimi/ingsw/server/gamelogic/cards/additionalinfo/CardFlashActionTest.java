package it.polimi.ingsw.server.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.shared.model.ActionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardFlashActionTest {
    private CardFlashAction cardFlashAction;

    @BeforeEach
    void setUp() {
        cardFlashAction = new CardFlashAction("", ActionType.BLUE_TOWER, 1);
    }


    @Test
    void testEqualsTrue1() {
        CardFlashAction cardFlashActionToConfront = new CardFlashAction("", ActionType.BLUE_TOWER, 1);
        assertTrue(cardFlashAction.equals(cardFlashActionToConfront));
    }

    @Test
    void testEqualsTrue2() {
        CardFlashAction cardFlashActionToConfront = cardFlashAction;
        assertTrue(cardFlashAction.equals(cardFlashActionToConfront));
    }

    @Test
    void testEqualsFalse1() {
        CardFlashAction cardFlashActionToConfront = new CardFlashAction("Lorenzo",
                ActionType.BLUE_TOWER, 1);
        assertFalse(cardFlashAction.equals(cardFlashActionToConfront));
    }

    @Test
    void testEqualsFalse2() {
        CardFlashAction cardFlashActionToConfront = new CardFlashAction("",
                ActionType.GREEN_TOWER, 1);
        assertFalse(cardFlashAction.equals(cardFlashActionToConfront));
    }

    @Test
    void testEqualsFalse3() {
        CardFlashAction cardFlashActionToConfront = new CardFlashAction("",
                ActionType.BLUE_TOWER, 2);
        assertFalse(cardFlashAction.equals(cardFlashActionToConfront));
    }

    @Test
    void testEqualsFalse4() {
        CardFlashAction cardFlashActionToConfront = new CardFlashAction("",
                ActionType.BLUE_TOWER, 1, new Goods(new Points(1,2,3)));
        assertFalse(cardFlashAction.equals(cardFlashActionToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(cardFlashAction.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(cardFlashAction.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        CardFlashAction cardFlashActionToConfront = new CardFlashAction("", ActionType.BLUE_TOWER, 1);
        assertEquals(cardFlashAction.hashCode(), cardFlashActionToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        CardFlashAction cardFlashActionToConfront = new CardFlashAction("", ActionType.GREEN_TOWER, 1);
        assertNotEquals(cardFlashAction.hashCode(), cardFlashActionToConfront.hashCode());
    }

    @Test
    void getActionType() {
        ActionType actionTypeToGet = ActionType.GREEN_TOWER;
        cardFlashAction.setActionType(actionTypeToGet);
        assertEquals(cardFlashAction.getActionType(), actionTypeToGet);
    }

    @Test
    void getActionValue() {
        int numberToGet = 5;
        cardFlashAction.setActionValue(numberToGet);
        assertEquals(numberToGet, cardFlashAction.getActionValue());
    }

    @Test
    void getDiscount() {
        Goods goodsToGet = new Goods(new Points(1,2,3));
        cardFlashAction.setDiscount(goodsToGet);
        assertEquals(cardFlashAction.getDiscount(), goodsToGet);
    }

    @Test
    void getName() {
        String nameToGet = "Lorenzo";
        cardFlashAction.setName(nameToGet);
        assertEquals(nameToGet, cardFlashAction.getName());
    }

}