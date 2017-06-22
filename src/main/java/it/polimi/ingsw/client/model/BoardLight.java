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

    public BoardLight() {
        // TODO
    }
}
