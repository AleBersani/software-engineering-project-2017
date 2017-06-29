package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
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
    private TowerSlot<Character> towerSlot;

    @BeforeEach
    void setUp() {
        towerSlot = new TowerSlot(new Space(BoardIdentifier.T_B_1, 1), new Goods(), new Character(
                new BasicDevelopmentCard(new CardInformation(1,"", PeriodNumber.FIRST , GeneralColor.BLUE),
                        new ArrayList<>())));
    }

    @Test
    void testEqualsTrue() {
        TowerSlot<Character> towerSlotToConfront = new TowerSlot(new Space(BoardIdentifier.T_B_1, 1),
                new Goods(), new Character( new BasicDevelopmentCard(
                new CardInformation(1,"", PeriodNumber.FIRST , GeneralColor.BLUE),
                new ArrayList<>())));
        assertTrue(towerSlot.equals(towerSlotToConfront));
    }

    @Test
    void testEqualsFalse() {
        TowerSlot<Character> towerSlotToConfront = new TowerSlot(new Space(BoardIdentifier.T_B_2, 4),
                new Goods(), new Character( new BasicDevelopmentCard(
                new CardInformation(1,"", PeriodNumber.SECOND , GeneralColor.BLUE),
                new ArrayList<>())));
        assertFalse(towerSlot.equals(towerSlotToConfront));
    }

    @Test
    void testEqualsDifferent() {
        String obj = "";
        assertFalse(towerSlot.equals(obj));
    }

    @Test
    void testHashCodeTrue() {
        TowerSlot<Character> towerSlotToConfront = new TowerSlot(new Space(BoardIdentifier.T_B_1, 1),
                new Goods(), new Character( new BasicDevelopmentCard(
                new CardInformation(1,"", PeriodNumber.FIRST , GeneralColor.BLUE),
                new ArrayList<>())));
        assertEquals(towerSlotToConfront.hashCode(), towerSlot.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        TowerSlot<Character> towerSlotToConfront = new TowerSlot(new Space(BoardIdentifier.T_B_2, 4),
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