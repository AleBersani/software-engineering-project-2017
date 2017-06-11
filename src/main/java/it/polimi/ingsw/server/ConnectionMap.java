package it.polimi.ingsw.server;

import it.polimi.ingsw.gamelogic.player.PlayerDetails;

import java.net.Socket;
import java.util.Map;

public final class ConnectionMap {
    private static Map<Socket, PlayerDetails> socketPlayerDetailsMap;

    private ConnectionMap() {
        throw new IllegalAccessError("Utility class");
    }
}
