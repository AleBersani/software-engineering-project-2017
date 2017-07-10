package it.polimi.ingsw.shared.requests.serverclient;

import it.polimi.ingsw.client.middleware.ClientReceiver;
import it.polimi.ingsw.client.model.Card;
import it.polimi.ingsw.client.model.DeckLight;
import it.polimi.ingsw.client.model.enums.PointsLight;
import it.polimi.ingsw.client.model.enums.ResourcesLight;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class UpdatePlayerBoard implements ServerClientRequest, Serializable {
    private List<Card> newActivatedLeaders;
    private Map<PointsLight, Integer> newNumberOfPoints;
    private String newBonusTileIdentifier;
    private DeckLight newDeckLight;
    private Map<ResourcesLight, Integer> numberOfResources;

    public UpdatePlayerBoard(List<Card> newActivatedLeaders, Map<PointsLight, Integer> newNumberOfPoints,
                             String newBonusTileIdentifier, DeckLight newDeckLight,
                             Map<ResourcesLight, Integer> numberOfResources) {
        this.newActivatedLeaders = newActivatedLeaders;
        this.newNumberOfPoints = newNumberOfPoints;
        this.newBonusTileIdentifier = newBonusTileIdentifier;
        this.newDeckLight = newDeckLight;
        this.numberOfResources = numberOfResources;
    }

    @Override
    public void acceptServerClientRequestVisitor(ClientReceiver clientReceiver) {
        clientReceiver.visitServerClientRequest(this);
    }

    public List<Card> getNewActivatedLeaders() {
        return newActivatedLeaders;
    }

    public void setNewActivatedLeaders(List<Card> newActivatedLeaders) {
        this.newActivatedLeaders = newActivatedLeaders;
    }

    public Map<PointsLight, Integer> getNewNumberOfPoints() {
        return newNumberOfPoints;
    }

    public void setNewNumberOfPoints(Map<PointsLight, Integer> newNumberOfPoints) {
        this.newNumberOfPoints = newNumberOfPoints;
    }

    public String getNewBonusTileIdentifier() {
        return newBonusTileIdentifier;
    }

    public void setNewBonusTileIdentifier(String newBonusTileIdentifier) {
        this.newBonusTileIdentifier = newBonusTileIdentifier;
    }

    public DeckLight getNewDeckLight() {
        return newDeckLight;
    }

    public void setNewDeckLight(DeckLight newDeckLight) {
        this.newDeckLight = newDeckLight;
    }

    public Map<ResourcesLight, Integer> getNumberOfResources() {
        return numberOfResources;
    }

    public void setNumberOfResources(Map<ResourcesLight, Integer> numberOfResources) {
        this.numberOfResources = numberOfResources;
    }
}
