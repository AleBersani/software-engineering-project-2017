package it.polimi.ingsw.client.connection.socket;

import it.polimi.ingsw.client.connection.middleware.ClientReceiver;
import it.polimi.ingsw.client.connection.middleware.ClientReceiverHandler;
import it.polimi.ingsw.shared.requests.serverclient.ServerClientRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reader implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(Reader.class.getName());

    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ClientReceiver serverClientReceiverHandler;

    public Reader(Socket socket) throws IOException {
        this.socket = socket;
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        serverClientReceiverHandler  = new ClientReceiverHandler();
    }

    @Override
    public void run() {
        boolean keepAlive = true;
        while (keepAlive) {
            try {
                ServerClientRequest serverClientRequest = (ServerClientRequest)objectInputStream.readObject();
                serverClientRequest.acceptServerClientRequestVisitor(serverClientReceiverHandler);
            } catch (IOException | ClassNotFoundException e) {
                keepAlive = false;
                LOGGER.log(Level.SEVERE, "An exception was thrown: cannot read on socket", e);
            }
        }
        closeInput();
    }

    private void closeInput() {
        try {
            objectInputStream.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: cannot close client input stream", e);
        }
    }
}
