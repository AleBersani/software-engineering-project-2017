package it.polimi.ingsw.server.socket;


import it.polimi.ingsw.shared.requests.serverclient.ServerClientRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Writer {
    private final static Logger LOGGER = Logger.getLogger(Reader.class.getName());

    public static void write(ObjectOutputStream objectOutputStream, ServerClientRequest serverClientRequest) {
        try {
            objectOutputStream.writeObject(serverClientRequest);
            objectOutputStream.flush();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: cannot send message", e);
        }
    }
}
