package it.polimi.ingsw.server.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(ClientHandler.class.getName());

    private static boolean clientAlive = true;

    private Socket socket;
    private ObjectOutputStream objectOutputStream;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            Thread thread = new Thread(new Reader(socket, objectOutputStream));
            thread.start();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: streams init", e);
        }
    }

    private void close() {
        try {
            objectOutputStream.close();
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
