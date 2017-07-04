package it.polimi.ingsw.server;

import it.polimi.ingsw.server.connection.ConnectedClient;
import it.polimi.ingsw.server.database.QueryHandler;
import it.polimi.ingsw.server.gamecontroller.Game;
import it.polimi.ingsw.server.gamelogic.basics.GameConfiguration;
import it.polimi.ingsw.server.middleware.ServerSender;
import it.polimi.ingsw.server.middleware.ServerSenderHandler;
import it.polimi.ingsw.shared.requests.serverclient.SimpleMessage;
import it.polimi.ingsw.shared.support.GameStartType;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

public class NewGamesHandler implements Observer {
    private final static Logger LOGGER = Logger.getLogger(NewGamesHandler.class.getName());
    private final static int MIN_PLAYERS = 2;

    private final Queue<ConnectedClient> connectedClientList;
    private final int MAX_PLAYERS_FOR_GAME;

    private int playersCounter;
    private Timer timer;

    private NewGamesHandler() {
        connectedClientList = new ConcurrentLinkedQueue<>();
        MAX_PLAYERS_FOR_GAME = GameConfiguration.getMaxNumberOfPlayer();
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
        GameStartType gameStartType = ((NewGameInformation)arg).getGameStartType();
        ConnectedClient connectedClient = ((NewGameInformation)arg).getConnectedClient();
        if (gameStartType == GameStartType.NEW) {
            handleNewPlayersInNewGame(connectedClient);
        } else if (gameStartType == GameStartType.RESUME) {
            //
        }
    }

    private void handleNewPlayersInNewGame(ConnectedClient connectedClient) {
        if (playersCounter < MAX_PLAYERS_FOR_GAME) {
            addNewPlayer(connectedClient);
            if (playersCounter == MIN_PLAYERS) {
                startTimer();
            }
            if (playersCounter == MAX_PLAYERS_FOR_GAME) {
                createNewGame();
                timer.cancel();
            }
        }
    }

    private void addNewPlayer(ConnectedClient connectedClient) {
        ServerSender serverSender = new ServerSenderHandler();
        serverSender.sendToClient(connectedClient.getConnectionStream(), new SimpleMessage("Registered to a game!"));
        connectedClientList.add(connectedClient);
        playersCounter++;
        LOGGER.info("Player registered: " + connectedClient.getPlayerName());
    }

    private void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                createNewGame();
                LOGGER.info("Time expired!");
            }
        }, GameConfiguration.getStartingGameTimeout() * 1000);
    }

    private void createNewGame() {
        QueryHandler queryHandler = new QueryHandler();
        queryHandler.addGame();
        int lastGameId = queryHandler.getLastGameID();
        for (ConnectedClient client: connectedClientList)
            queryHandler.addPlayerToGame(client.getPlayerName(), lastGameId);
        Game game = new Game(lastGameId, connectedClientList);
        ActiveGames.getInstance().addGame(game);
        Thread newGame = new Thread(game);
        newGame.start();
        connectedClientList.clear();
        playersCounter = 0;
        LOGGER.info("New game started!");
    }
}
