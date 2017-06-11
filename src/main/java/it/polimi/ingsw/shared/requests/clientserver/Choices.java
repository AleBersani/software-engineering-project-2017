package it.polimi.ingsw.shared.requests.clientserver;

import java.io.Serializable;

public class Choices implements Serializable {
    private String choiseType; // TODO: enum
    private boolean choice;

    public Choices(String choiseType, boolean choice) {
        this.choiseType = choiseType;
        this.choice = choice;
    }

    @Override
    public String toString() {
        return "Choices{" +
                "choiseType='" + choiseType + '\'' +
                ", choice=" + choice +
                '}';
    }

    public String getChoiseType() {
        return choiseType;
    }

    public void setChoiseType(String choiseType) {
        this.choiseType = choiseType;
    }

    public boolean isChoice() {
        return choice;
    }

    public void setChoice(boolean choice) {
        this.choice = choice;
    }
}
