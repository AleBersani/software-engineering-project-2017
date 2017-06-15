package it.polimi.ingsw.server.rmi;

import it.polimi.ingsw.shared.requests.clientserver.ClientServerRequest;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMICommunicator extends Remote {
    void run(ClientServerRequest clientServerRequest) throws RemoteException;
}
