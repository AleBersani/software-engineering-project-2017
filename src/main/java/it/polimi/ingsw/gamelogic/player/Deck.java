package it.polimi.ingsw.gamelogic.player;

import it.polimi.ingsw.gamelogic.cards.development.Building;
import it.polimi.ingsw.gamelogic.cards.development.Territory;
import it.polimi.ingsw.gamelogic.cards.development.Venture;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: JavaDoc
 */
public class Deck {
    private List<Territory> territories;
    private List<Building> buildings;
    private List<Character> characters;
    private List<Venture> ventures;

    public Deck() {
        territories = new ArrayList<>();
        buildings = new ArrayList<>();
        characters = new ArrayList<>();
        ventures = new ArrayList<>();
    }

    /*
    TODO: Aux methods
     */

    public List<Territory> getTerritories() {
        return territories;
    }

    public void setTerritories(List<Territory> territories) {
        this.territories = territories;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public List<Venture> getVentures() {
        return ventures;
    }

    public void setVentures(List<Venture> ventures) {
        this.ventures = ventures;
    }
}
