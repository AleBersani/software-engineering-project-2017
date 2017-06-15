package it.polimi.ingsw.server.rmi;

import it.polimi.ingsw.server.middleware.ServerReceiver;
import it.polimi.ingsw.server.middleware.ServerReceiverHandler;
import it.polimi.ingsw.shared.requests.clientserver.ClientServerRequest;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Communicator extends UnicastRemoteObject implements RMICommunicator {
    private final static Logger LOGGER = Logger.getLogger(Communicator.class.getName());

    private ClientServerRequest clientServerRequest;

    public Communicator() throws RemoteException {
        clientServerRequest = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Communicator receiver = (Communicator) o;
        return Objects.equals(clientServerRequest, receiver.clientServerRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clientServerRequest);
    }

    @Override
    public void run(ClientServerRequest clientServerRequest) throws RemoteException {
        ServerReceiver clientServerRequestHandler = new ServerReceiverHandler();
        clientServerRequest.acceptClientServerRequestVisitor(clientServerRequestHandler);
    }
/*
    @Override
    public void recordClient(PlayerLoginRMI playerLoginRMI) throws RemoteException {
        if (!GamesConnections.playerAlreadyRegistered(playerName)) {
            GamesConnections.addClient(playerName, registrable);
        }
    }*/

    public static void doCallback(String identifier, String msg) throws RemoteException{
        LOGGER.log(Level.INFO, () -> "Sending callback to: " + identifier);
        //GamesConnections.getClients().get(identifier).update(msg);
        LOGGER.log(Level.INFO, "Callback complete");
    }

    public static void doCallbacks(String msg) throws RemoteException{/*
        GamesConnections.getClients().forEach((k, v) -> {
            try {
                v.update(msg);
                System.out.println("Callback");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });*/
    }
}
