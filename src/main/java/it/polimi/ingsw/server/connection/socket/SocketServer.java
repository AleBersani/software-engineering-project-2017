package it.polimi.ingsw.server.connection.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketServer {
    private final static Logger LOGGER = Logger.getLogger(SocketServer.class.getName());
    private final static int PORT = 6677;

    private ServerSocket serverSocket;
    private ExecutorService executorService;

    private SocketServer() {
        executorService = Executors.newCachedThreadPool();
    }

    private static class SocketServerHolder {
        private static final SocketServer INSTANCE = new SocketServer();
    }

    public static SocketServer getSocketServer() {
        return SocketServerHolder.INSTANCE;
    }

    public void startServer() {
        initServer();
        handleConnections();
        executorService.shutdown();
    }

    private void initServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            LOGGER.log(Level.INFO, () -> "Server socket ready on port: " + PORT);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: cannot open socket server", e);
        }
    }

    private void handleConnections() {
        boolean keepAlive = true;
        while (keepAlive) {
            try {
                Socket socket = serverSocket.accept();
                socket.setKeepAlive(true);
                executorService.submit(new ClientHandler(socket));
            } catch (IOException e) {
                keepAlive = false;
                LOGGER.log(Level.SEVERE, "An exception was thrown: server socket closed", e);
            }
        }
    }
}
