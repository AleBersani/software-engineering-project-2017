package it.polimi.ingsw.client;

public class ClientInformation {
    private static String playerName;
    private static int currentGameId;

    public static String getPlayerName() {
        return playerName;
    }

    public static void setPlayerName(String playerName) {
        ClientInformation.playerName = playerName;
    }

    public static int getCurrentGameId() {
        return currentGameId;
    }

    public static void setCurrentGameId(int currentGameId) {
        ClientInformation.currentGameId = currentGameId;
    }
}
