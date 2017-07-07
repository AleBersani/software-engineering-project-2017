package it.polimi.ingsw.server.middleware;

import it.polimi.ingsw.server.ActiveGames;
import it.polimi.ingsw.server.NewGameInformation;
import it.polimi.ingsw.server.connection.ConnectedClient;
import it.polimi.ingsw.server.connection.ConnectionStream;
import it.polimi.ingsw.server.database.QueryHandler;
import it.polimi.ingsw.server.gamecontroller.Game;
import it.polimi.ingsw.shared.requests.clientserver.*;
import it.polimi.ingsw.shared.requests.serverclient.LoginResponse;
import it.polimi.ingsw.shared.support.Registrable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerReceiverHandler extends Observable implements ServerReceiver {
    private static final Logger LOGGER = Logger.getLogger(ServerReceiverHandler.class.getName());

    private ObjectOutputStream objectOutputStream;

    public ServerReceiverHandler() {
        objectOutputStream = null;
    }

    public ServerReceiverHandler(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }

    @Override
    public void visitClientServerRequest(ChosenBonusTile bonusTile) {
        ActiveGames activeGames = ActiveGames.getInstance();
        Optional<Game> optionalGame = activeGames.getGameById(bonusTile.getGameId());
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            game.addBonusTileToPlayer(bonusTile.getPlayerName(),
                    bonusTile.getBonusTileIdentifier());
        }
    }

    @Override
    public void visitClientServerRequest(ChosenLeader chosenLeader) {
        ActiveGames activeGames = ActiveGames.getInstance();
        Optional<Game> optionalGame = activeGames.getGameById(chosenLeader.getGameId());
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            game.addChosenLeaderToPlayer(chosenLeader.getPlayerName(),
                    chosenLeader.getLeaderName());
        }
    }

    @Override
    public void visitClientServerRequest(LeaderAction leaderAction) {
        System.out.println("LeaderAction");
    }

    @Override
    public void visitClientServerRequest(PawnPlacement pawnPlacement) {
        System.out.println("PawnPlacement");
    }

    @Override
    public void visitClientServerRequest(GameStartChoice gameStartChoice) {
        NewGameInformation newGameInformation = new NewGameInformation(
                new ConnectedClient(gameStartChoice.getPlayerName(), new ConnectionStream(objectOutputStream)),
                gameStartChoice.getGameStartType());
        setChanged();
        notifyObservers(newGameInformation);
    }

    @Override
    public void visitClientServerRequest(GameStartChoiceRMI gameStartChoiceRMI) {
        NewGameInformation newGameInformation = new NewGameInformation(
                new ConnectedClient(gameStartChoiceRMI.getPlayerName(),
                        new ConnectionStream(gameStartChoiceRMI.getRegistrable())),
                gameStartChoiceRMI.getGameStartType());
        setChanged();
        notifyObservers(newGameInformation);
    }

    @Override
    public void visitClientServerRequest(PlayerLogin playerLogin) {
        if (playerLogin.isNewPlayer()) {
            registerNewPlayer(playerLogin);
        } else {
            authenticatePlayer(playerLogin);
        }
    }

    private void registerNewPlayer(PlayerLogin playerLogin) {
        LOGGER.info("Player registration socket");
        if (registerNewPlayerQuery(playerLogin.getPlayerName(), playerLogin.getPassword())) {
            LOGGER.info("Socket registration and login successful");
            sendSocketLoginResponse(true, playerLogin.getPlayerName());
        } else {
            LOGGER.info("Cannot register client");
            sendSocketLoginResponse(false, "");
        }
    }

    private void authenticatePlayer(PlayerLogin playerLogin) {
        LOGGER.info("Player login socket");
        if (authenticateQuery(playerLogin.getPlayerName(), playerLogin.getPassword())) {
            LOGGER.info("Socket login successful");
            sendSocketLoginResponse(true, playerLogin.getPlayerName());
        } else {
            LOGGER.info("Login unsuccessful");
            sendSocketLoginResponse(false, "");
        }
    }

    private void sendSocketLoginResponse(boolean successful, String playerName) {
        try {
            objectOutputStream.writeObject(new LoginResponse(successful, playerName));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: cannot write on socket", e);
        }
    }

    @Override
    public void visitClientServerRequest(PlayerLoginRMI playerLoginRMI) {
        if (playerLoginRMI.isNewPlayer()) {
            registerNewPlayerRMI(playerLoginRMI);
        } else {
            authenticatePlayerRMI(playerLoginRMI);
        }
    }

    private void registerNewPlayerRMI(PlayerLoginRMI playerLoginRMI) {
        LOGGER.info("Player registration RMI");
        if (registerNewPlayerQuery(playerLoginRMI.getPlayerName(), playerLoginRMI.getPassword())) {
            LOGGER.info("RMI registration and login successful");
            sendRMILoginResponse(playerLoginRMI.getRegistrable(), true, playerLoginRMI.getPlayerName());
        } else {
            LOGGER.info("Cannot register client");
            sendRMILoginResponse(playerLoginRMI.getRegistrable(), false, "");
        }
    }

    private void authenticatePlayerRMI(PlayerLoginRMI playerLoginRMI) {
        LOGGER.info("Player login RMI");
        if (authenticateQuery(playerLoginRMI.getPlayerName(), playerLoginRMI.getPassword())) {
            LOGGER.info("RMI login successful");
            sendRMILoginResponse(playerLoginRMI.getRegistrable(), true, playerLoginRMI.getPlayerName());
        } else {
            LOGGER.info("Login unsuccessful");
            sendRMILoginResponse(playerLoginRMI.getRegistrable(), false, "");
        }
    }

    private void sendRMILoginResponse(Registrable registrable, boolean successful, String playerName) {
        try {
            registrable.update(new LoginResponse(successful, playerName));
        } catch (RemoteException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: cannot write on registrable", e);
        }
    }

    private boolean authenticateQuery(String playerName, String psw) {
        QueryHandler queryHandler = new QueryHandler();
        return queryHandler.authenticate(playerName, psw);
    }

    private boolean registerNewPlayerQuery(String playerName, String psw) {
        QueryHandler queryHandler = new QueryHandler();
        return queryHandler.addPlayer(playerName, psw);
    }

    @Override
    public void visitClientServerRequest(Ready ready) {
        ActiveGames activeGames = ActiveGames.getInstance();
        Optional<Game> optionalGame = activeGames.getGameById(ready.getGameId());
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            switch (ready.getReadyForWhat()) {
                case "leadersChoice":
                    game.checkAndStartLeaderChoice();
                    break;
                case "tileChoice":
                    game.bonusTilesSetup();
                    break;
                case "game":
                    game.initFirstPeriod();
            }
        }
    }
}
