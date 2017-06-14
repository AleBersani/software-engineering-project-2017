package it.polimi.ingsw.client.connection.rmi;

import it.polimi.ingsw.shared.model.PlayerDetails;
import it.polimi.ingsw.server.rmi.RMICommunicator;
import it.polimi.ingsw.shared.Client;
import it.polimi.ingsw.shared.Registrable;
import it.polimi.ingsw.shared.requests.clientserver.PlayerLogin;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RMIClient extends UnicastRemoteObject {
    private final static Logger LOGGER = Logger.getLogger(RMIClient.class.getName());
    private static final String URL = "communicator";

    private Registry registry;

    public static void main(String argv[]) {
        try {
            // Mando interfaccia e identificativo insieme, se fallisce l'autenticazione, rimando tutto
            PlayerDetails playerDetails = new PlayerDetails();
            playerDetails.setPlayerName("Dennis");
            //playerDetails.setPlayerIdentifier("Player2");

            Registrable client = new Client();
            RMIClient rmiClient = new RMIClient();

            rmiClient.getReceiver().recordClient("Player2", client);
            rmiClient.getReceiver().run(new PlayerLogin(playerDetails, "password"));
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

    public RMICommunicator getReceiver() throws RemoteException, NotBoundException {
        return (RMICommunicator)registry.lookup(URL);
    }
}
