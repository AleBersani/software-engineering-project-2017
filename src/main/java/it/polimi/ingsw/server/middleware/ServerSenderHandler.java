package it.polimi.ingsw.server.middleware;

import it.polimi.ingsw.server.connection.ConnectionStream;
import it.polimi.ingsw.shared.requests.serverclient.ServerClientRequest;
import it.polimi.ingsw.shared.support.Registrable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerSenderHandler implements ServerSender {
    private final static Logger LOGGER = Logger.getLogger(ServerSenderHandler.class.getName());

    @Override
    public void sendToClient(ConnectionStream connectionStream, ServerClientRequest serverClientRequest) {
        Optional<ObjectOutputStream> objectOutputStream = connectionStream.getObjectOutputStream();
        Optional<Registrable> registrable = connectionStream.getRegistrable();
        if (objectOutputStream.isPresent()) {
            sendToClient(objectOutputStream.get(), serverClientRequest);
        } else registrable.ifPresent(r -> sendToClient(r, serverClientRequest));
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
