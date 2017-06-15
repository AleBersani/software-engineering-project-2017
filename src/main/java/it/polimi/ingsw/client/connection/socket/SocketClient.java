package it.polimi.ingsw.client.connection.socket;

import it.polimi.ingsw.shared.requests.clientserver.ClientServerRequest;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class SocketClient {
    private final static Logger LOGGER = Logger.getLogger(SocketClient.class.getName());
    private final static int PORT = 6677;

    private static Socket socket;
    private static ObjectOutputStream objectOutputStream;

/*
    public static void main(String argv[]) {
        SocketClient socketClient = new SocketClient("127.0.0.1", 6677);
        try {
            socketClient.startSocketClient();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: Socket Connection Error", e);
        }

        try {
            socketClient.writeSocket(new Connection("Dennis", "password"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: ", e);
        }
        try {
            socketClient.writeSocket(new Connection("Dennis", "password"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: ", e);
        }
    }
*/

    public static void startSocketClient(String ip) throws IOException {
        socket = new Socket(ip, PORT);
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

    public static void writeSocket(ClientServerRequest clientServerRequest) throws IOException {
        objectOutputStream.writeObject(clientServerRequest);
        objectOutputStream.flush();
    }
}
