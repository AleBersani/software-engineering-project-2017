package it.polimi.ingsw.server;

import it.polimi.ingsw.server.rmi.RMIInit;
import it.polimi.ingsw.server.socket.SocketInit;

public class ServerMain {
    public static void main(String argv[]) {
        GamesConnections.init();

        Thread socketServer = new Thread(new SocketInit());
        socketServer.start();

        Thread rmiServer = new Thread(new RMIInit());
        rmiServer.start();
    }
}
