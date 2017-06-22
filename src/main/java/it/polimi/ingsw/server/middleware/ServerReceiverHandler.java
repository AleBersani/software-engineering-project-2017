package it.polimi.ingsw.server.middleware;

import it.polimi.ingsw.server.connection.ConnectedClient;
import it.polimi.ingsw.server.connection.ConnectionStream;
import it.polimi.ingsw.server.database.QueryHandler;
import it.polimi.ingsw.shared.requests.clientserver.*;
import it.polimi.ingsw.shared.requests.serverclient.SimpleMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerReceiverHandler extends Observable implements ServerReceiver {
    private final static Logger LOGGER = Logger.getLogger(ServerReceiverHandler.class.getName());

    private ObjectOutputStream objectOutputStream;

    public ServerReceiverHandler() {
        objectOutputStream = null;
    }

    public ServerReceiverHandler(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }

    @Override
    public void visitClientServerRequest(Choices choices) {
        System.out.println("Choice");
    }

    @Override
    public void visitClientServerRequest(Connection connection) {
        System.out.println("Connection");
    }

    @Override
    public void visitClientServerRequest(LeaderAction leaderAction) {
        System.out.println("LeaderAction");
    }

    @Override
    public void visitClientServerRequest(PawnPlacement pawnPlacement) {
        System.out.println("PawnPlacement");
    }

    @Override
    public void visitClientServerRequest(PlayerLogin playerLogin) {
        LOGGER.info("Player login Socket");
        String playerName = playerLogin.getPlayerName();
        if (authenticate(playerName, playerLogin.getPassword())) {
            LOGGER.info("Socket login successful");
            setChanged();
            notifyObservers(new ConnectedClient(playerName, new ConnectionStream(objectOutputStream)));
        } else {
            LOGGER.info("Login unsuccessful");
            try {
                objectOutputStream.writeObject(new SimpleMessage("Login unsuccessful"));
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "An exception was thrown: cannot write on socket", e);
            }

        }
    }

    @Override
    public void visitClientServerRequest(PlayerLoginRMI playerLoginRMI) {
        LOGGER.info("Player login RMI");
        String playerName = playerLoginRMI.getPlayerName();
        if (authenticate(playerName, playerLoginRMI.getPassword())) {
            LOGGER.info("RMI login successful");
            setChanged();
            notifyObservers(new ConnectedClient(playerName, new ConnectionStream(playerLoginRMI.getRegistrable())));
        } else {
            LOGGER.info("Login unsuccessful");
            try {
                playerLoginRMI.getRegistrable().update(new SimpleMessage("Login unsuccessful"));
            } catch (RemoteException e) {
                LOGGER.log(Level.SEVERE, "An exception was thrown: cannot send RMI callback", e);
            }
        }
    }

    private boolean authenticate(String playerName, String psw) {
        QueryHandler queryHandler = new QueryHandler();
        return queryHandler.authenticate(playerName, psw);
    }
}
