package it.polimi.ingsw.shared.requests.clientserver;

import it.polimi.ingsw.server.middleware.ServerReceiver;

import java.io.Serializable;

public interface ClientServerRequest extends Serializable {
    void acceptClientServerRequestVisitor(ServerReceiver serverReceiver);
}
