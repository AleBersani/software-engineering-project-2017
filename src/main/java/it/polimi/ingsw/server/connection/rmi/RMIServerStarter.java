package it.polimi.ingsw.server.connection.rmi;

public class RMIServerStarter implements Runnable {
    @Override
    public void run() {
        RMIServer rmiServer = RMIServer.getInstance();
        rmiServer.initRMIServer();
    }
}
