package it.polimi.ingsw.server;

import it.polimi.ingsw.shared.model.PlayerDetails;
import it.polimi.ingsw.shared.Registrable;

import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GamesConnections extends UnicastRemoteObject {
    private static List<StreamOfPlayersInGame<Socket>> socketPlayersInGames;
    private static List<StreamOfPlayersInGame<Registrable>> rmiPlayersInGames;

    private GamesConnections() throws RemoteException {
        throw new IllegalAccessError("Utility class");
    }

    public static void init() {
        socketPlayersInGames = new ArrayList<>();
        rmiPlayersInGames = new ArrayList<>();
    }

    public static void addClient(String playerName, Socket socket) {
        if (socketPlayersInGames.isEmpty()) {
        }
    }

    public static void addClient(String playerName, Registrable registrable) {

    }

    public static boolean removeClient(Registrable client) {
        // TODO
        return false;
    }

}
