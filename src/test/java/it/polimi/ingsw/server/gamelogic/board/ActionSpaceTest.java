package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionSpaceTest {
    private ActionSpace actionSpace;

    @BeforeEach
    void setUp() {
        actionSpace = new ActionSpace(new Space(BoardIdentifier.HARVEST_1, 1), 2);
    }

    @Test
    void testEquals() {
        ActionSpace actionSpaceToConfront = new ActionSpace(new Space(BoardIdentifier.HARVEST_1,
                1), 2);
        assertTrue(actionSpace.equals(actionSpaceToConfront));
    }

}