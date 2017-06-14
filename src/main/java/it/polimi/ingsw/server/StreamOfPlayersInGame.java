package it.polimi.ingsw.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StreamOfPlayersInGame<Value> {
    private final int GAME_ID;
    private final Map<String, Value> PLAYERS;

    public StreamOfPlayersInGame(int GAME_ID) {
        this.GAME_ID = GAME_ID;
        PLAYERS = new HashMap<>();
    }

    public void put(String playerName, Value value) {
        if (playerName != null ) {
            PLAYERS.put(playerName, value);
        }
    }

    public Optional<Value> get(String playerName) {
        if (PLAYERS.containsKey(playerName)) {
            return Optional.of(PLAYERS.get(playerName));
        }
        return Optional.empty();
    }

    public boolean has(String playerName) {
        return PLAYERS.containsKey(playerName);
    }

    public int getGAME_ID() {
        return GAME_ID;
    }

    public Map<String, Value> getPLAYERS() {
        return PLAYERS;
    }
}
