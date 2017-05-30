package it.polimi.ingsw.gamelogic.board;

import it.polimi.ingsw.gamelogic.enums.GeneralColor;

import java.util.List;

/**
 * TODO: JavaDoc
 */
public class Tower {
    private GeneralColor color;
    private List<TowerSlot> tower;

    public Tower(GeneralColor color, List<TowerSlot> tower) {
        this.color = color;
        this.tower = tower;
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

    public List<TowerSlot> getTower() {
        return tower;
    }

    public void setTower(List<TowerSlot> tower) {
        this.tower = tower;
    }
}
