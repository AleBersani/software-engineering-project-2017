package it.polimi.ingsw.server.gameelements;

import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.cards.development.*;
import it.polimi.ingsw.server.gamelogic.cards.development.Character;
import it.polimi.ingsw.server.gamelogic.cards.excommunicationtiles.ExcommunicationTile;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderCard;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderInformation;
import it.polimi.ingsw.server.gamelogic.enums.LeaderCategory;
import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;
import it.polimi.ingsw.shared.model.GeneralColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardsTest {
    Cards cards;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetTerritories() {
        List<Territory> territoriesToSet = new ArrayList<>();
        territoriesToSet.add(new Territory(new BasicDevelopmentCard(
                new CardInformation(1,"", PeriodNumber.FIRST, GeneralColor.GREEN),
                new ArrayList<>()), 1, new ExchangingGoods(
                        new Goods(new Points(1,2,3)))));
        cards.setTerritories(territoriesToSet);
        assertEquals(territoriesToSet, cards.getTerritories());
    }

    @Test
    void testGetBuildings() {
        List<Building> buildingsToSet = new ArrayList<>();
        buildingsToSet.add(new Building(new BasicDevelopmentCard(
                new CardInformation(9, "", PeriodNumber.FIRST, GeneralColor.YELLOW), new ArrayList<>()),
                1, new ExchangingGoods(new Goods(new Points(1,2,3)))));
        cards.setBuildings(buildingsToSet);
        assertEquals(buildingsToSet, cards.getBuildings());
    }

    @Test
    void testGetCharacters() {
        List<Character> charactersToSet = new ArrayList<>();
        charactersToSet.add(new Character(new BasicDevelopmentCard(
                new CardInformation(17, "", PeriodNumber.FIRST, GeneralColor.BLUE), new ArrayList<>())));
        cards.setCharacters(charactersToSet);
        assertEquals(charactersToSet, cards.getCharacters());
    }

    @Test
    void testGetVentures() {
        List<Venture> venturesToSet = new ArrayList<>();
        venturesToSet.add(new Venture(new BasicDevelopmentCard(
                new CardInformation(17, "", PeriodNumber.FIRST, GeneralColor.BLUE), new ArrayList<>()),
                new Goods(new Points(1,2,3)), new ArrayList<>()));
        cards.setVentures(venturesToSet);
        assertEquals(venturesToSet, cards.getVentures());
    }

    @Test
    void testGetExcommunicationTiles() {
        List<ExcommunicationTile> excommunicationTilesToSet = new ArrayList<>();
        excommunicationTilesToSet.add(new ExcommunicationTile("", PeriodNumber.FIRST));
        cards.setExcommunicationTiles(excommunicationTilesToSet);
        assertEquals(excommunicationTilesToSet, cards.getExcommunicationTiles());
    }

    @Test
    void testGetLeaderCards() {
        List<LeaderCard> leaderCardsToSet = new ArrayList<>();
        leaderCardsToSet.add(new LeaderCard(new LeaderInformation("", LeaderCategory.CONSUMABLE),
                new ArrayList<>()));
        cards.setLeaderCards(leaderCardsToSet);
        assertEquals(leaderCardsToSet, cards.getLeaderCards());
    }
}