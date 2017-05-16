package it.polimi.ingsw.gamelogic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResourcesTest {
    Resources resources;

    @Before
    public void setUp() throws Exception {
        resources = new Resources(1,2,3,4);
    }

    @Test
    public void testEquals() throws Exception {
        Resources resourcesToConfront = new Resources(1,2,3,4);
        assertTrue(resources.equals(resourcesToConfront));
    }

    @Test
    public void testAddFixedValue() throws Exception {
        Resources resourcesToAdd = new Resources(1,2,3,4);
        resources.add(resourcesToAdd);
        assertTrue(resources.equals(new Resources(2,4,6,8)));
    }

    @Test
    public void testAddGeneralValue() throws Exception {
        Resources resourcesToAdd = new Resources(4,3,2,1);
        resources.add(resourcesToAdd);
        assertEquals(new Resources(5,5,5,5), resources);
    }

    @Test
    public void testSubtractFixedValue() throws Exception {
        Resources resourcesToSubtract = new Resources(1,2,3,4);
        resources.subtract(resourcesToSubtract);
        assertEquals(new Resources(), resources);
    }

    @Test
    public void testSubtractGeneralValue() throws Exception {
        Resources resourcesToSubtract = new Resources(1,1,1,1);
        resources.subtract(resourcesToSubtract);
        assertEquals(new Resources(0,1,2,3), resources);
    }

    @Test
    public void testSubtractWithNegativeResult() throws Exception {
        Resources resourcesToSubtract = new Resources(2,3,4,5);
        resources.subtract(resourcesToSubtract);
        assertEquals(new Resources(), resources);
    }

    @Test
    public void testIsEmptyTrue() throws Exception {
        Resources emptyResources = new Resources();
        assertTrue(emptyResources.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() throws Exception {
        assertFalse(resources.isEmpty());
    }
}