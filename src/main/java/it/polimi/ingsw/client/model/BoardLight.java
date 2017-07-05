package it.polimi.ingsw.client.model;

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

    public BoardLight(List<TowerSlotLight> greenTower, List<TowerSlotLight> yellowTower,
                      List<TowerSlotLight> blueTower, List<TowerSlotLight> purpleTower,
                      List<SlotLight> production, List<SlotLight> harvest,
                      List<SlotLight> market, CouncilPalaceLight councilPalaceLight) {
        this.greenTower = greenTower;
        this.yellowTower = yellowTower;
        this.blueTower = blueTower;
        this.purpleTower = purpleTower;
        this.production = production;
        this.harvest = harvest;
        this.market = market;
        this.councilPalaceLight = councilPalaceLight;
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
}
