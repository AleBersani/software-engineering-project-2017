package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.shared.model.GeneralColor;

import java.util.List;

/**
 * Class that describes the Tower element of the board. Each Tower contains 4 TowerSlots and has a color defined
 * by the enum GeneralColor
 */
public class Tower {
    private GeneralColor color;
    private List<TowerSlot> towerSlots;

    public Tower(GeneralColor color, List<TowerSlot> towerSlots) {
        this.color = color;
        this.towerSlots = towerSlots;
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
