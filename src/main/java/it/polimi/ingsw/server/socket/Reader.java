package it.polimi.ingsw.server.socket;

import it.polimi.ingsw.server.middleware.ServerReceiver;
import it.polimi.ingsw.server.middleware.ServerReceiverHandler;
import it.polimi.ingsw.shared.requests.clientserver.ClientServerRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reader implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(Reader.class.getName());

    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public Reader(Socket socket, ObjectOutputStream objectOutputStream) throws IOException {
        this.socket = socket;
        this.objectOutputStream = objectOutputStream;
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        SocketOutputMemory.add(Thread.currentThread().getId(), objectOutputStream);
        while (true) {
            try {
                ClientServerRequest clientServerRequest = (ClientServerRequest)objectInputStream.readObject();
                ServerReceiver clientServerRequestHandler = new ServerReceiverHandler();
                clientServerRequest.acceptClientServerRequestVisitor(clientServerRequestHandler);
            } catch (IOException | ClassNotFoundException e) {
                // TODO: Player disconnesso
                LOGGER.log(Level.SEVERE, "An exception was thrown: disconnected player", e);
            }
        }
    }
}


