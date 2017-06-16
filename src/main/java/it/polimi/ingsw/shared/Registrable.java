package it.polimi.ingsw.shared;

import it.polimi.ingsw.shared.requests.serverclient.ServerClientRequest;
import org.apache.maven.settings.Server;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Registrable extends Remote, Serializable {
    void update(ServerClientRequest serverClientRequest) throws RemoteException;
}
