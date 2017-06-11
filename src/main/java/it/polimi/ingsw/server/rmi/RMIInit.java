package it.polimi.ingsw.server.rmi;

public class RMIInit implements Runnable {
    @Override
    public void run() {
        RMIServer.initRMIServer();
    }
}
