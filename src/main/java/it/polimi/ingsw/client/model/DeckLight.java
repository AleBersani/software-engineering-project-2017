package it.polimi.ingsw.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeckLight implements Serializable {
    private List<Card> territories;
    private List<Card> buildings;
    private List<Card> characters;
    private List<Card> ventures;
    private List<LeaderLight> leaders;

    public DeckLight() {
        territories = new ArrayList<>();
        buildings = new ArrayList<>();
        characters = new ArrayList<>();
        ventures = new ArrayList<>();
        leaders = new ArrayList<>();
    }

    public DeckLight(List<Card> territories, List<Card> buildings, List<Card> characters,
                     List<Card> ventures, List<LeaderLight> leaders) {
        this.territories = territories;
        this.buildings = buildings;
        this.characters = characters;
        this.ventures = ventures;
        this.leaders = leaders;
    }

    @Override
    public String toString() {
        return "DeckLight{" +
                "territories=" + territories.toString() +
                ", buildings=" + buildings.toString() +
                ", characters=" + characters.toString() +
                ", ventures=" + ventures.toString() +
                ", leaders=" + leaders.toString() +
                '}';
    }

    public List<Card> getTerritories() {
        return territories;
    }

    public void setTerritories(List<Card> territories) {
        this.territories = territories;
    }

    public List<Card> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Card> buildings) {
        this.buildings = buildings;
    }

    public List<Card> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Card> characters) {
        this.characters = characters;
    }

    public List<Card> getVentures() {
        return ventures;
    }

    public void setVentures(List<Card> ventures) {
        this.ventures = ventures;
    }

    public List<LeaderLight> getLeaders() {
        return leaders;
    }

    public void setLeaders(List<LeaderLight> leaders) {
        this.leaders = leaders;
    }
}
