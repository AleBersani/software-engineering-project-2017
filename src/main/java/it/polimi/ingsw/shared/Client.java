package it.polimi.ingsw.shared;

import it.polimi.ingsw.client.connection.middleware.ClientReceiver;
import it.polimi.ingsw.client.connection.middleware.ClientReceiverHandler;
import it.polimi.ingsw.shared.requests.serverclient.ServerClientRequest;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements Registrable, Serializable {
    public Client() throws RemoteException {
    }

    @Override
    public void update(ServerClientRequest serverClientRequest) throws RemoteException {
        ClientReceiver serverClientReceiverHandler = new ClientReceiverHandler();
        serverClientRequest.acceptServerClientRequestVisitor(serverClientReceiverHandler);
    }
}
