package it.polimi.ingsw.shared.support;

import it.polimi.ingsw.client.middleware.ClientReceiver;
import it.polimi.ingsw.client.middleware.ClientReceiverHandler;
import it.polimi.ingsw.shared.requests.serverclient.ServerClientRequest;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements Registrable, Serializable {
    public Client() throws RemoteException {}

    @Override
    public void update(ServerClientRequest serverClientRequest) throws RemoteException {
        ClientReceiver serverClientReceiverHandler = ClientReceiverHandler.getInstance();
        serverClientRequest.acceptServerClientRequestVisitor(serverClientReceiverHandler);
    }
}
