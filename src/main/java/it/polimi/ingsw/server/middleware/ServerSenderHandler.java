package it.polimi.ingsw.server.middleware;

import it.polimi.ingsw.server.connection.ConnectionStream;
import it.polimi.ingsw.shared.Registrable;
import it.polimi.ingsw.shared.requests.serverclient.ServerClientRequest;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerSenderHandler implements ServerSender {
    private final static Logger LOGGER = Logger.getLogger(ServerSenderHandler.class.getName());

    @Override
    public void sendToClient(ConnectionStream connectionStream, ServerClientRequest serverClientRequest) {
        if (connectionStream.getObjectOutputStream().isPresent()) {
            ObjectOutputStream objectOutputStream = connectionStream.getObjectOutputStream().get();
            sendToClient(objectOutputStream, serverClientRequest);
        } else if (connectionStream.getRegistrable().isPresent()) {
            Registrable registrable = connectionStream.getRegistrable().get();
            sendToClient(registrable, serverClientRequest);
        }
    }

    private void sendToClient(ObjectOutputStream objectOutputStream,
                              ServerClientRequest serverClientRequest) {
        try {
            objectOutputStream.writeObject(serverClientRequest);
            objectOutputStream.flush();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: cannot send message via socket", e);
        }
    }

    private void sendToClient(Registrable registrable, ServerClientRequest serverClientRequest) {
        try {
            registrable.update(serverClientRequest);
        } catch (RemoteException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: cannot send message via RMI", e);
        }
    }
}
