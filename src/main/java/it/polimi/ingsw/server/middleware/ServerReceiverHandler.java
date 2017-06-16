package it.polimi.ingsw.server.middleware;

import it.polimi.ingsw.server.GamesConnections;
import it.polimi.ingsw.server.database.QueryHandler;
import it.polimi.ingsw.server.socket.SocketOutputMemory;
import it.polimi.ingsw.shared.requests.clientserver.*;
import it.polimi.ingsw.shared.requests.serverclient.SimpleMessage;

import java.io.ObjectOutputStream;
import java.net.Socket;
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
            LOGGER.info("Socket login successful");
            ObjectOutputStream currentOutputStream = SocketOutputMemory.getOutputStream(Thread.currentThread().getId());
            GamesConnections.addClient(playerLogin.getPlayerName(), currentOutputStream);
            LOGGER.info("Socket client registered");
            sendSuccessfullyConnectedMessage(playerLogin.getPlayerName());
        } else {
            LOGGER.info("Login unsuccessful");
        }
    }

    @Override
    public void visitClientServerRequest(PlayerLoginRMI playerLoginRMI) {
        LOGGER.info("Player Login");
        if (QueryHandler.authenticate(playerLoginRMI.getPlayerName(), playerLoginRMI.getPassword())) {
            LOGGER.info("RMI login successful");
            GamesConnections.addClient(playerLoginRMI.getPlayerName(), playerLoginRMI.getRegistrable());
            LOGGER.info("RMI client registered");
            sendSuccessfullyConnectedMessage(playerLoginRMI.getPlayerName());
        } else {
            LOGGER.info("Login unsuccessful");
        }
    }

    private void sendSuccessfullyConnectedMessage(String playerName) {
        ServerSender serverSender = new ServerSenderHandler();
        serverSender.sendToClient(playerName, new SimpleMessage("Connected!"));
    }
}
