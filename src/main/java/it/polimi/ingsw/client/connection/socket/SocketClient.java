package it.polimi.ingsw.client.connection.socket;

import it.polimi.ingsw.server.connection.socket.SocketServer;
import it.polimi.ingsw.shared.requests.clientserver.ClientServerRequest;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketClient {
    private final static Logger LOGGER = Logger.getLogger(SocketClient.class.getName());
    private final static String ADDRESS = "127.0.0.1";
    private final static int PORT = 6677;

    private static Socket socket;
    private static ObjectOutputStream objectOutputStream;

    private SocketClient() {}

    private static class SocketClientHolder {
        private static final SocketClient INSTANCE = new SocketClient();
    }

    public static SocketClient getInstance() {
        return SocketClientHolder.INSTANCE;
    }

    public void startSocketClient() throws IOException {
        socket = new Socket(ADDRESS, PORT);
        LOGGER.info("Connection established");
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        Thread thread = new Thread(new Reader(socket));
        thread.start();/*
        try {
            thread.join();
            closeConnection();
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: interrupted thread", e);
        }*/
    }

    private void closeConnection() {
        try {
            objectOutputStream.close();
            socket.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: cannot close socket client", e);
        }
    }

    public static void writeSocket(ClientServerRequest clientServerRequest) throws IOException {
        objectOutputStream.writeObject(clientServerRequest);
        objectOutputStream.flush();
    }
}
