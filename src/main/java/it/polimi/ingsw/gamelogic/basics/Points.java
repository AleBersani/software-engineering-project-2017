package it.polimi.ingsw.gamelogic.basics;

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

    public void subtract(Points pointsToSubtract) {
        victory -= pointsToSubtract.getVictory();
        military -= pointsToSubtract.getMilitary();
        faith -= pointsToSubtract.getFaith();
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
