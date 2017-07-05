package it.polimi.ingsw.server.gamelogic.cards.development;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;
import it.polimi.ingsw.shared.model.GeneralColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CharacterTest {
    private Character character;
    private BasicDevelopmentCard basicDevelopmentCard;

    @BeforeEach
    void setUp() {
        CardInformation cardInformation = new CardInformation(1, "Name",
                PeriodNumber.FIRST, GeneralColor.BLUE);
        List<Goods> cardCosts = new ArrayList<>();
        cardCosts.add(new Goods(new Resources(1,2,3,4)));
        basicDevelopmentCard = new BasicDevelopmentCard(cardInformation, cardCosts);

        character = new Character(basicDevelopmentCard);
    }

    @Test
    void testEquals() {
        Character characterToConfront = new Character(basicDevelopmentCard);
        assertTrue(character.equals(characterToConfront));
    }

}