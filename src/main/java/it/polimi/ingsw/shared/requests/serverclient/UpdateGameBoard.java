package it.polimi.ingsw.shared.requests.serverclient;

import it.polimi.ingsw.client.middleware.ClientReceiver;
import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.shared.model.BoardIdentifier;

import java.util.ArrayList;
import java.util.List;

public class UpdateGameBoard implements ServerClientRequest {
    private List<TowerSlotLight> newGreenTower;
    private List<TowerSlotLight> newYellowTower;
    private List<TowerSlotLight> newBlueTower;
    private List<TowerSlotLight> newPurpleTower;
    private List<SlotLight> newProduction;
    private List<SlotLight> newHarvest;
    private List<SlotLight> newMarket;
    private CouncilPalaceLight newCouncilPalaceLight;
    private List<PlayerLight> newPlayerLights;
    private List<DiceLight> diceLightList;
    private List<Card> newExcommunicationTiles;

    public UpdateGameBoard() {
        newGreenTower = new ArrayList<>();
        newYellowTower = new ArrayList<>();
        newBlueTower = new ArrayList<>();
        newPurpleTower = new ArrayList<>();
        newProduction = new ArrayList<>();
        newHarvest = new ArrayList<>();
        newMarket = new ArrayList<>();
        newCouncilPalaceLight = new CouncilPalaceLight(BoardIdentifier.COUNCIL_PALACE);
        newPlayerLights = new ArrayList<>();
        diceLightList = new ArrayList<>();
        newExcommunicationTiles = new ArrayList<>();
    }

    @Override
    public void acceptServerClientRequestVisitor(ClientReceiver clientReceiver) {
        clientReceiver.visitServerClientRequest(this);
    }

    public List<TowerSlotLight> getNewGreenTower() {
        return newGreenTower;
    }

    public void setNewGreenTower(List<TowerSlotLight> newGreenTower) {
        this.newGreenTower = newGreenTower;
    }

    public List<TowerSlotLight> getNewYellowTower() {
        return newYellowTower;
    }

    public void setNewYellowTower(List<TowerSlotLight> newYellowTower) {
        this.newYellowTower = newYellowTower;
    }

    public List<TowerSlotLight> getNewBlueTower() {
        return newBlueTower;
    }

    public void setNewBlueTower(List<TowerSlotLight> newBlueTower) {
        this.newBlueTower = newBlueTower;
    }

    public List<TowerSlotLight> getNewPurpleTower() {
        return newPurpleTower;
    }

    public void setNewPurpleTower(List<TowerSlotLight> newPurpleTower) {
        this.newPurpleTower = newPurpleTower;
    }

    public List<SlotLight> getNewProduction() {
        return newProduction;
    }

    public void setNewProduction(List<SlotLight> newProduction) {
        this.newProduction = newProduction;
    }

    public List<SlotLight> getNewHarvest() {
        return newHarvest;
    }

    public void setNewHarvest(List<SlotLight> newHarvest) {
        this.newHarvest = newHarvest;
    }

    public List<SlotLight> getNewMarket() {
        return newMarket;
    }

    public void setNewMarket(List<SlotLight> newMarket) {
        this.newMarket = newMarket;
    }

    public CouncilPalaceLight getNewCouncilPalaceLight() {
        return newCouncilPalaceLight;
    }

    public void setNewCouncilPalaceLight(CouncilPalaceLight newCouncilPalaceLight) {
        this.newCouncilPalaceLight = newCouncilPalaceLight;
    }

    public List<PlayerLight> getNewPlayerLights() {
        return newPlayerLights;
    }

    public void setNewPlayerLights(List<PlayerLight> newPlayerLights) {
        this.newPlayerLights = newPlayerLights;
    }

    public List<DiceLight> getDiceLightList() {
        return diceLightList;
    }

    public void setDiceLightList(List<DiceLight> diceLightList) {
        this.diceLightList = diceLightList;
    }

    public List<Card> getNewExcommunicationTiles() {
        return newExcommunicationTiles;
    }

    public void setNewExcommunicationTiles(List<Card> newExcommunicationTiles) {
        this.newExcommunicationTiles = newExcommunicationTiles;
    }
}
