package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import it.polimi.ingsw.shared.model.GeneralColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TowerTest {
    private Tower tower;

    @BeforeEach
    void setUp() {
        tower = new Tower(GeneralColor.BLUE, new ArrayList<>());
    }

    @Test
    void testEqualsTrue1() {
        Tower towerToConfront = new Tower(GeneralColor.BLUE, new ArrayList<>());
        assertTrue(tower.equals(towerToConfront));
    }

    @Test
    void testEqualsTrue2() {
        Tower towerToConfront = tower;
        assertTrue(tower.equals(towerToConfront));
    }

    @Test
    void testEqualsFalse1() {
        Tower towerToConfront = new Tower(GeneralColor.GREEN, new ArrayList<>());
        assertFalse(tower.equals(towerToConfront));
    }

    @Test
    void testEqualsFalse2() {
        List<TowerSlot> towerSlots = new ArrayList<>();
        towerSlots.add(new TowerSlot(new Space(BoardIdentifier.T_G_3, 2), new Goods()));
        Tower towerToConfront = new Tower(GeneralColor.BLUE, towerSlots);
        assertFalse(tower.equals(towerToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(tower.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(tower.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        Tower towerToConfront = new Tower(GeneralColor.BLUE, new ArrayList<>());
        assertEquals(tower.hashCode(), towerToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        Tower towerToConfront = new Tower(GeneralColor.GREEN, new ArrayList<>());
        assertNotEquals(tower.hashCode(), towerToConfront.hashCode());
    }

    @Test
    void testGetColor() {
        GeneralColor colorToGet = GeneralColor.GREEN;
        tower.setColor(colorToGet);
        assertEquals(colorToGet, tower.getColor());
    }

    @Test
    void testGetTowerSlot() {
        List<TowerSlot> towerSlotsToGet = new ArrayList<>();
        towerSlotsToGet.add(new TowerSlot(new Space(BoardIdentifier.T_G_3, 2), new Goods()));
        tower.setTowerSlots(towerSlotsToGet);
        assertEquals(towerSlotsToGet, tower.getTowerSlots());
    }
}