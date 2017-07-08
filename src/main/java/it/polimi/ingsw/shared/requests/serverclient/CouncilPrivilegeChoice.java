package it.polimi.ingsw.shared.requests.serverclient;

import it.polimi.ingsw.client.middleware.ClientReceiver;

import java.io.Serializable;

public class CouncilPrivilegeChoice implements ServerClientRequest, Serializable {
    private int numberOfChoices;

    public CouncilPrivilegeChoice(int numberOfChoices) {
        this.numberOfChoices = numberOfChoices;
    }

    @Override
    public void acceptServerClientRequestVisitor(ClientReceiver clientReceiver) {
        clientReceiver.visitServerClientRequest(this);
    }

    public int getNumberOfChoices() {
        return numberOfChoices;
    }

    public void setNumberOfChoices(int numberOfChoices) {
        this.numberOfChoices = numberOfChoices;
    }
}
