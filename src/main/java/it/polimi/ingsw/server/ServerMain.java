package it.polimi.ingsw.server;

import it.polimi.ingsw.server.rmi.RMIInit;
import it.polimi.ingsw.server.rmi.nanohttpd.NanoInit;
import it.polimi.ingsw.server.socket.SocketInit;

public class ServerMain {
    public static void main(String argv[]) {
        Thread socketServer = new Thread(new SocketInit());
        socketServer.start();

        Thread nanoInit = new Thread(new NanoInit());
        nanoInit.start();

        Thread rmiServer = new Thread(new RMIInit());
        rmiServer.start();
    }
}
