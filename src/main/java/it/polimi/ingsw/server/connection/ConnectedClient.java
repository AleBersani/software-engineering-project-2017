package it.polimi.ingsw.server.connection;

public class ConnectedClient {
    private String playerName;
    private ConnectionStream connectionStream;

    public ConnectedClient(String playerName, ConnectionStream connectionStream) {
        this.playerName = playerName;
        this.connectionStream = connectionStream;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public ConnectionStream getConnectionStream() {
        return connectionStream;
    }

    public void setConnectionStream(ConnectionStream connectionStream) {
        this.connectionStream = connectionStream;
    }
}
