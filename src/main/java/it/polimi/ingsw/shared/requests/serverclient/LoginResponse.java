package it.polimi.ingsw.shared.requests.serverclient;

import it.polimi.ingsw.client.middleware.ClientReceiver;
import it.polimi.ingsw.shared.model.GeneralColor;

import java.io.Serializable;

public class LoginResponse implements ServerClientRequest, Serializable {
    private boolean successful;
    private String playerName;

    public LoginResponse(boolean successful) {
        this.successful = successful;
    }

    public LoginResponse(boolean successful, String playerName) {
        this.successful = successful;
        this.playerName = playerName;
    }

    @Override
    public void acceptServerClientRequestVisitor(ClientReceiver clientReceiver) {
        clientReceiver.visitServerClientRequest(this);
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
