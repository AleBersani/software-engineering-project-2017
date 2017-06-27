package it.polimi.ingsw.server.connection.rmi;

import it.polimi.ingsw.server.NewGamesHandler;
import it.polimi.ingsw.server.middleware.ServerReceiver;
import it.polimi.ingsw.server.middleware.ServerReceiverHandler;
import it.polimi.ingsw.shared.requests.clientserver.ClientServerRequest;
import it.polimi.ingsw.shared.requests.clientserver.GameStartChoiceRMI;
import it.polimi.ingsw.shared.requests.clientserver.PlayerLoginRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

public class Communicator extends UnicastRemoteObject implements RMICommunicator {
    private final static Logger LOGGER = Logger.getLogger(Communicator.class.getName());

    public Communicator() throws RemoteException {}

    @Override
    public void login(PlayerLoginRMI playerLoginRMI) throws RemoteException {
        ServerReceiverHandler serverReceiverHandler = new ServerReceiverHandler();
        playerLoginRMI.acceptServerReceiver(serverReceiverHandler);
        //serverReceiverHandler.addObserver(NewGamesHandler.getInstance());
        //playerLoginRMI.acceptServerReceiver(serverReceiverHandler);
    }

    @Override
    public void startGame(GameStartChoiceRMI gameStartChoiceRMI) throws RemoteException {
        ServerReceiverHandler serverReceiverHandler = new ServerReceiverHandler();
        serverReceiverHandler.addObserver(NewGamesHandler.getInstance());
        gameStartChoiceRMI.acceptServerReceiver(serverReceiverHandler);
    }

    @Override
    public void run(ClientServerRequest clientServerRequest) throws RemoteException {
        ServerReceiver clientServerRequestHandler = new ServerReceiverHandler();
        clientServerRequest.acceptServerReceiver(clientServerRequestHandler);
    }

    /*
    public static void doCallback(String identifier, String msg) throws RemoteException{
        LOGGER.log(Level.INFO, () -> "Sending callback to: " + identifier);
        //GamesConnections.getClients().get(identifier).update(msg);
        LOGGER.log(Level.INFO, "Callback complete");
    }

    public static void doCallbacks(String msg) throws RemoteException{/*
        GamesConnections.getClients().forEach((k, v) -> {
            try {
                v.update(msg);
                System.out.println("Callback");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }*/
}
