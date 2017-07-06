package it.polimi.ingsw.server.gamelogic.cards.additionalinfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerOrderWeightTest {
    private PlayerOrderWeight playerOrderWeight;

    @BeforeEach
    void setUp() {
        playerOrderWeight = new PlayerOrderWeight("", 5);
    }

    @Test
    void testEqualsTrue1() {
        PlayerOrderWeight playerOrderWeightToConfront = new PlayerOrderWeight("", 5);
        assertTrue(playerOrderWeight.equals(playerOrderWeightToConfront));
    }

    @Test
    void testEqualsTrue2() {
        PlayerOrderWeight playerOrderWeightToConfront = playerOrderWeight;
        assertTrue(playerOrderWeight.equals(playerOrderWeightToConfront));
    }

    @Test
    void testEqualsFalse1() {
        PlayerOrderWeight playerOrderWeightToConfront = new PlayerOrderWeight("Lorenzo", 5);
        assertFalse(playerOrderWeight.equals(playerOrderWeightToConfront));
    }

    @Test
    void testEqualsFalse2() {
        PlayerOrderWeight playerOrderWeightToConfront = new PlayerOrderWeight("", 4);
        assertFalse(playerOrderWeight.equals(playerOrderWeightToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(playerOrderWeight.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(playerOrderWeight.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        PlayerOrderWeight playerOrderWeightToConfront = new PlayerOrderWeight("", 5);
        assertEquals(playerOrderWeight.hashCode(),playerOrderWeightToConfront.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        PlayerOrderWeight playerOrderWeightToConfront = new PlayerOrderWeight("", 4);
        assertNotEquals(playerOrderWeight.hashCode(), playerOrderWeightToConfront.hashCode());
    }

    @Test
    void testGetName() {
        String nameToGet = "Lorenzo";
        playerOrderWeight.setName(nameToGet);
        assertEquals(nameToGet, playerOrderWeight.getName());
    }

    @Test
    void testGetWeight() {
        int numberToConfront = 7;
        playerOrderWeight.setWeight(numberToConfront);
        assertEquals(numberToConfront, playerOrderWeight.getWeight());
    }

}