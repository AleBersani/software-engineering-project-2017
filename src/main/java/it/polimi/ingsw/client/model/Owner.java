package it.polimi.ingsw.client.model;

import it.polimi.ingsw.client.ClientInformation;
import it.polimi.ingsw.client.model.enums.ResourcesLight;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Owner {
    private PlayerLight playerLight;
    private String bonusTileIdentifier;
    private DeckLight deckLight;
    private Map<ResourcesLight, Integer> numberOfResources;

    private Owner() {
        playerLight = new PlayerLight(ClientInformation.getPlayerName(), ClientInformation.getPlayerColor());
        bonusTileIdentifier = "";
        deckLight = new DeckLight();
        numberOfResources = new EnumMap<>(ResourcesLight.class);
    }

    private static class OwnerHolder {
        private static final Owner INSTANCE = new Owner();
    }

    public static Owner getInstance() {
        return OwnerHolder.INSTANCE;
    }

    public List<Card> getTerritories() {
        return deckLight.getTerritories();
    }

    public List<Card> getBuildings() {
        return deckLight.getBuildings();
    }

    public List<Card> getCharacters() {
        return deckLight.getCharacters();
    }

    public List<Card> getVentures() {
        return deckLight.getVentures();
    }

    public List<Card> getLeaders() {
        return deckLight.getLeaders();
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

    public DeckLight getDeckLight() {
        return deckLight;
    }

    public void setDeckLight(DeckLight deckLight) {
        this.deckLight = deckLight;
    }

    public Map<ResourcesLight, Integer> getNumberOfResources() {
        return numberOfResources;
    }

    public void setNumberOfResources(Map<ResourcesLight, Integer> numberOfResources) {
        this.numberOfResources = numberOfResources;
    }
}
