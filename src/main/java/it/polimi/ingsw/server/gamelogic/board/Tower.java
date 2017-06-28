package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.shared.model.GeneralColor;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Tower tower = (Tower) o;
        return getColor() == tower.getColor() &&
                Objects.equals(getTowerSlots(), tower.getTowerSlots());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getTowerSlots());
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
