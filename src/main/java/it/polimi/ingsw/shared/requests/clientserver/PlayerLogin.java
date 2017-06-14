package it.polimi.ingsw.shared.requests.clientserver;

import it.polimi.ingsw.shared.model.PlayerDetails;
import it.polimi.ingsw.server.exectutionmiddleware.ClientServerRequestVisitor;

import java.io.Serializable;

public class PlayerLogin implements Serializable, ClientServerRequest {
    private PlayerDetails playerDetails;
    private String password;

    public PlayerLogin(PlayerDetails playerDetails, String password) {
        this.playerDetails = playerDetails;
        this.password = password;
    }

    @Override
    public void acceptClientServerRequestVisitor(ClientServerRequestVisitor clientServerRequestVisitor) {
        clientServerRequestVisitor.visitClientServerRequest(this);
    }

    public PlayerDetails getPlayerDetails() {
        return playerDetails;
    }

    public void setPlayerDetails(PlayerDetails playerDetails) {
        this.playerDetails = playerDetails;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
