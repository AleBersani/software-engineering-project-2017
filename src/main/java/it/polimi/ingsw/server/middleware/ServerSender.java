package it.polimi.ingsw.server.middleware;

import it.polimi.ingsw.server.connection.ConnectionStream;
import it.polimi.ingsw.shared.Registrable;
import it.polimi.ingsw.shared.requests.serverclient.ServerClientRequest;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;

public interface ServerSender {
    void sendToClient(ConnectionStream connectionStream, ServerClientRequest serverClientRequest);
}
