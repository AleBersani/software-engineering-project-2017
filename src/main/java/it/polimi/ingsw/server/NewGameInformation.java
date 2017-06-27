package it.polimi.ingsw.server;

import it.polimi.ingsw.server.connection.ConnectedClient;
import it.polimi.ingsw.shared.support.GameStartType;

public class NewGameInformation {
    private ConnectedClient connectedClient;
    private GameStartType gameStartType;

    public NewGameInformation(ConnectedClient connectedClient, GameStartType gameStartType) {
        this.connectedClient = connectedClient;
        this.gameStartType = gameStartType;
    }

    public ConnectedClient getConnectedClient() {
        return connectedClient;
    }

    public void setConnectedClient(ConnectedClient connectedClient) {
        this.connectedClient = connectedClient;
    }

    public GameStartType getGameStartType() {
        return gameStartType;
    }

    public void setGameStartType(GameStartType gameStartType) {
        this.gameStartType = gameStartType;
    }
}
