package it.polimi.ingsw.shared.requests.clientserver;

import it.polimi.ingsw.server.middleware.ServerReceiver;

import java.io.Serializable;
import java.net.Socket;

public class PlayerLogin implements Serializable, ClientServerRequest {
    private String playerName;
    private String password;

    public PlayerLogin(String playerName, String password) {
        this.playerName = playerName;
        this.password = password;
    }

    @Override
    public void acceptClientServerRequestVisitor(ServerReceiver serverReceiver) {
        serverReceiver.visitClientServerRequest(this);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
