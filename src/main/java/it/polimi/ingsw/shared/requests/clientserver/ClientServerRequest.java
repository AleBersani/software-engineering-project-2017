package it.polimi.ingsw.shared.requests.clientserver;

import it.polimi.ingsw.server.middleware.ServerReceiver;

import java.io.Serializable;

@FunctionalInterface
public interface ClientServerRequest extends Serializable {
    void acceptServerReceiver(ServerReceiver serverReceiver);
}
