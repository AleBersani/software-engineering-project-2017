package it.polimi.ingsw.client.model;

import it.polimi.ingsw.client.model.enums.ResourcesLight;

import java.util.List;
import java.util.Map;

public class Owner {
    private PlayerLight playerLight;
    private String bonusTileIdentifier;
    private List<Card> territories;
    private List<Card> buildings;
    private List<Card> characters;
    private List<Card> ventures;
    private List<Card> leader;
    private Map<ResourcesLight, Integer> numberOfResources;

    private Owner() {}

    private static class OwnerHolder {
        private static final Owner INSTANCE = new Owner();
    }

    public static Owner getInstance() {
        return OwnerHolder.INSTANCE;
    }

    public PlayerLight getPlayerLight() {
        return playerLight;
    }

    public void setPlayerLight(PlayerLight playerLight) {
        this.playerLight = playerLight;
    }

    public String getBonusTileIdentifier() {
        return bonusTileIdentifier;
    }

    public void setBonusTileIdentifier(String bonusTileIdentifier) {
        this.bonusTileIdentifier = bonusTileIdentifier;
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

    public List<Card> getLeader() {
        return leader;
    }

    public void setLeader(List<Card> leader) {
        this.leader = leader;
    }

    public Map<ResourcesLight, Integer> getNumberOfResources() {
        return numberOfResources;
    }

    public void setNumberOfResources(Map<ResourcesLight, Integer> numberOfResources) {
        this.numberOfResources = numberOfResources;
    }
}
