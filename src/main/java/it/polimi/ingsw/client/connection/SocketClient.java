package it.polimi.ingsw.client.connection;

import it.polimi.ingsw.gamelogic.enums.ActionType;
import it.polimi.ingsw.gamelogic.enums.PawnColor;
import it.polimi.ingsw.shared.requests.clientserver.PawnPlacement;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class SocketClient {
    private final static Logger LOGGER = Logger.getLogger(SocketClient.class.getName());

    private String ip;
    private int port;

    private Socket socket;
    private ObjectOutputStream objectOutputStream;

    public static void main(String argv[]) {
        SocketClient socketClient = new SocketClient("127.0.0.1", 6677);
        try {
            socketClient.startSocketClient();
        } catch (IOException e) {
            LOGGER.info("Socket Connection Error");
        }
    }

    public SocketClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void startSocketClient() throws IOException {
        socket = new Socket(ip, port);
        LOGGER.info("Connection established");
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        Thread thread = new Thread(new SocketReader(socket));
        thread.start();
    }

    private void closeConnection() {
        /*
        TODO
         */
    }

    public void writeSocket(Object o) throws IOException {
        objectOutputStream.writeObject(o);
        objectOutputStream.flush();
    }
}
