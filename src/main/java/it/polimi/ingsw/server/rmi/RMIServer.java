package it.polimi.ingsw.server.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class RMIServer {
    private final static Logger LOGGER = Logger.getLogger(RMIServer.class.getName());

    public static void initRMIServer() {
        try {
            Communicator communicator = new Communicator();
            try {
                Registry registry = LocateRegistry.createRegistry(1099);
                registry.bind("communicator", communicator);
                LOGGER.info("Waiting for RMI clients");
            } catch (RemoteException | AlreadyBoundException e) {
                LOGGER.log(Level.SEVERE, "An exception was thrown: RMI setup error", e);
            }
        } catch (RemoteException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: RMI calling error", e);
        }
    }
}
