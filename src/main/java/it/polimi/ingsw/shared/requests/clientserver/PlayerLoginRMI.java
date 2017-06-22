package it.polimi.ingsw.shared.requests.clientserver;

import it.polimi.ingsw.server.middleware.ServerReceiver;
import it.polimi.ingsw.shared.Registrable;

import java.io.Serializable;

public class PlayerLoginRMI implements ClientServerRequest, Serializable {
    private PlayerLogin playerLogin;
    private Registrable registrable;

    public PlayerLoginRMI(PlayerLogin playerLogin, Registrable registrable) {
        this.playerLogin = playerLogin;
        this.registrable = registrable;
    }

    @Override
    public void acceptServerReceiver(ServerReceiver serverReceiver) {
        serverReceiver.visitClientServerRequest(this);
    }

    public String getPlayerName() {
        return playerLogin.getPlayerName();
    }

    public String getPassword() {
        return playerLogin.getPassword();
    }

    public PlayerLogin getPlayerLogin() {
        return playerLogin;
    }

    public void setPlayerLogin(PlayerLogin playerLogin) {
        this.playerLogin = playerLogin;
    }

    public Registrable getRegistrable() {
        return registrable;
    }

    public void setRegistrable(Registrable registrable) {
        this.registrable = registrable;
    }
}
