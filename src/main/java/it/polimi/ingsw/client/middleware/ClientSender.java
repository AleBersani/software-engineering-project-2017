package it.polimi.ingsw.client.middleware;

import it.polimi.ingsw.shared.requests.clientserver.ClientServerRequest;
import it.polimi.ingsw.shared.support.GameStartType;

public interface ClientSender {
    void register(String playerName, String psw);
    void login(String playerName, String psw);
    void choseGameType(GameStartType gameStartType);
    void sendToServer(ClientServerRequest clientServerRequest);
}
