package it.polimi.ingsw.server.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketServer {
    private final static Logger LOGGER = Logger.getLogger(SocketServer.class.getName());

    private int port;

    private ServerSocket serverSocket;
    private ExecutorService executorService;

    public SocketServer(int port) {
        this.port = port;
        executorService = Executors.newCachedThreadPool();
    }

    public void startServer() {
        initServer();
        handleConnections();
        executorService.shutdown();
    }

    private void initServer() {
        try {
            serverSocket = new ServerSocket(port);
            LOGGER.info("Server socket ready on port: " + port);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: Cannot open socket server", e);
        }
    }

    private void handleConnections() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                executorService.submit(new ClientHandler(socket));
                //handleGameSessions(socket);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "An exception was thrown: Server socket closed", e);
                break;
            }
        }
    }
}
