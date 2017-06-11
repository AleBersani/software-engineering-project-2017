package it.polimi.ingsw.client.connection.rmi;

import it.polimi.ingsw.server.rmi.RMIReceiver;
import it.polimi.ingsw.server.rmi.nanohttpd.NanoInit;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RMIClient {
    private final static Logger LOGGER = Logger.getLogger(RMIClient.class.getName());
    private static final String URL = "receiver";

    private Registry registry;

    public static void main(String argv[]) {
        try {
            RMIClient rmiClient = new RMIClient();
            System.out.println(rmiClient.getReceiver().testRMI());
        } catch (RemoteException | NotBoundException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: ", e);
        }
    }

    public RMIClient() throws RemoteException {
        registry = LocateRegistry.getRegistry();
    }

    public String[] getRegistryBindings() throws RemoteException {
        return registry.list();
    }

    public RMIReceiver getReceiver() throws RemoteException, NotBoundException {
        return (RMIReceiver) registry.lookup(URL);
    }
}
