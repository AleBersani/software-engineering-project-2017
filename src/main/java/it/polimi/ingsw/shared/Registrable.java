package it.polimi.ingsw.shared;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Registrable extends Remote, Serializable {
    void update(String msg) throws RemoteException;
}
