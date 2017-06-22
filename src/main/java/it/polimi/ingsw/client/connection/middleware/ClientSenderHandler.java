package it.polimi.ingsw.client.connection.middleware;

import it.polimi.ingsw.client.connection.rmi.RMIClient;
import it.polimi.ingsw.client.connection.socket.SocketClient;
import it.polimi.ingsw.shared.Client;
import it.polimi.ingsw.shared.Registrable;
import it.polimi.ingsw.shared.requests.clientserver.ClientServerRequest;
import it.polimi.ingsw.shared.requests.clientserver.PlayerLogin;
import it.polimi.ingsw.shared.requests.clientserver.PlayerLoginRMI;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientSenderHandler implements ClientSender {
    private final static Logger LOGGER = Logger.getLogger(ClientSenderHandler.class.getName());

    private static boolean socket = true;

    public ClientSenderHandler(boolean socket) {
        this.socket = socket;
    }

    @Override
    public void login(String playerName, String psw) {
        PlayerLogin playerLogin = new PlayerLogin(playerName, psw);
        if (socket) {
            socketLogin(playerLogin);
        } else {
            RMILogin(playerLogin);
        }
    }

    private void socketLogin(PlayerLogin playerLogin) {
        try {
            SocketClient.writeSocket(playerLogin);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: Login socket", e);
        }
    }

    private void RMILogin(PlayerLogin playerLogin) {
        Registrable client;
        try {
            client = new Client();
            RMIClient.remoteLogin(new PlayerLoginRMI(playerLogin, client));
        } catch (RemoteException | NotBoundException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: Login RMI", e);
        }
    }

    @Override
    public void sendToServer(ClientServerRequest clientServerRequest) {

    }
}
