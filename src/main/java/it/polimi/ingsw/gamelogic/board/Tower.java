package it.polimi.ingsw.gamelogic.board;

import it.polimi.ingsw.gamelogic.enums.GeneralColor;

import java.util.List;

/**
 * TODO: JavaDoc
 */
public class Tower {
    private GeneralColor color;
    private List<TowerSlot> towerSlots;

    public Tower(GeneralColor color, List<TowerSlot> towerSlots) {
        this.color = color;
        this.towerSlots = towerSlots;
    }

    /*
    TODO: Aux methods
     */

    /**
     * Check if the towers is occupied
     * @return true if the tower is occupied
     */
    public boolean isTowerOccupied() {
        /*
        TODO
         */
        return true;
    }

    public GeneralColor getColor() {
        return color;
    }

    public void setColor(GeneralColor color) {
        this.color = color;
    }

    public List<TowerSlot> getTowerSlots() {
        return towerSlots;
    }

    public void setTowerSlots(List<TowerSlot> towerSlots) {
        this.towerSlots = towerSlots;
    }
}
