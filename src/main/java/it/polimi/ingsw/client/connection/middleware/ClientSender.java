package it.polimi.ingsw.client.connection.middleware;

import it.polimi.ingsw.shared.requests.clientserver.ClientServerRequest;

public interface ClientSender {
    void login(String playerName, String psw);
    void sendToServer(ClientServerRequest clientServerRequest);
}
