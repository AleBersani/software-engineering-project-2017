package it.polimi.ingsw.client.connection.middleware;

import it.polimi.ingsw.shared.requests.serverclient.SimpleMessage;
import it.polimi.ingsw.shared.requests.serverclient.UpdateActionSpaces;
import it.polimi.ingsw.shared.requests.serverclient.UpdateTowers;

public interface ClientReceiver {
    void visitServerClientRequest(SimpleMessage SimpleMessage);
    void visitServerClientRequest(UpdateActionSpaces updateActionSpaces);
    void visitServerClientRequest(UpdateTowers updateTowers);
}
