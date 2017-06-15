package it.polimi.ingsw.server;

import it.polimi.ingsw.server.database.QueryHandler;
import it.polimi.ingsw.shared.Registrable;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public final class GamesConnections {
    private static List<GameConnections> gameConnectionsList;

    private GamesConnections() {
        throw new IllegalAccessError("Utility class");
    }

    public static void init() {
        gameConnectionsList = new ArrayList<>();
    }

    public static void addClient(String playerName, Socket socket) {
        if (newGameConnectionsNeeded()) {
            createNewGameConnections();
        }
        gameConnectionsList.get(gameConnectionsList.size() - 1).addPlayer(playerName, socket);
    }

    public static void addClient(String playerName, Registrable registrable) {
        if (newGameConnectionsNeeded()) {
            createNewGameConnections();
        }
        gameConnectionsList.get(gameConnectionsList.size() - 1).addPlayer(playerName, registrable);
    }

    private static boolean newGameConnectionsNeeded() {
        return gameConnectionsList.isEmpty() ||
                gameConnectionsList.stream().allMatch(GameConnections::isStartedGame);
    }

    public static boolean playerAlreadyRegistered(String playerName) {
        return getGameConnectionsList()
                .stream()
                .anyMatch(gameConnections -> gameConnections.hasPlayer(playerName));
    }

    public static ConnectionStream getPlayerConnectionStream(String playerName) {
        for (GameConnections gameConnections: gameConnectionsList) {
            if (gameConnections.hasPlayer(playerName)) {
                return gameConnections.getConnectionStream(playerName);
            }
        }
        throw new IllegalStateException("Player doesn't exist");
    }

    private synchronized static void createNewGameConnections() {
        int nextGameID = QueryHandler.getLastGameID() + 1;
        gameConnectionsList.add(new GameConnections(nextGameID));
    }

    public static List<GameConnections> getGameConnectionsList() {
        return gameConnectionsList;
    }
}
