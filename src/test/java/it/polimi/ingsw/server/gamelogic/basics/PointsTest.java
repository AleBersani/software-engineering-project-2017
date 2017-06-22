package it.polimi.ingsw.server.gamelogic.basics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointsTest {
    private Points points;

    @BeforeEach
    void setUp() {
        points = new Points(1,2,3);
    }

    @Test
    void testEquals() {
        Points pointsToConfront = new Points(1,2,3);
        assertTrue(points.equals(pointsToConfront));
    }

    @Test
    void testAddFixedValue() {
        Points pointsToAdd = new Points(3,2,1);
        Points expectedResult = new Points(4,4,4);
        points.add(pointsToAdd);
        assertEquals(expectedResult, points);
    }

    @Test
    void testAddGeneralValue() {
        Points pointsToAdd = new Points(1,1,1);
        Points expectedResult = new Points(2,3,4);
        points.add(pointsToAdd);
        assertEquals(expectedResult, points);
    }

    @Test
    void testReflectiveAddition() {
        Points excpectedResult = new Points(2,4,6);
        points.add(points);
        assertEquals(excpectedResult, points);
    }

    @Test
    void testSubtract() {
        Points pointsToSubtract = new Points(1,1,1);
        Points expectedResult = new Points(0,1,2);
        points.subtract(pointsToSubtract);
        assertEquals(expectedResult, points);
    }

    @Test
    void testSubtractWithNegativeResult() {
        Points pointsToSubtract = new Points(5,5,5);
        points.subtract(pointsToSubtract);
        assertEquals(new Points(-4,-3,-2), points);
    }

    @Test
    void testTrueIsLessThan() {
        Points pointsToConfront = new Points(5,4,3);
        assertTrue(points.isLessThan(pointsToConfront));
    }

    @Test
    void testFalseIsLessThan() {
        Points pointsToConfront = new Points(1,1,1);
        assertFalse(points.isLessThan(pointsToConfront));
    }

    @Test
    void testTrueIsEmpty() {
        Points pointsToTest = new Points(0,0,0);
        assertTrue(pointsToTest.isEmpty());
    }

    @Test
    void testFalseIsEmpty() {
        points.isEmpty();
    }
}