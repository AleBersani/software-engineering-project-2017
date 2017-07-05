package it.polimi.ingsw.shared.support;

import it.polimi.ingsw.shared.requests.serverclient.ServerClientRequest;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

@FunctionalInterface
public interface Registrable extends Remote, Serializable {
    void update(ServerClientRequest serverClientRequest) throws RemoteException;
}
