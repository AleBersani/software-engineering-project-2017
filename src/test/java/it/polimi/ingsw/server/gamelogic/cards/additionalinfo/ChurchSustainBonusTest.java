package it.polimi.ingsw.server.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChurchSustainBonusTest {
    private ChurchSustainBonus churchSustainBonus;

    @BeforeEach
    void setUp() {
        churchSustainBonus = new ChurchSustainBonus("", new Goods(new Points(1,2,3)));
    }

    @Test
    void testEqualsTrue1() {
        ChurchSustainBonus churchSustainBonusToConfront = new ChurchSustainBonus("", new Goods(new Points
                (1,2,3)));
        assertTrue(churchSustainBonus.equals(churchSustainBonusToConfront));
    }

    @Test
    void testEqualsTrue2() {
        ChurchSustainBonus churchSustainBonusToConfront = churchSustainBonus;
        assertTrue(churchSustainBonus.equals(churchSustainBonusToConfront));
    }

    @Test
    void testEqualsFalse1() {
        ChurchSustainBonus churchSustainBonusToConfront = new ChurchSustainBonus(" ", new Goods(new Points(
                1,2,3)));
        assertFalse(churchSustainBonus.equals(churchSustainBonusToConfront));
    }

    @Test
    void testEqualsFalse2() {
        ChurchSustainBonus churchSustainBonusToConfront = new ChurchSustainBonus("", new Goods(new Points(
                1,3,3)));
        assertFalse(churchSustainBonus.equals(churchSustainBonusToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(churchSustainBonus.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(churchSustainBonus.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        ChurchSustainBonus churchSustainBonusToConfront = new ChurchSustainBonus("", new Goods(new Points
                (1,2,3)));
        assertEquals(churchSustainBonus.hashCode(), churchSustainBonusToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        ChurchSustainBonus churchSustainBonusToConfront = new ChurchSustainBonus("", new Goods(new Points
                (2,3,3)));
        assertNotEquals(churchSustainBonus.hashCode(), churchSustainBonusToConfront.hashCode());
    }

    @Test
    void testGetBonus() {
        Goods bonusToGet = new Goods(new Resources(1,2,3,4));
        churchSustainBonus.setBonus(bonusToGet);
        assertEquals(bonusToGet, churchSustainBonus.getBonus());
    }

    @Test
    void testGetName() {
        String nameToGet = "Lorenzo";
        churchSustainBonus.setName(nameToGet);
        assertEquals(nameToGet, churchSustainBonus.getName());
    }

}