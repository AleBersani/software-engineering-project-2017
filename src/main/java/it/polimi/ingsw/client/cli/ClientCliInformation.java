package it.polimi.ingsw.client.cli;

import it.polimi.ingsw.client.model.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientCliInformation {
    private static AtomicBoolean gameStarted;
    private static AtomicBoolean canPlay;
    private static AtomicBoolean loginSuccessful;
    private static AtomicBoolean leaderChoiceEnded;
    private static AtomicBoolean leadersAvailable;
    private static List<Card> currentLeadersToChoice;
    private static AtomicBoolean bonusTilesChoiceEnded;
    private static AtomicBoolean bonusTilesAvailable;
    private static List<String> currentBonusTilesToChoice;
    private static AtomicBoolean gameInterrupted;

    public static void init() {
        gameStarted = new AtomicBoolean(false);
        canPlay = new AtomicBoolean(false);
        loginSuccessful = new AtomicBoolean(false);
        leaderChoiceEnded = new AtomicBoolean(false);
        leadersAvailable = new AtomicBoolean(false);
        currentLeadersToChoice = new ArrayList<>();
        bonusTilesChoiceEnded = new AtomicBoolean(false);
        bonusTilesAvailable = new AtomicBoolean(false);
        currentBonusTilesToChoice = new ArrayList<>();
        gameInterrupted = new AtomicBoolean(false);
    }

    public static AtomicBoolean getGameStarted() {
        return gameStarted;
    }

    public static void setGameStarted(AtomicBoolean gameStarted) {
        ClientCliInformation.gameStarted = gameStarted;
    }

    public static AtomicBoolean getCanPlay() {
        return canPlay;
    }

    public static void setCanPlay(AtomicBoolean canPlay) {
        ClientCliInformation.canPlay = canPlay;
    }

    public static AtomicBoolean getLoginSuccessful() {
        return loginSuccessful;
    }

    public static void setLoginSuccessful(AtomicBoolean loginSuccessful) {
        ClientCliInformation.loginSuccessful = loginSuccessful;
    }

    public static AtomicBoolean getLeaderChoiceEnded() {
        return leaderChoiceEnded;
    }

    public static void setLeaderChoiceEnded(AtomicBoolean leaderChoiceEnded) {
        ClientCliInformation.leaderChoiceEnded = leaderChoiceEnded;
    }

    public static AtomicBoolean getLeadersAvailable() {
        return leadersAvailable;
    }

    public static void setLeadersAvailable(AtomicBoolean leadersAvailable) {
        ClientCliInformation.leadersAvailable = leadersAvailable;
    }

    public static List<Card> getCurrentLeadersToChoice() {
        return currentLeadersToChoice;
    }

    public static void setCurrentLeadersToChoice(List<Card> currentLeadersToChoice) {
        ClientCliInformation.currentLeadersToChoice = currentLeadersToChoice;
    }

    public static AtomicBoolean getBonusTilesChoiceEnded() {
        return bonusTilesChoiceEnded;
    }

    public static void setBonusTilesChoiceEnded(AtomicBoolean bonusTilesChoiceEnded) {
        ClientCliInformation.bonusTilesChoiceEnded = bonusTilesChoiceEnded;
    }

    public static AtomicBoolean getBonusTilesAvailable() {
        return bonusTilesAvailable;
    }

    public static void setBonusTilesAvailable(AtomicBoolean bonusTilesAvailable) {
        ClientCliInformation.bonusTilesAvailable = bonusTilesAvailable;
    }

    public static List<String> getCurrentBonusTilesToChoice() {
        return currentBonusTilesToChoice;
    }

    public static void setCurrentBonusTilesToChoice(List<String> currentBonusTilesToChoice) {
        ClientCliInformation.currentBonusTilesToChoice = currentBonusTilesToChoice;
    }

    public static AtomicBoolean getGameInterrupted() {
        return gameInterrupted;
    }

    public static void setGameInterrupted(AtomicBoolean gameInterrupted) {
        ClientCliInformation.gameInterrupted = gameInterrupted;
    }
}
