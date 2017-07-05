package it.polimi.ingsw.client;

import it.polimi.ingsw.shared.model.GeneralColor;

public class ClientInformation {
    private static String playerName;
    private static GeneralColor playerColor;
    private static int currentGameId;

    public static String getPlayerName() {
        return playerName;
    }

    public static void setPlayerName(String playerName) {
        ClientInformation.playerName = playerName;
    }

    public static GeneralColor getPlayerColor() {
        return playerColor;
    }

    public static void setPlayerColor(GeneralColor playerColor) {
        ClientInformation.playerColor = playerColor;
    }

    public static int getCurrentGameId() {
        return currentGameId;
    }

    public static void setCurrentGameId(int currentGameId) {
        ClientInformation.currentGameId = currentGameId;
    }
}
