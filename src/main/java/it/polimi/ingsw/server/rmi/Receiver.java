package it.polimi.ingsw.server.rmi;

import it.polimi.ingsw.server.exectutionmiddleware.RequestHandler;
import it.polimi.ingsw.shared.requests.clientserver.ClientServerRequest;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

public class Receiver extends UnicastRemoteObject implements RMIReceiver {
    private ClientServerRequest clientServerRequest;

    public Receiver() throws RemoteException {
        clientServerRequest = null;
    }

    public Receiver(ClientServerRequest clientServerRequest) throws RemoteException {
        this.clientServerRequest = clientServerRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Receiver receiver = (Receiver) o;
        return Objects.equals(clientServerRequest, receiver.clientServerRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clientServerRequest);
    }

    @Override
    public void run(ClientServerRequest clientServerRequest) throws RemoteException {
        RequestHandler requestHandler = new RequestHandler();
        clientServerRequest.acceptClientServerRequestVisitor(requestHandler);
    }

    public void run() {
        RequestHandler requestHandler = new RequestHandler();
        clientServerRequest.acceptClientServerRequestVisitor(requestHandler);
    }

    @Override
    public int testRMI() throws RemoteException {
        return 1;
    }
}
