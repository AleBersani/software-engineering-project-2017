package it.polimi.ingsw.shared.requests.serverclient;

import it.polimi.ingsw.client.connection.middleware.ClientReceiver;

import java.io.Serializable;

public interface ServerClientRequest extends Serializable {
    void acceptServerClientRequestVisitor(ClientReceiver clientReceiver);
}
