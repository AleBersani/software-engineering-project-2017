package it.polimi.ingsw.server.gamelogic.board;

import com.sun.javafx.image.impl.ByteGrayAlpha;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardActionSpacesTest {
    private BoardActionSpaces boardActionSpaces;

    @BeforeEach
    void setUp() {
        boardActionSpaces = new BoardActionSpaces(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    @Test
    void testEquals() {
        BoardActionSpaces boardActionSpacesToConfront = new BoardActionSpaces(new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        assertTrue(boardActionSpaces.equals(boardActionSpacesToConfront));
    }

}