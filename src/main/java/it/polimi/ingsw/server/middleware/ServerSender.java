package it.polimi.ingsw.server.middleware;

import it.polimi.ingsw.server.connection.ConnectionStream;
import it.polimi.ingsw.shared.requests.serverclient.ServerClientRequest;

public interface ServerSender {
    void sendToClient(ConnectionStream connectionStream, ServerClientRequest serverClientRequest);
}
