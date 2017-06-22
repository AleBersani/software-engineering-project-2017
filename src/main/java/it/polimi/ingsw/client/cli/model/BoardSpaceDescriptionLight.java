package it.polimi.ingsw.client.cli.model;

import it.polimi.ingsw.shared.model.BoardIdentifier;

public class BoardSpaceDescriptionLight {
    private BoardIdentifier boardIdentifier;
    private String bonus;
    private int actionValue;
    private int malus;

    public BoardSpaceDescriptionLight(BoardIdentifier boardIdentifier, String bonus, int actionValue, int malus) {
        this.boardIdentifier = boardIdentifier;
        this.bonus = bonus;
        this.actionValue = actionValue;
        this.malus = malus;
    }

    public BoardIdentifier getBoardIdentifier() {
        return boardIdentifier;
    }

    public void setBoardIdentifier(BoardIdentifier boardIdentifier) {
        this.boardIdentifier = boardIdentifier;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public int getActionValue() {
        return actionValue;
    }

    public void setActionValue(int actionValue) {
        this.actionValue = actionValue;
    }

    public int getMalus() {
        return malus;
    }

    public void setMalus(int malus) {
        this.malus = malus;
    }
}
