package it.polimi.ingsw.client.cli.model;

import it.polimi.ingsw.shared.model.BoardIdentifier;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BoardSpaceDescriptionLight that = (BoardSpaceDescriptionLight) o;
        return Objects.equals(getBoardIdentifier(), that.getBoardIdentifier()) &&
               Objects.equals(getBonus(), that.getBonus()) &&
               getActionValue() == that.getActionValue() &&
               getMalus() == that.getMalus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBoardIdentifier(), getBonus(), getActionValue(), getMalus());
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
