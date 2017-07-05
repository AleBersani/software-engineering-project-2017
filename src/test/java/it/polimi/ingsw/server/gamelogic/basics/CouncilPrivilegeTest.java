package it.polimi.ingsw.server.gamelogic.basics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CouncilPrivilegeTest {
    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetPossibleChoices() {
        List<Goods> goodsToSet = new ArrayList<>();
        goodsToSet.add(new Goods(new Points(1,2,3)));
        CouncilPrivilege.setPossibleChoices(goodsToSet);
        assertEquals(goodsToSet, CouncilPrivilege.getPossibleChoices());
    }
}