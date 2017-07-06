package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.cards.development.BasicDevelopmentCard;
import it.polimi.ingsw.server.gamelogic.cards.development.CardInformation;
import it.polimi.ingsw.server.gamelogic.cards.development.Character;
import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import it.polimi.ingsw.shared.model.GeneralColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TowerSlotTest {
    private TowerSlot towerSlot;

    @BeforeEach
    void setUp() {
        towerSlot = new TowerSlot(new Space(BoardIdentifier.T_B_1, 1), new Goods(), new Character(
                new BasicDevelopmentCard(new CardInformation(1,"", PeriodNumber.FIRST , GeneralColor.BLUE),
                        new ArrayList<>())));
    }

    @Test
    void testEqualsTrue1() {
        TowerSlot towerSlotToConfront = new TowerSlot(new Space(BoardIdentifier.T_B_1, 1),
                new Goods(), new Character( new BasicDevelopmentCard(
                new CardInformation(1,"", PeriodNumber.FIRST , GeneralColor.BLUE),
                new ArrayList<>())));
        assertTrue(towerSlot.equals(towerSlotToConfront));
    }

    @Test
    void testEqualsTrue2() {
        TowerSlot towerSlotToConfront = towerSlot;
        assertTrue(towerSlot.equals(towerSlotToConfront));
    }

    @Test
    void testEqualsFalse1() {
        TowerSlot towerSlotToConfront = new TowerSlot(new Space(BoardIdentifier.T_G_1, 1),
                new Goods(), new Character( new BasicDevelopmentCard(
                new CardInformation(1,"", PeriodNumber.FIRST , GeneralColor.BLUE),
                new ArrayList<>())));
        assertFalse(towerSlot.equals(towerSlotToConfront));
    }

    @Test
    void testEqualsFalse2() {
        TowerSlot towerSlotToConfront = new TowerSlot(new Space(BoardIdentifier.T_B_1, 1),
                new Goods(new Points(1,2,3)), new Character( new BasicDevelopmentCard(
                new CardInformation(1,"", PeriodNumber.FIRST , GeneralColor.BLUE),
                new ArrayList<>())));
        assertFalse(towerSlot.equals(towerSlotToConfront));
    }

    @Test
    void testEqualsFalse3() {
        TowerSlot towerSlotToConfront = new TowerSlot(new Space(BoardIdentifier.T_B_1, 1),
                new Goods(), new Character( new BasicDevelopmentCard(
                new CardInformation(5,"", PeriodNumber.FIRST , GeneralColor.BLUE),
                new ArrayList<>())));
        assertFalse(towerSlot.equals(towerSlotToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(towerSlot.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(towerSlot.equals(null));
    }

    @Test
    void testHashCodeTrue() {
        TowerSlot towerSlotToConfront = new TowerSlot(new Space(BoardIdentifier.T_B_1, 1),
                new Goods(), new Character( new BasicDevelopmentCard(
                new CardInformation(1,"", PeriodNumber.FIRST , GeneralColor.BLUE),
                new ArrayList<>())));
        assertEquals(towerSlotToConfront.hashCode(), towerSlot.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        TowerSlot towerSlotToConfront = new TowerSlot(new Space(BoardIdentifier.T_B_2, 4),
                new Goods(), new Character( new BasicDevelopmentCard(
                new CardInformation(1,"", PeriodNumber.SECOND , GeneralColor.BLUE),
                new ArrayList<>())));
        assertNotEquals(towerSlotToConfront.hashCode(), towerSlot.hashCode());
    }

    @Test
    void testGetSpace() {
        Space spaceToConfront = new Space(BoardIdentifier.T_G_3, 4);
        towerSlot.setSpace(spaceToConfront);
        assertEquals(spaceToConfront, towerSlot.getSpace());
    }

    @Test
    void testGetInstantGoods() {
        Goods instantGoodsToConfront = new Goods(new Resources(1,2,3,4));
        towerSlot.setInstantGoods(instantGoodsToConfront);
        assertEquals(instantGoodsToConfront, towerSlot.getInstantGoods());
    }

    @Test
    void testGetDevelopmentCard() {
        Character characterToConfront = new Character(new BasicDevelopmentCard(
                new CardInformation(1,"Bosco", PeriodNumber.FIRST, GeneralColor.YELLOW),
                new ArrayList<>()));
        towerSlot.setDevelopmentCard(characterToConfront);
        assertEquals(characterToConfront, towerSlot.getDevelopmentCard());
    }
}