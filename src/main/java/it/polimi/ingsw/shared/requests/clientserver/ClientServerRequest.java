package it.polimi.ingsw.shared.requests.clientserver;

import it.polimi.ingsw.server.exectutionmiddleware.ClientServerRequestVisitor;

import java.io.Serializable;

public interface ClientServerRequest extends Serializable {
    void acceptClientServerRequestVisitor(ClientServerRequestVisitor clientServerRequestVisitor);
}
