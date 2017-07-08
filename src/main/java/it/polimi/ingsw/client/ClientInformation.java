package it.polimi.ingsw.client;

import it.polimi.ingsw.client.model.Card;
import it.polimi.ingsw.shared.model.GeneralColor;

import java.util.List;

public class ClientInformation {
    private static String playerName;
    private static GeneralColor playerColor;
    private static int currentGameId;
    private static boolean gameStarted;
    private static boolean canPlay;
    private static boolean loginSuccessful;
    private static boolean leaderChoiceEnded;
    private static List<Card> currentLeadersToChoice;
    private static boolean bonusTilesChoiceEnded;
    private static List<String> currentBonusTilesToChoice;

    public static void initClientInformation() {
        gameStarted = false;
        canPlay = false;
        loginSuccessful = false;
        leaderChoiceEnded = false;
        bonusTilesChoiceEnded = false;
    }

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

    public static boolean isGameStarted() {
        return gameStarted;
    }

    public static void setGameStarted(boolean gameStarted) {
        ClientInformation.gameStarted = gameStarted;
    }

    public static boolean isCanPlay() {
        return canPlay;
    }

    public static void setCanPlay(boolean canPlay) {
        ClientInformation.canPlay = canPlay;
    }

    public static boolean isLoginSuccessful() {
        return loginSuccessful;
    }

    public static void setLoginSuccessful(boolean loginSuccessful) {
        ClientInformation.loginSuccessful = loginSuccessful;
    }

    public static boolean isLeaderChoiceEnded() {
        return leaderChoiceEnded;
    }

    public static void setLeaderChoiceEnded(boolean leaderChoiceEnded) {
        ClientInformation.leaderChoiceEnded = leaderChoiceEnded;
    }

    public static List<Card> getCurrentLeadersToChoice() {
        return currentLeadersToChoice;
    }

    public static void setCurrentLeadersToChoice(List<Card> currentLeadersToChoice) {
        ClientInformation.currentLeadersToChoice = currentLeadersToChoice;
    }

    public static boolean isBonusTilesChoiceEnded() {
        return bonusTilesChoiceEnded;
    }

    public static void setBonusTilesChoiceEnded(boolean bonusTilesChoiceEnded) {
        ClientInformation.bonusTilesChoiceEnded = bonusTilesChoiceEnded;
    }

    public static List<String> getCurrentBonusTilesToChoice() {
        return currentBonusTilesToChoice;
    }

    public static void setCurrentBonusTilesToChoice(List<String> currentBonusTilesToChoice) {
        ClientInformation.currentBonusTilesToChoice = currentBonusTilesToChoice;
    }
}
