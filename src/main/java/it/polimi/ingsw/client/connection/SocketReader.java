package it.polimi.ingsw.client.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class SocketReader implements Runnable {
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
                e.printStackTrace();
            }
        }
    }
}
