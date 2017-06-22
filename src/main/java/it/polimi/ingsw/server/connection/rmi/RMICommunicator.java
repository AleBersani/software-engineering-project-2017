package it.polimi.ingsw.server.connection.rmi;

import it.polimi.ingsw.shared.requests.clientserver.ClientServerRequest;
import it.polimi.ingsw.shared.requests.clientserver.PlayerLoginRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMICommunicator extends Remote {
    void run(ClientServerRequest clientServerRequest) throws RemoteException;
    void login(PlayerLoginRMI playerLoginRMI) throws RemoteException;
}
