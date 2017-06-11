package it.polimi.ingsw.server;

import it.polimi.ingsw.shared.requests.clientserver.PawnPlacement;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(ClientHandler.class.getName());

    private Socket socket;

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                PawnPlacement actionDetails = (PawnPlacement)objectInputStream.readObject();
                ReceiverHandler receiverHandler = new ReceiverHandler(actionDetails);
                receiverHandler.run();

            }/*
            objectOutputStream.close();
            objectInputStream.close();
            socket.close();*/
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
