package it.polimi.ingsw.gamelogic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PointsTest {
    private Points points;

    @Before
    public void setUp() throws Exception {
        points = new Points(1,2,3);
    }

    @Test
    public void testEquals() throws Exception {
        Points pointsToConfront = new Points(1,2,3);
        assertTrue(points.equals(pointsToConfront));
    }

    @Test
    public void testAddFixedValue() throws Exception {
        Points pointsToAdd = new Points(3,2,1);
        Points expectedResult = new Points(4,4,4);
        points.add(pointsToAdd);
        assertEquals(expectedResult, points);
    }

    @Test
    public void testAddGeneralValue() throws Exception {
        Points pointsToAdd = new Points(1,1,1);
        Points expectedResult = new Points(2,3,4);
        points.add(pointsToAdd);
        assertEquals(expectedResult, points);
    }

    @Test
    public void testReflectiveAddition() throws Exception {
        Points excpectedResult = new Points(2,4,6);
        points.add(points);
        assertEquals(excpectedResult, points);
    }

    @Test
    public void testSubtract() throws Exception {
        Points pointsToSubtract = new Points(1,1,1);
        Points expectedResult = new Points(0,1,2);
        points.subtract(pointsToSubtract);
        assertEquals(expectedResult, points);
    }

    @Test
    public void testSubtractWithNegativeResult()throws Exception {
        Points pointsToSubtract = new Points(5,5,5);
        points.subtract(pointsToSubtract);
        assertEquals(new Points(), points);
    }

    @Test
    public void testIsEmptyTrue() throws Exception {
        Points pointsToTest = new Points(0,0,0);
        assertTrue(pointsToTest.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() throws Exception {
        points.isEmpty();
    }

}