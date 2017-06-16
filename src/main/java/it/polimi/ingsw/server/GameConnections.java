package it.polimi.ingsw.server;

import it.polimi.ingsw.gamelogic.basics.GameConfiguration;
import it.polimi.ingsw.shared.Registrable;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class GameConnections {
    private final int ID_GAME;
    private final int MAX_PLAYERS;
    private final Map<String, ObjectOutputStream> PLAYER_OUTPUT_STREAM_MAP;
    private final Map<String, Registrable> PLAYER_RMI_MAP;

    private boolean startedGame;
    private int numberOfPlayer;

    public GameConnections(int ID_GAME) {
        this.ID_GAME = ID_GAME;
        //TODO: MAX_PLAYERS = GameConfiguration.getMaxNumberOfPlayer();
        MAX_PLAYERS = 4;
        PLAYER_OUTPUT_STREAM_MAP = new HashMap<>();
        PLAYER_RMI_MAP = new HashMap<>();
        startedGame = false;
        numberOfPlayer = 0;
    }

    public void addPlayer(String playerName, ObjectOutputStream objectOutputStream) {
        if (numberOfPlayer < MAX_PLAYERS) {
            PLAYER_OUTPUT_STREAM_MAP.put(playerName, objectOutputStream);
            numberOfPlayer++;
        }
    }

    public void addPlayer(String playerName, Registrable registrable) {
        if (numberOfPlayer < MAX_PLAYERS) {
            PLAYER_RMI_MAP.put(playerName, registrable);
            numberOfPlayer++;
        }
    }

    public ConnectionStream getConnectionStream(String playerName) {
        ConnectionStream connectionStream = new ConnectionStream();
        connectionStream.setObjectOutputStream(getObjectOutputStream(playerName));
        connectionStream.setRegistrable(getRMIStream(playerName));
        return connectionStream;
    }

    private ObjectOutputStream getObjectOutputStream(String playerName) {
        if (PLAYER_OUTPUT_STREAM_MAP.containsKey(playerName)) {
            return PLAYER_OUTPUT_STREAM_MAP.get(playerName);
        } else {
            return null;
        }
    }

    private Registrable getRMIStream(String playerName) {
        if (PLAYER_RMI_MAP.containsKey(playerName)) {
            return PLAYER_RMI_MAP.get(playerName);
        } else {
            return null;
        }
    }

    public boolean hasPlayer(String playerName) {
        return PLAYER_OUTPUT_STREAM_MAP.containsKey(playerName) ||
                PLAYER_RMI_MAP.containsKey(playerName);
    }

    public int getID_GAME() {
        return ID_GAME;
    }

    public Map<String, ObjectOutputStream> getPLAYER_OUTPUT_STREAM_MAP() {
        return PLAYER_OUTPUT_STREAM_MAP;
    }

    public Map<String, Registrable> getPLAYER_RMI_MAP() {
        return PLAYER_RMI_MAP;
    }

    public boolean isStartedGame() {
        return startedGame;
    }

    public void setStartedGame(boolean startedGame) {
        this.startedGame = startedGame;
    }
}
