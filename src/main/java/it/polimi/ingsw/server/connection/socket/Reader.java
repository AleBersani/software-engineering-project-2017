package it.polimi.ingsw.server.connection.socket;

import it.polimi.ingsw.server.NewGamesHandler;
import it.polimi.ingsw.server.middleware.ServerReceiverHandler;
import it.polimi.ingsw.shared.requests.clientserver.ClientServerRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reader implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Reader.class.getName());

    private Socket socket;
    private ObjectOutputStream objectOutputStream;

    private ObjectInputStream objectInputStream;
    private ServerReceiverHandler serverReceiverHandler;

    public Reader(Socket socket, ObjectOutputStream objectOutputStream) throws IOException {
        this.socket = socket;
        this.objectOutputStream = objectOutputStream;
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        serverReceiverHandler = new ServerReceiverHandler(objectOutputStream);
    }

    @Override
    public void run() {
        boolean keepAlive = true;
        serverReceiverHandler.addObserver(NewGamesHandler.getInstance());
        while (keepAlive) {
            try {
                ClientServerRequest clientServerRequest = (ClientServerRequest)objectInputStream.readObject();
                clientServerRequest.acceptServerReceiver(serverReceiverHandler);
            } catch (IOException | ClassNotFoundException e) {
                // TODO: Player disconnesso
                keepAlive = false;
                LOGGER.log(Level.SEVERE, "An exception was thrown: disconnected player", e);
            }
        }
        closeInput();
    }

    private void closeInput() {
        try {
            objectInputStream.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: cannot close server input stream", e);
        }
    }
}


