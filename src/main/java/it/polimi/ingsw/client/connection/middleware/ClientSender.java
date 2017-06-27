package it.polimi.ingsw.client.connection.middleware;

import it.polimi.ingsw.shared.requests.clientserver.ClientServerRequest;
import it.polimi.ingsw.shared.support.GameStartType;

public interface ClientSender {
    void login(String playerName, String psw);
    void choseGameType(GameStartType gameStartType);
    void sendToServer(ClientServerRequest clientServerRequest);
}
