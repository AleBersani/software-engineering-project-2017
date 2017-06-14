package it.polimi.ingsw.server.rmi;

import it.polimi.ingsw.shared.Client;
import it.polimi.ingsw.shared.Registrable;
import it.polimi.ingsw.shared.requests.clientserver.ClientServerRequest;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMICommunicator extends Remote {
    void run(ClientServerRequest clientServerRequest) throws RemoteException;
    void recordClient(String identifier, Registrable registrable) throws RemoteException;
    void deleteClient(String identifier, Registrable registrable) throws RemoteException;
}
