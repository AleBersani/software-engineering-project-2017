package it.polimi.ingsw.server.socket;

import it.polimi.ingsw.server.middleware.ServerReceiverHandler;
import it.polimi.ingsw.shared.requests.clientserver.ClientServerRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(ClientHandler.class.getName());

    private static boolean clientAlive = true;

    private Socket socket;

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        streamsInit();
        keepReading();
        close();
    }

    private void streamsInit() {
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: ", e);
        }
    }

    private void keepReading() {
        while (clientAlive) {
            try {
                ClientServerRequest clientServerRequest = (ClientServerRequest)objectInputStream.readObject();
                ServerReceiverHandler clientServerRequestHandler = new ServerReceiverHandler();
                clientServerRequest.acceptClientServerRequestVisitor(clientServerRequestHandler);
            } catch (IOException | ClassNotFoundException e) {
                LOGGER.log(Level.SEVERE, "An exception was thrown: ", e);
            }
        }
    }

    private void close() {
        try {
            objectOutputStream.close();
            objectInputStream.close();
            socket.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: ", e);
        }
    }

    public static boolean isClientAlive() {
        return clientAlive;
    }

    public static void setClientAlive(boolean clientAlive) {
        ClientHandler.clientAlive = clientAlive;
    }
}
