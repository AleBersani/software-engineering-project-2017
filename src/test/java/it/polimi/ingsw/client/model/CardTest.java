package it.polimi.ingsw.client.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    private Card card;

    @BeforeEach
    void setUp() {
        card = new Card("");
    }

    @Test
    void testGetName() {
        String nameToSet = "";
        card.setName(nameToSet);
        assertEquals(nameToSet, card.getName());
    }
}