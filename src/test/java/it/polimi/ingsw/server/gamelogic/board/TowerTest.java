package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.shared.model.GeneralColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TowerTest {
    private Tower tower;

    @BeforeEach
    void setUp() {
        tower = new Tower(GeneralColor.BLUE, new ArrayList<>());
    }

    @Test
    void testEquals() {
        Tower towerToConfront = new Tower(GeneralColor.BLUE, new ArrayList<>());
        assertTrue(tower.equals(towerToConfront));
    }
}