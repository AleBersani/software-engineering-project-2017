package it.polimi.ingsw.shared.requests.clientserver;

import it.polimi.ingsw.server.middleware.ServerReceiver;

import java.io.Serializable;

public class PlayerLogin implements Serializable, ClientServerRequest {
    private String playerName;
    private String password;

    private boolean newPlayer;

    public PlayerLogin(String playerName, String password) {
        this.playerName = playerName;
        this.password = password;
        newPlayer = false;
    }

    public PlayerLogin(String playerName, String password, boolean newPlayer) {
        this.playerName = playerName;
        this.password = password;
        this.newPlayer = newPlayer;
    }

    @Override
    public void acceptServerReceiver(ServerReceiver serverReceiver) {
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

    public boolean isNewPlayer() {
        return newPlayer;
    }

    public void setNewPlayer(boolean newPlayer) {
        this.newPlayer = newPlayer;
    }
}
