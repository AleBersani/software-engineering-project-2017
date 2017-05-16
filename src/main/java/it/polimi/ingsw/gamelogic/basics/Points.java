package it.polimi.ingsw.gamelogic.basics;

import it.polimi.ingsw.gamelogic.Util;

import java.util.Objects;

public class Points {
    private int victory;
    private int military;
    private int faith;

    public Points() {
        victory = 0;
        military = 0;
        faith = 0;
    }

    public Points(int victory, int military, int faith) {
        this.victory = victory;
        this.military = military;
        this.faith = faith;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Points points = (Points) o;
        return getVictory() == points.getVictory() &&
                getMilitary() == points.getMilitary() &&
                getFaith() == points.getFaith();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVictory(), getMilitary(), getFaith());
    }

    public void add(Points pointsToAdd) {
        victory += pointsToAdd.getVictory();
        military += pointsToAdd.getMilitary();
        faith += pointsToAdd.getFaith();
    }

    /*
    TODO: delete subtractOrSetToZero
     */
    public void subtract(Points pointsToSubtract) {
        victory = Util.subtractOrSetToZero(victory, pointsToSubtract.getVictory());
        military = Util.subtractOrSetToZero(military, pointsToSubtract.getMilitary());
        faith = Util.subtractOrSetToZero(faith, pointsToSubtract.getFaith());
    }

    public boolean isMoreThan(Points pointsToConfront) {
        /*
        TODO: method
         */
        return false;
    }

    public boolean isEmpty(){
        return victory == 0 &&
                military == 0 &&
                faith == 0;
    }

    public int getVictory() {
        return victory;
    }

    public void setVictory(int victory) {
        this.victory = victory;
    }

    public int getMilitary() {
        return military;
    }

    public void setMilitary(int military) {
        this.military = military;
    }

    public int getFaith() {
        return faith;
    }

    public void setFaith(int faith) {
        this.faith = faith;
    }
}
