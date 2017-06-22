package it.polimi.ingsw.client.connection.rmi;

import it.polimi.ingsw.server.connection.rmi.RMICommunicator;
import it.polimi.ingsw.shared.requests.clientserver.ClientServerRequest;
import it.polimi.ingsw.shared.requests.clientserver.PlayerLoginRMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RMIClient extends UnicastRemoteObject {
    private static final Logger LOGGER = Logger.getLogger(RMIClient.class.getName());
    private static final String URL = "communicator";

    private static volatile RMIClient instance;

    private static Registry registry;

    private RMIClient() throws RemoteException {
        registry = LocateRegistry.getRegistry();
    }

    public static RMIClient getInstance() {
        if (instance == null) {
            synchronized (RMIClient.class) {
                if (instance == null) {
                    try {
                        instance = new RMIClient();
                    } catch (RemoteException e) {
                        LOGGER.log(Level.SEVERE, "An exception was thrown: cannot start RMI client", e);
                    }
                }
            }
        }
        return instance;
    }

    public void startRMIClient() throws RemoteException {
        registry = LocateRegistry.getRegistry();
    }

    public static void remoteLogin(PlayerLoginRMI playerLoginRMI) throws RemoteException, NotBoundException {
        RMICommunicator rmiCommunicator = (RMICommunicator)registry.lookup(URL);
        rmiCommunicator.login(playerLoginRMI);
    }

    public static void callMethod(ClientServerRequest clientServerRequest) throws RemoteException, NotBoundException {
        RMICommunicator rmiCommunicator = (RMICommunicator)registry.lookup(URL);
        rmiCommunicator.run(clientServerRequest);
    }
}
