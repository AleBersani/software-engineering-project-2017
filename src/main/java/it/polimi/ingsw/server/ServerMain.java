package it.polimi.ingsw.server;

import it.polimi.ingsw.server.connection.rmi.RMIServerStarter;
import it.polimi.ingsw.server.connection.socket.SocketServerStarter;
import it.polimi.ingsw.server.gameelements.SetGameElements;

public class ServerMain {
    public static void main(String argv[]) {
        Thread socketServerStarted = new Thread(new SocketServerStarter());
        socketServerStarted.start();

        Thread rmiServerStarter = new Thread(new RMIServerStarter());
        rmiServerStarter.start();

        Thread gameElementsLoader = new Thread(new SetGameElements());
        gameElementsLoader.start();
    }
}
