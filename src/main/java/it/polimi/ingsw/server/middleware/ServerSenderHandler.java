package it.polimi.ingsw.server.middleware;

import it.polimi.ingsw.server.ConnectionStream;
import it.polimi.ingsw.server.GamesConnections;
import it.polimi.ingsw.server.socket.Writer;
import it.polimi.ingsw.shared.Registrable;
import it.polimi.ingsw.shared.requests.serverclient.ServerClientRequest;
import it.polimi.ingsw.shared.requests.serverclient.SimpleMessage;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerSenderHandler implements ServerSender {
    private final static Logger LOGGER = Logger.getLogger(ServerSenderHandler.class.getName());

    @Override
    public void sendToClient(String playerName, ServerClientRequest serverClientRequest) {
        ConnectionStream connectionStream = GamesConnections.getPlayerConnectionStream(playerName);
        if (connectionStream.getObjectOutputStream().isPresent()) {
            System.out.println("Sto scrivendo su socket");
            Writer.write(connectionStream.getObjectOutputStream().get(), serverClientRequest);
        } else if (connectionStream.getRegistrable().isPresent()) {
            Registrable registrable = connectionStream.getRegistrable().get();
            try {
                registrable.update(serverClientRequest);
            } catch (RemoteException e) {
                LOGGER.log(Level.SEVERE,"An exception was thrown: RMI callback", e);
            }
        }
    }
}
