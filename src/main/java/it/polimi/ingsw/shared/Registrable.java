package it.polimi.ingsw.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Registrable extends Remote {
    void update(String msg) throws RemoteException;
}
