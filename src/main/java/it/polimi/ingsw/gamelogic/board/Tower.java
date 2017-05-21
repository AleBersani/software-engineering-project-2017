package it.polimi.ingsw.gamelogic.board;

import it.polimi.ingsw.gamelogic.enums.GeneralColor;
import it.polimi.ingsw.gamelogic.enums.PawnColor;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private GeneralColor color;
    private List<TowerSlot> tower;

    private List<PawnColor> positionedPawnsColors;

    public Tower(GeneralColor color, List<TowerSlot> tower) {
        this.color = color;
        this.tower = tower;
        positionedPawnsColors = new ArrayList<>();
    }

    /**
     *  It adds a specific coloured pawn if that colour is not present on the tower yet.
     *  Control delegated to the implementing class.
     **/
    public void addPositionedPawn(PawnColor color) {
        positionedPawnsColors.add(color);
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

    public List<PawnColor> getPositionedPawnsColors() {
        return positionedPawnsColors;
    }

    public void setPositionedPawnsColors(List<PawnColor> positionedPawnsColors) {
        this.positionedPawnsColors = positionedPawnsColors;
    }
}
