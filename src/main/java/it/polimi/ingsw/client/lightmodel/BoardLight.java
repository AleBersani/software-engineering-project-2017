package it.polimi.ingsw.client.lightmodel;

import java.util.List;
import java.util.Objects;

public class BoardLight {
    private List<SlotLight> greenTower;
    private List<SlotLight> yellowTower;
    private List<SlotLight> blueTower;
    private List<SlotLight> purpleTower;
    private List<SlotLight> market;
    private List<SlotLight> harvest;
    private List<SlotLight> production;
    private List<SlotLight> councilPalace;

    public BoardLight(List<SlotLight> greenTower, List<SlotLight> yellowTower, List<SlotLight> blueTower,
                      List<SlotLight> purpleTower, List<SlotLight> market, List<SlotLight> harvest,
                      List<SlotLight> production, List<SlotLight> councilPalace) {
        this.greenTower = greenTower;
        this.yellowTower = yellowTower;
        this.blueTower = blueTower;
        this.purpleTower = purpleTower;
        this.market = market;
        this.harvest = harvest;
        this.production = production;
        this.councilPalace = councilPalace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BoardLight that = (BoardLight) o;
        return  Objects.equals(getGreenTower(), that.getGreenTower()) &&
                Objects.equals(getYellowTower(), that.getYellowTower()) &&
                Objects.equals(getBlueTower(), that.getBlueTower()) &&
                Objects.equals(getPurpleTower(), that.getPurpleTower()) &&
                Objects.equals(getMarket(), that.getMarket()) &&
                Objects.equals(getHarvest(), that.getHarvest()) &&
                Objects.equals(getProduction(), that.getProduction()) &&
                Objects.equals(getCouncilPalace(), that.getCouncilPalace());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGreenTower(), getYellowTower(), getBlueTower(), getPurpleTower(),
                                getMarket(), getHarvest(), getProduction(), getCouncilPalace());
    }

    public List<SlotLight> getGreenTower() {
        return greenTower;
    }

    public void setGreenTower(List<SlotLight> greenTower) {
        this.greenTower = greenTower;
    }

    public List<SlotLight> getYellowTower() {
        return yellowTower;
    }

    public void setYellowTower(List<SlotLight> yellowTower) {
        this.yellowTower = yellowTower;
    }

    public List<SlotLight> getBlueTower() {
        return blueTower;
    }

    public void setBlueTower(List<SlotLight> blueTower) {
        this.blueTower = blueTower;
    }

    public List<SlotLight> getPurpleTower() {
        return purpleTower;
    }

    public void setPurpleTower(List<SlotLight> purpleTower) {
        this.purpleTower = purpleTower;
    }

    public List<SlotLight> getMarket() {
        return market;
    }

    public void setMarket(List<SlotLight> market) {
        this.market = market;
    }

    public List<SlotLight> getHarvest() {
        return harvest;
    }

    public void setHarvest(List<SlotLight> harvest) {
        this.harvest = harvest;
    }

    public List<SlotLight> getProduction() {
        return production;
    }

    public void setProduction(List<SlotLight> production) {
        this.production = production;
    }

    public List<SlotLight> getCouncilPalace() {
        return councilPalace;
    }

    public void setCouncilPalace(List<SlotLight> councilPalace) {
        this.councilPalace = councilPalace;
    }
}
