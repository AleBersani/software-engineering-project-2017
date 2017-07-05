package it.polimi.ingsw.server;

import it.polimi.ingsw.server.connection.rmi.RMIServerStarter;
import it.polimi.ingsw.server.connection.socket.SocketServerStarter;
import it.polimi.ingsw.server.gameelements.SetGameElements;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMain {
    private final static Logger LOGGER = Logger.getLogger(NewGamesHandler.class.getName());

    public static void main(String argv[]) {
        Thread socketServerStarted = new Thread(new SocketServerStarter());
        socketServerStarted.start();

        Thread rmiServerStarter = new Thread(new RMIServerStarter());
        rmiServerStarter.start();

        Thread gameElementsLoader = new Thread(new SetGameElements());
        gameElementsLoader.start();
        try {
            gameElementsLoader.join();
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: join game elements");
            Thread.currentThread().interrupt();
        }
        LOGGER.info("Game elements loading ended");
    }
}
