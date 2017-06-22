package it.polimi.ingsw.server.gamecontroller.gameelements;

import it.polimi.ingsw.server.gamelogic.cards.development.*;
import it.polimi.ingsw.server.gamelogic.cards.development.Character;
import it.polimi.ingsw.server.gamelogic.cards.excommunicationtiles.ExcommunicationTile;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderCard;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    public static List<Territory> territories;
    public static List<Building> buildings;
    public static List<Character> characters;
    public static List<Venture> ventures;
    public static List<ExcommunicationTile> excommunicationTiles;
    public static List<LeaderCard> leaderCards;

    public static void initializeCards() {
        territories = new ArrayList<>();
        buildings = new ArrayList<>();
        characters = new ArrayList<>();
        ventures = new ArrayList<>();
        excommunicationTiles = new ArrayList<>();
        leaderCards = new ArrayList<>();
    }
}
