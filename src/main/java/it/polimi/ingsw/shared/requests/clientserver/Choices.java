package it.polimi.ingsw.shared.requests.clientserver;

import it.polimi.ingsw.server.exectutionmiddleware.ClientServerRequestVisitor;

import java.io.Serializable;

public class Choices implements Serializable, ClientServerRequest {
    private String choiceType; // TODO: enum
    private boolean choice;

    public Choices(String choiceType, boolean choice) {
        this.choiceType = choiceType;
        this.choice = choice;
    }

    @Override
    public String toString() {
        return "Choices{" +
                "choiceType='" + choiceType + '\'' +
                ", choice=" + choice +
                '}';
    }

    @Override
    public void acceptClientServerRequestVisitor(ClientServerRequestVisitor clientServerRequestVisitor) {
        clientServerRequestVisitor.visitClientServerRequest(this);
    }

    public String getChoiceType() {
        return choiceType;
    }

    public void setChoiceType(String choiceType) {
        this.choiceType = choiceType;
    }

    public boolean isChoice() {
        return choice;
    }

    public void setChoice(boolean choice) {
        this.choice = choice;
    }
}
