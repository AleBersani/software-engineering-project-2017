package it.polimi.ingsw.server.rmi;

import it.polimi.ingsw.server.GamesConnections;
import it.polimi.ingsw.shared.Registrable;
import it.polimi.ingsw.server.exectutionmiddleware.RequestHandler;
import it.polimi.ingsw.shared.requests.clientserver.ClientServerRequest;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;
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
        RequestHandler requestHandler = new RequestHandler();
        clientServerRequest.acceptClientServerRequestVisitor(requestHandler);
    }

    @Override
    public synchronized void recordClient(String identifier, Registrable registrable) throws RemoteException {
        /*if (!GamesConnections.getClients().containsKey(identifier)) {
            GamesConnections.addClient(identifier, registrable);
        }*/
    }

    @Override
    public synchronized void deleteClient(String identifier, Registrable client) throws RemoteException {
        /*
        if (ConnectionMap.removeClient((client)) {
            LOGGER.info("RMI client deleted");
        } else {
            LOGGER.info("RMI client to delete not found");
        }*/
    }

    public static void doCallback(String identifier, String msg) throws RemoteException{
        LOGGER.info("Sending callback to: " + identifier);
        //GamesConnections.getClients().get(identifier).update(msg);
        System.out.println("Callbacks complete.");
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