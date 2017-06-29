package it.polimi.ingsw.shared.requests.serverclient;

import it.polimi.ingsw.client.middleware.ClientReceiver;

import java.io.Serializable;

public class SimpleMessage implements ServerClientRequest, Serializable {
    private String message;

    public SimpleMessage(String message) {
        this.message = message;
    }

    @Override
    public void acceptServerClientRequestVisitor(ClientReceiver clientReceiver) {
        clientReceiver.visitServerClientRequest(this);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
