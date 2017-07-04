package it.polimi.ingsw.client.middleware;

import it.polimi.ingsw.client.ClientInformation;
import it.polimi.ingsw.client.connection.rmi.RMIClient;
import it.polimi.ingsw.client.connection.socket.SocketClient;
import it.polimi.ingsw.shared.requests.clientserver.*;
import it.polimi.ingsw.shared.support.GameStartType;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientSenderHandler implements ClientSender {
    private final static Logger LOGGER = Logger.getLogger(ClientSenderHandler.class.getName());

    private static String playerName;
    private static boolean socket = true;

    public ClientSenderHandler() {}

    public ClientSenderHandler(boolean socket) {
        this.socket = socket;
    }

    @Override
    public void register(String playerName, String psw) {
        PlayerLogin playerLogin = new PlayerLogin(playerName, psw, true);
        if (socket) {
            socketLogin(playerLogin);
        } else {
            RMILogin(playerLogin);
        }
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
            LOGGER.log(Level.SEVERE, "An exception was thrown: login socket", e);
        }
    }

    private void RMILogin(PlayerLogin playerLogin) {
        try {
            RMIClient.remoteLogin(new PlayerLoginRMI(playerLogin, RMIClient.getClient()));
        } catch (RemoteException | NotBoundException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: login RMI", e);
        }
    }

    @Override
    public void choseGameType(GameStartType gameStartType) {
        GameStartChoice gameStartChoice = new GameStartChoice(ClientInformation.getPlayerName(), gameStartType);
        if (socket) {
            choseGameTypeSocket(gameStartChoice);
        } else {
            choseGameTypeRMI(gameStartChoice);
        }
    }

    private void choseGameTypeSocket(GameStartChoice gameStartChoice) {
        try {
            SocketClient.writeSocket(gameStartChoice);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: chose game type socket", e);
        }
    }

    private void choseGameTypeRMI(GameStartChoice gameStartChoice) {
        try {
            GameStartChoiceRMI gameStartChoiceRMI = new GameStartChoiceRMI(gameStartChoice, RMIClient.getClient());
            RMIClient.choseGameType(gameStartChoiceRMI);
        } catch (RemoteException | NotBoundException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: chose game type RMI", e);
        }
    }

    @Override
    public void sendToServer(ClientServerRequest clientServerRequest) {
        if (socket) {
            try {
                SocketClient.writeSocket(clientServerRequest);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "An exception was thrown: cannot send client server request on socket", e);
            }
        } else {
            try {
                RMIClient.callMethod(clientServerRequest);
            } catch (RemoteException | NotBoundException e) {
                LOGGER.log(Level.SEVERE, "An exception was thrown: cannot send client server request on RMI", e);
            }
        }
    }

    public static String getPlayerName() {
        return playerName;
    }

    public static void setPlayerName(String playerName) {
        ClientSenderHandler.playerName = playerName;
    }

    public static boolean isSocket() {
        return socket;
    }

    public static void setSocket(boolean socket) {
        ClientSenderHandler.socket = socket;
    }
}
