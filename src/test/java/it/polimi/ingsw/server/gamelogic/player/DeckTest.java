package it.polimi.ingsw.server.gamelogic.player;

import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.cards.development.*;
import it.polimi.ingsw.server.gamelogic.cards.development.Character;
import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;
import it.polimi.ingsw.shared.model.GeneralColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeckTest {
    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @Test
    void testEquals() {
        Deck deckToConfront = new Deck();
        assertTrue(deckToConfront.equals(deck));
    }

    @Test
    void testGetNumberOfTerritories() {
        List<Goods> costCard = new ArrayList<Goods>(){{add(new Goods(
                new Resources(0, 0, 0, 4),
                new Points(2, 0, 0)));}};
        BasicDevelopmentCard card = new BasicDevelopmentCard(new CardInformation(25, "Zecca",
                PeriodNumber.FIRST, GeneralColor.GREEN), costCard);
        Territory territory = new Territory(card, 2, new ExchangingGoods());
        deck.getTerritories().add(territory);
        assertEquals(1, deck.getNumberOfTerritories());
    }

    @Test
    void testGetNumberOfBuildings() {
        List<Goods> costCard = new ArrayList<Goods>(){{add(new Goods(
                new Resources(0, 0, 0, 4),
                new Points(2, 0, 0)));}};
        BasicDevelopmentCard card = new BasicDevelopmentCard(new CardInformation(25, "Zecca",
                PeriodNumber.FIRST, GeneralColor.GREEN), costCard);
        Building building = new Building(card, 2, new ExchangingGoods());
        deck.getBuildings().add(building);
        assertEquals(1, deck.getNumberOfBuildings());
    }

    @Test
    void testGetNumberOfCharacters() {
        List<Goods> costCard = new ArrayList<Goods>(){{add(new Goods(
                new Resources(0, 0, 0, 4),
                new Points(2, 0, 0)));}};
        BasicDevelopmentCard card = new BasicDevelopmentCard(new CardInformation(25, "Zecca",
                PeriodNumber.FIRST, GeneralColor.GREEN), costCard);
        Character character = new Character(card);
        deck.getCharacters().add(character);
        assertEquals(1, deck.getNumberOfCharacters());
    }

    @Test
    void testGetNumberOfVentures() {
        List<Goods> costCard = new ArrayList<Goods>(){{add(new Goods(
                new Resources(0, 0, 0, 4),
                new Points(2, 0, 0)));}};
        BasicDevelopmentCard card = new BasicDevelopmentCard(new CardInformation(25, "Zecca",
                PeriodNumber.FIRST, GeneralColor.GREEN), costCard);
        Venture venture = new Venture(card, new Goods(), new ArrayList<>());
        deck.getVentures().add(venture);
        assertEquals(1, deck.getNumberOfVentures());
    }

    @Test
    void testGetTerritories() {
        List<Goods> costCard = new ArrayList<Goods>(){{add(new Goods(
                new Resources(0, 0, 0, 4),
                new Points(2, 0, 0)));}};
        BasicDevelopmentCard card = new BasicDevelopmentCard(new CardInformation(25, "Zecca",
                PeriodNumber.FIRST, GeneralColor.GREEN), costCard);
        Territory territory = new Territory(card, 2, new ExchangingGoods());
        List<Territory> territoriesToConfront = new ArrayList<>();
        territoriesToConfront.add(territory);
        deck.setTerritories(territoriesToConfront);
        assertEquals(territoriesToConfront, deck.getTerritories());
    }

    @Test
    void testGetBuildings() {
        List<Goods> costCard = new ArrayList<Goods>(){{add(new Goods(
                new Resources(0, 0, 0, 4),
                new Points(2, 0, 0)));}};
        BasicDevelopmentCard card = new BasicDevelopmentCard(new CardInformation(25, "Zecca",
                PeriodNumber.FIRST, GeneralColor.GREEN), costCard);
        Building building = new Building(card, 2, new ExchangingGoods());
        List<Building> buildingsToConfront = new ArrayList<>();
        buildingsToConfront.add(building);
        deck.setBuildings(buildingsToConfront);
        assertEquals(buildingsToConfront, deck.getBuildings());
    }

    @Test
    void testGetCharacters() {
        List<Goods> costCard = new ArrayList<Goods>(){{add(new Goods(
                new Resources(0, 0, 0, 4),
                new Points(2, 0, 0)));}};
        BasicDevelopmentCard card = new BasicDevelopmentCard(new CardInformation(25, "Zecca",
                PeriodNumber.FIRST, GeneralColor.GREEN), costCard);
        Character character = new Character(card);
        List<Character> charactersToConfront = new ArrayList<>();
        charactersToConfront.add(character);
        deck.setCharacters(charactersToConfront);
        assertEquals(charactersToConfront, deck.getCharacters());
    }

    @Test
    void testGetVentures() {
        List<Goods> costCard = new ArrayList<Goods>(){{add(new Goods(
                new Resources(0, 0, 0, 4),
                new Points(2, 0, 0)));}};
        BasicDevelopmentCard card = new BasicDevelopmentCard(new CardInformation(25, "Zecca",
                PeriodNumber.FIRST, GeneralColor.GREEN), costCard);
        Venture venture = new Venture(card, new Goods(), new ArrayList<>());
        List<Venture> venturesToConfront = new ArrayList<>();
        venturesToConfront.add(venture);
        deck.setVentures(venturesToConfront);
        assertEquals(venturesToConfront, deck.getVentures());
    }

}