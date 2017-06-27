package it.polimi.ingsw.server.gamecontroller.gameelements;

import it.polimi.ingsw.server.gamelogic.cards.development.Building;
import it.polimi.ingsw.server.gamelogic.cards.development.Character;
import it.polimi.ingsw.server.gamelogic.cards.development.Territory;
import it.polimi.ingsw.server.gamelogic.cards.development.Venture;
import it.polimi.ingsw.server.gamelogic.cards.excommunicationtiles.ExcommunicationTile;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderCard;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static List<Territory> territories;
    private static List<Building> buildings;
    private static List<Character> characters;
    private static List<Venture> ventures;
    private static List<ExcommunicationTile> excommunicationTiles;
    private static List<LeaderCard> leaderCards;

    public static void initializeCards() {
        territories = new ArrayList<>();
        buildings = new ArrayList<>();
        characters = new ArrayList<>();
        ventures = new ArrayList<>();
        excommunicationTiles = new ArrayList<>();
        leaderCards = new ArrayList<>();
    }

    public static List<Territory> getTerritories() {
        return territories;
    }

    public static void setTerritories(List<Territory> territories) {
        Cards.territories = territories;
    }

    public static List<Building> getBuildings() {
        return buildings;
    }

    public static void setBuildings(List<Building> buildings) {
        Cards.buildings = buildings;
    }

    public static List<Character> getCharacters() {
        return characters;
    }

    public static void setCharacters(List<Character> characters) {
        Cards.characters = characters;
    }

    public static List<Venture> getVentures() {
        return ventures;
    }

    public static void setVentures(List<Venture> ventures) {
        Cards.ventures = ventures;
    }

    public static List<ExcommunicationTile> getExcommunicationTiles() {
        return excommunicationTiles;
    }

    public static void setExcommunicationTiles(List<ExcommunicationTile> excommunicationTiles) {
        Cards.excommunicationTiles = excommunicationTiles;
    }

    public static List<LeaderCard> getLeaderCards() {
        return leaderCards;
    }

    public static void setLeaderCards(List<LeaderCard> leaderCards) {
        Cards.leaderCards = leaderCards;
    }
}
