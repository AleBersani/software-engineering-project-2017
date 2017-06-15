package it.polimi.ingsw.shared.requests.serverclient;

import it.polimi.ingsw.client.connection.middleware.ClientReceiver;

public class SimpleMessage implements ServerClientRequest {
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
