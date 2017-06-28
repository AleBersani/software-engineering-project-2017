package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.cards.development.BasicDevelopmentCard;
import it.polimi.ingsw.server.gamelogic.cards.development.CardInformation;
import it.polimi.ingsw.server.gamelogic.cards.development.Character;
import it.polimi.ingsw.server.gamelogic.cards.development.DevelopmentCard;
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
    void testEquals() {
        TowerSlot<Character> towerSlotToConfront = new TowerSlot(new Space(BoardIdentifier.T_B_1, 1),
                new Goods(), new Character( new BasicDevelopmentCard(
                        new CardInformation(1,"", PeriodNumber.FIRST , GeneralColor.BLUE),
                new ArrayList<>())));
        assertTrue(towerSlot.equals(towerSlotToConfront));
    }
}