package it.polimi.ingsw.client.middleware;

import it.polimi.ingsw.shared.requests.serverclient.*;

public interface ClientReceiver {
    void visitServerClientRequest(LeadersChoice leadersChoice);
    void visitServerClientRequest(LoginResponse loginResponse);
    void visitServerClientRequest(SimpleMessage simpleMessage);
    void visitServerClientRequest(UpdateActionSpaces updateActionSpaces);
    void visitServerClientRequest(UpdateTowers updateTowers);
}
