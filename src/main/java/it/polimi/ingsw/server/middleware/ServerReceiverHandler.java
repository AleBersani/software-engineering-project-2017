package it.polimi.ingsw.server.middleware;

import it.polimi.ingsw.server.database.QueryHandler;
import it.polimi.ingsw.shared.requests.clientserver.*;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerReceiverHandler implements ServerReceiver {
    private final static Logger LOGGER = Logger.getLogger(ServerReceiverHandler.class.getName());

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
        LOGGER.info("Player Login");
        if (QueryHandler.authenticate(playerLogin.getPlayerName(), playerLogin.getPassword())) {
            LOGGER.info("Login successful");
        } else {
            LOGGER.info("Login unsuccessful");
        }
    }

    @Override
    public void visitClientServerRequest(PlayerLoginRMI playerLoginRMI) {
        LOGGER.info("Player Login");
        if (QueryHandler.authenticate(playerLoginRMI.getPlayerName(), playerLoginRMI.getPassword())) {
            LOGGER.info("Login successful");
            try {
                playerLoginRMI.getRegistrable().update("Connected!");
            } catch (RemoteException e) {
                LOGGER.log(Level.SEVERE, "An exception was thrown: RMI callback error", e);
            }
        } else {
            LOGGER.info("Login unsuccessful");
        }
    }
}
