package it.polimi.ingsw.server.connection.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(ClientHandler.class.getName());

    private Socket socket;
    private ObjectOutputStream objectOutputStream;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        objectOutputStream = null;
    }

    public void run() {
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            Thread thread = new Thread(new Reader(socket, objectOutputStream));
            thread.start();
            thread.join();
            close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: streams init", e);
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: interrupted thread", e);
            close();
            Thread.currentThread().interrupt();
        }
    }

    private void close() {
        try {
            objectOutputStream.close();
            socket.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: cannot close output/socket", e);
        }
    }
}
