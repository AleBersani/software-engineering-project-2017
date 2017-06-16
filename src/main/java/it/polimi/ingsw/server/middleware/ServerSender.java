package it.polimi.ingsw.server.middleware;

import it.polimi.ingsw.shared.requests.serverclient.ServerClientRequest;

public interface ServerSender {
    void sendToClient(String playerName, ServerClientRequest serverClientRequest);
}
