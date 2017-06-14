package it.polimi.ingsw.shared;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements Registrable, Serializable {
    public Client() throws RemoteException {
    }

    @Override
    public void update(String msg) throws RemoteException {
        System.out.println(msg);
    }
}
