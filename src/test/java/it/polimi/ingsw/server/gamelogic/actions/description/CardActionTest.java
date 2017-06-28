package it.polimi.ingsw.server.gamelogic.actions.description;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardActionTest {
    private CardAction cardAction;

    @BeforeEach
    void setUp() {
        cardAction = new CardAction(new BasicAction(ActionType.BLUE_TOWER, BoardIdentifier.T_B_1, 1));
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