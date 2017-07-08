package it.polimi.ingsw.client.model;

import it.polimi.ingsw.shared.model.BoardIdentifier;

import java.util.ArrayList;
import java.util.List;

public class BoardLight {
    private List<TowerSlotLight> greenTower;
    private List<TowerSlotLight> yellowTower;
    private List<TowerSlotLight> blueTower;
    private List<TowerSlotLight> purpleTower;
    private List<SlotLight> production;
    private List<SlotLight> harvest;
    private List<SlotLight> market;
    private CouncilPalaceLight councilPalaceLight;
    private List<PlayerLight> playerLights;
    private List<DiceLight> diceLightList;
    private List<Card> excommunicationTiles;

    private BoardLight() {
        greenTower = new ArrayList<>();
        yellowTower = new ArrayList<>();
        blueTower = new ArrayList<>();
        purpleTower = new ArrayList<>();
        production = new ArrayList<>();
        harvest = new ArrayList<>();
        market = new ArrayList<>();
        councilPalaceLight = new CouncilPalaceLight(BoardIdentifier.COUNCIL_PALACE);
        playerLights = new ArrayList<>();
        diceLightList = new ArrayList<>();
        excommunicationTiles = new ArrayList<>();
    }

    private static class BoardLightHolder {
        private static final BoardLight INSTANCE = new BoardLight();
    }

    public static BoardLight getInstance() {
        return BoardLightHolder.INSTANCE;
    }

    public List<TowerSlotLight> getGreenTower() {
        return greenTower;
    }

    public void setGreenTower(List<TowerSlotLight> greenTower) {
        this.greenTower = greenTower;
    }

    public List<TowerSlotLight> getYellowTower() {
        return yellowTower;
    }

    public void setYellowTower(List<TowerSlotLight> yellowTower) {
        this.yellowTower = yellowTower;
    }

    public List<TowerSlotLight> getBlueTower() {
        return blueTower;
    }

    public void setBlueTower(List<TowerSlotLight> blueTower) {
        this.blueTower = blueTower;
    }

    public List<TowerSlotLight> getPurpleTower() {
        return purpleTower;
    }

    public void setPurpleTower(List<TowerSlotLight> purpleTower) {
        this.purpleTower = purpleTower;
    }

    public List<SlotLight> getProduction() {
        return production;
    }

    public void setProduction(List<SlotLight> production) {
        this.production = production;
    }

    public List<SlotLight> getHarvest() {
        return harvest;
    }

    public void setHarvest(List<SlotLight> harvest) {
        this.harvest = harvest;
    }

    public List<SlotLight> getMarket() {
        return market;
    }

    public void setMarket(List<SlotLight> market) {
        this.market = market;
    }

    public CouncilPalaceLight getCouncilPalaceLight() {
        return councilPalaceLight;
    }

    public void setCouncilPalaceLight(CouncilPalaceLight councilPalaceLight) {
        this.councilPalaceLight = councilPalaceLight;
    }

    public List<PlayerLight> getPlayerLights() {
        return playerLights;
    }

    public void setPlayerLights(List<PlayerLight> playerLights) {
        this.playerLights = playerLights;
    }

    public List<DiceLight> getDiceLightList() {
        return diceLightList;
    }

    public void setDiceLightList(List<DiceLight> diceLightList) {
        this.diceLightList = diceLightList;
    }

    public List<Card> getExcommunicationTiles() {
        return excommunicationTiles;
    }

    public void setExcommunicationTiles(List<Card> excommunicationTiles) {
        this.excommunicationTiles = excommunicationTiles;
    }
}
