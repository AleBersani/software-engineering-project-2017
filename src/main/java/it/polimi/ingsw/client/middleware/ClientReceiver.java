package it.polimi.ingsw.client.middleware;

import it.polimi.ingsw.shared.requests.serverclient.LoginResponse;
import it.polimi.ingsw.shared.requests.serverclient.SimpleMessage;
import it.polimi.ingsw.shared.requests.serverclient.UpdateActionSpaces;
import it.polimi.ingsw.shared.requests.serverclient.UpdateTowers;

public interface ClientReceiver {
    void visitServerClientRequest(SimpleMessage simpleMessage);
    void visitServerClientRequest(LoginResponse loginResponse);
    void visitServerClientRequest(UpdateActionSpaces updateActionSpaces);
    void visitServerClientRequest(UpdateTowers updateTowers);
}
