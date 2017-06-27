package it.polimi.ingsw.shared.requests.serverclient;

import it.polimi.ingsw.client.connection.middleware.ClientReceiver;

import java.io.Serializable;

public class SuccessfulLogin implements ServerClientRequest, Serializable {
    @Override
    public void acceptServerClientRequestVisitor(ClientReceiver clientReceiver) {
        clientReceiver.visitServerClientRequest(this);
    }
}
