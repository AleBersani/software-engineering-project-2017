package it.polimi.ingsw.client.cli;

import it.polimi.ingsw.client.model.Card;

import java.util.List;

public class ClientCliInformation {
    private static boolean gameStarted;
    private static boolean canPlay;
    private static boolean loginSuccessful;
    private static boolean leaderChoiceEnded;
    private static List<Card> currentLeadersToChoice;
    private static boolean bonusTilesChoiceEnded;
    private static List<String> currentBonusTilesToChoice;
    private static boolean gameInterrupted;

    public static boolean isGameStarted() {
        return gameStarted;
    }

    public static void setGameStarted(boolean gameStarted) {
        ClientCliInformation.gameStarted = gameStarted;
    }

    public static boolean isCanPlay() {
        return canPlay;
    }

    public static void setCanPlay(boolean canPlay) {
        ClientCliInformation.canPlay = canPlay;
    }

    public static boolean isLoginSuccessful() {
        return loginSuccessful;
    }

    public static void setLoginSuccessful(boolean loginSuccessful) {
        ClientCliInformation.loginSuccessful = loginSuccessful;
    }

    public static boolean isLeaderChoiceEnded() {
        return leaderChoiceEnded;
    }

    public static void setLeaderChoiceEnded(boolean leaderChoiceEnded) {
        ClientCliInformation.leaderChoiceEnded = leaderChoiceEnded;
    }

    public static List<Card> getCurrentLeadersToChoice() {
        return currentLeadersToChoice;
    }

    public static void setCurrentLeadersToChoice(List<Card> currentLeadersToChoice) {
        ClientCliInformation.currentLeadersToChoice = currentLeadersToChoice;
    }

    public static boolean isBonusTilesChoiceEnded() {
        return bonusTilesChoiceEnded;
    }

    public static void setBonusTilesChoiceEnded(boolean bonusTilesChoiceEnded) {
        ClientCliInformation.bonusTilesChoiceEnded = bonusTilesChoiceEnded;
    }

    public static List<String> getCurrentBonusTilesToChoice() {
        return currentBonusTilesToChoice;
    }

    public static void setCurrentBonusTilesToChoice(List<String> currentBonusTilesToChoice) {
        ClientCliInformation.currentBonusTilesToChoice = currentBonusTilesToChoice;
    }

    public static boolean isGameInterrupted() {
        return gameInterrupted;
    }

    public static void setGameInterrupted(boolean gameInterrupted) {
        ClientCliInformation.gameInterrupted = gameInterrupted;
    }
}
