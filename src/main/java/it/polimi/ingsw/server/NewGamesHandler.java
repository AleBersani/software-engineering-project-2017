package it.polimi.ingsw.server;

import it.polimi.ingsw.server.connection.ConnectedClient;
import it.polimi.ingsw.server.database.QueryHandler;
import it.polimi.ingsw.server.gamecontroller.Game;
import it.polimi.ingsw.server.middleware.ServerSender;
import it.polimi.ingsw.server.middleware.ServerSenderHandler;
import it.polimi.ingsw.shared.requests.serverclient.SimpleMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

public class NewGamesHandler implements Observer {
    private final static Logger LOGGER = Logger.getLogger(NewGamesHandler.class.getName());

    private final List<ConnectedClient> connectedClientList;
    private final int MAX_PLAYERS_FOR_GAME;

    private int playersCounter;

    private NewGamesHandler() {
        connectedClientList = new ArrayList<>();
        MAX_PLAYERS_FOR_GAME = 3;
        playersCounter = 0;
    }

    private static class GamesHandlerHolder {
        private static final NewGamesHandler INSTANCE = new NewGamesHandler();
    }

    public static NewGamesHandler getInstance() {
        return GamesHandlerHolder.INSTANCE;
    }

    @Override
    public synchronized void update(Observable o, Object arg) {
        if (playersCounter < MAX_PLAYERS_FOR_GAME) {
            ConnectedClient connectedClient = (ConnectedClient)arg;
            ServerSender serverSender = new ServerSenderHandler();
            serverSender.sendToClient(connectedClient.getConnectionStream(), new SimpleMessage("Connected!"));
            LOGGER.info("Client registered");
            playersCounter++;
            if (playersCounter == MAX_PLAYERS_FOR_GAME) {
                QueryHandler queryHandler = new QueryHandler();
                queryHandler.addGame();
                int lastGameId = queryHandler.getLastGameID();
                for (ConnectedClient client: connectedClientList)
                    queryHandler.addPlayerToGame(client.getPlayerName(), lastGameId);
                Game game = new Game(lastGameId, connectedClientList);
                connectedClientList.clear();
                Thread newGame = new Thread(game);
                newGame.start();
                playersCounter = 0;
            }
        }
    }
}
