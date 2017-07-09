package it.polimi.ingsw.client.model;

import it.polimi.ingsw.client.ClientInformation;
import it.polimi.ingsw.client.cli.gameinformation.BoardOwnerInformation;
import it.polimi.ingsw.client.cli.model.BonusTileDescriptionLight;
import it.polimi.ingsw.client.model.enums.ResourcesLight;

import java.util.*;

public class Owner {
    private PlayerLight playerLight;
    private String bonusTileIdentifier;
    private DeckLight deckLight;
    private Map<ResourcesLight, Integer> numberOfResources;
    private List<PawnLight> pawnLights;

    private Owner() {
        playerLight = new PlayerLight(ClientInformation.getPlayerName(), ClientInformation.getPlayerColor());
        bonusTileIdentifier = "";
        deckLight = new DeckLight();
        numberOfResources = new EnumMap<>(ResourcesLight.class);
        pawnLights = new ArrayList<>();
    }

    private static class OwnerHolder {
        private static final Owner INSTANCE = new Owner();
    }

    public String printPlayerLight() {
        return playerLight.printPlayerLightInfo();
    }

    public String printOwnResourcesPoints() {
        String toString =
                "Resources: {" +
                        numberOfResources.get(ResourcesLight.WOODS) + "W, " +
                        numberOfResources.get(ResourcesLight.STONES) + "S, " +
                        numberOfResources.get(ResourcesLight.SERVANTS) + "Se, " +
                        numberOfResources.get(ResourcesLight.COINS) + "C} " +
                        playerLight.printPlayerPoints();
        return toString;
    }

    public String printBonusTiles() {
        BonusTileDescriptionLight bonusTileDescriptionLight;
        Optional<BonusTileDescriptionLight> bonusTile = BoardOwnerInformation
                .searchForBonusTileLight(bonusTileIdentifier);
        if (bonusTile.isPresent()){
            bonusTileDescriptionLight = bonusTile.get();
            return bonusTileDescriptionLight.getDescription();
        }
        return "";
    }

    public String printLeaders() {
        StringBuilder string = new StringBuilder().append("Leaders: ");
        for (LeaderLight card : deckLight.getLeaders()) {
            string.append(card.getCard().getName());
            string.append(", ");
        }
        string.delete(string.length()-2, string.length());
        return string.toString();
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

    public List<LeaderLight> getLeaders() {
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

    public List<PawnLight> getPawnLights() {
        return pawnLights;
    }

    public void setPawnLights(List<PawnLight> pawnLights) {
        this.pawnLights = pawnLights;
    }
}
