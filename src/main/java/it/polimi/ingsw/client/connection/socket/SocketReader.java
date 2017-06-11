package it.polimi.ingsw.client.connection.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketReader implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(SocketReader.class.getName());

    private Socket socket;
    ObjectInputStream inputStream;

    public SocketReader(Socket socket) throws IOException {
        this.socket = socket;
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        while (true) {
            try {
                Object o = inputStream.readObject();
                System.out.println("Read object: " + o);
            } catch (IOException | ClassNotFoundException e) {
                LOGGER.log(Level.SEVERE, "An exception was thrown: ", e);
            }
        }
    }
}
