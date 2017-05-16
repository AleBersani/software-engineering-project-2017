package it.polimi.ingsw.gamelogic.board;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    /*
    TODO: color is an ENUM
     */
    private String color;
    /*
    TODO: streams
     */
    private List<TowerSlot> tower;
    private List<String> positionedPawnsColors;

    public Tower(String color, List<TowerSlot> tower) {
        this.color = color;
        this.tower = tower;
        positionedPawnsColors = new ArrayList<>();
    }

    /**
     * It controls if the tower is already occupied by a specific coloured pawn
     **/
    /*
    TODO: pawnColor is an ENUM
     */
    public boolean hasSameColor(String pawnColor) {
        for (String p: positionedPawnsColors)
            if (p.equals(pawnColor))
                return true;
        return false;
    }

    /**
     *  It adds a specific coloured pawn if that colour is not present on the tower yet
     **/
    /*
    TODO: pawnColor is an ENUM & return type boolean
     */
    public void addPositionedPawn(String color) {
        if(!hasSameColor(color))
            positionedPawnsColors.add(color);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<TowerSlot> getTower() {
        return tower;
    }

    public void setTower(List<TowerSlot> tower) {
        this.tower = tower;
    }

    public List<String> getPositionedPawnsColors() {
        return positionedPawnsColors;
    }

    public void setPositionedPawnsColors(List<String> positionedPawnsColors) {
        this.positionedPawnsColors = positionedPawnsColors;
    }
}
