package it.polimi.ingsw.client.connection.rmi;

import it.polimi.ingsw.server.rmi.RMICommunicator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

public class RMIClient extends UnicastRemoteObject {
    private final static Logger LOGGER = Logger.getLogger(RMIClient.class.getName());
    private final static String URL = "communicator";

    private static Registry registry;

    public RMIClient() throws RemoteException {
        registry = LocateRegistry.getRegistry();
    }

    public static void startRMIClient() throws RemoteException {
        registry = LocateRegistry.getRegistry();
    }

    public static RMICommunicator getReceiver() throws RemoteException, NotBoundException {
        return (RMICommunicator)registry.lookup(URL);
    }
}
