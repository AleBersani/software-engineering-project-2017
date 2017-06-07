package it.polimi.ingsw.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Server {
    private final static Logger LOGGER = Logger.getLogger(Server.class.getName());

    private int port;

    public static void main(String argv[]) {
        Server server = new Server(6677);
        server.startServer();
    }

    public Server(int port) {
        this.port = port;
    }

    public void startServer() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            LOGGER.info(e.getMessage());
            return;
        }
        LOGGER.info("Server ready on port: " + port);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                executorService.submit(new ClientHandler(socket));
            } catch (IOException e) {
                /*
                TODO: serverSocket closed
                 */
                break;
            }
        }
        executorService.shutdown();
    }
}
