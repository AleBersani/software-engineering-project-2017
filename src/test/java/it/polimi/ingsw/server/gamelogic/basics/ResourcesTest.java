package it.polimi.ingsw.server.gamelogic.basics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourcesTest {
    private Resources resources;

    @BeforeEach
    void setUp() {
        resources = new Resources(1,2,3,4);
    }

    @Test
    void testEqualsTrue1() {
        Resources resourcesToConfront = new Resources(1,2,3,4);
        assertTrue(resources.equals(resourcesToConfront));
    }

    @Test
    void testEqualsTrue2() {
        Resources resourcesToConfront = resources;
        assertTrue(resources.equals(resourcesToConfront));
    }

    @Test
    void testEqualsFalse1() {
        Resources resourcesToConfront = new Resources(2,2,3,4);
        assertFalse(resources.equals(resourcesToConfront));
    }

    @Test
    void testEqualsFalse2() {
        Resources resourcesToConfront = new Resources(1,3,3,4);
        assertFalse(resources.equals(resourcesToConfront));
    }

    @Test
    void testEqualsFalse3() {
        Resources resourcesToConfront = new Resources(1,2,4,4);
        assertFalse(resources.equals(resourcesToConfront));
    }

    @Test
    void testEqualsFalse4() {
        Resources resourcesToConfront = new Resources(1,2,3,5);
        assertFalse(resources.equals(resourcesToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(resources.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(resources.equals(null));
    }

    @Test
    void testAddFixedValue() {
        Resources resourcesToAdd = new Resources(1,2,3,4);
        resources.add(resourcesToAdd);
        assertTrue(resources.equals(new Resources(2,4,6,8)));
    }

    @Test
    void testAddGeneralValue() {
        Resources resourcesToAdd = new Resources(4,3,2,1);
        resources.add(resourcesToAdd);
        assertEquals(new Resources(5,5,5,5), resources);
    }

    @Test
    void testSubtractFixedValue() {
        Resources resourcesToSubtract = new Resources(1,2,3,4);
        resources.subtract(resourcesToSubtract);
        assertEquals(new Resources(), resources);
    }

    @Test
    void testSubtractGeneralValue() {
        Resources resourcesToSubtract = new Resources(1,1,1,1);
        resources.subtract(resourcesToSubtract);
        assertEquals(new Resources(0,1,2,3), resources);
    }

    @Test
    void testSubtractWithNegativeResult() {
        Resources resourcesToSubtract = new Resources(2,3,4,5);
        resources.subtract(resourcesToSubtract);
        assertEquals(new Resources(-1,-1,-1,-1), resources);
    }

    @Test
    void testTrueIsLessThan() {
        Resources resourcesToConfront = new Resources(5,5,5,5);
        assertTrue(resources.isLessThan(resourcesToConfront));
    }

    @Test
    void testFalseIsLessThan() {
        Resources resourcesToConfront = new Resources(1,1,1,1);
        assertFalse(resources.isLessThan(resourcesToConfront));
    }

    @Test
    void testTrueIsEmpty() {
        Resources emptyResources = new Resources();
        assertTrue(emptyResources.isEmpty());
    }

    @Test
    void testFalseIsEmpty() {
        assertFalse(resources.isEmpty());
    }

    @Test
    void testGetWoods() {
        int numberToGet = 8;
        resources.setWoods(numberToGet);
        assertEquals(numberToGet, resources.getWoods());
    }

    @Test
    void testGetServants() {
        int numberToGet = 8;
        resources.setServants(numberToGet);
        assertEquals(numberToGet, resources.getServants());
    }

    @Test
    void testGetStones() {
        int numberToGet = 8;
        resources.setStones(numberToGet);
        assertEquals(numberToGet, resources.getStones());
    }

    @Test
    void testGetCoins() {
        int numberToGet = 8;
        resources.setCoins(numberToGet);
        assertEquals(numberToGet, resources.getCoins());
    }
}