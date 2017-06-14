package it.polimi.ingsw.server.rmi.nanohttpd;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NanoInit implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(NanoInit.class.getName());

    @Override
    public void run() {
        try {
            Runtime.getRuntime().exec("java -jar nanoHTTPD.jar");
            LOGGER.info("Starting nanoHTTPD...");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: Can't run nanoHTTPD.jar", e);
        }
    }
}
