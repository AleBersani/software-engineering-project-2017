package it.polimi.ingsw.client;

import it.polimi.ingsw.shared.model.GeneralColor;
import it.polimi.ingsw.shared.model.actionsdescription.BoardAction;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientInformation {
    private static String playerName;
    private static GeneralColor playerColor;
    private static int currentGameId;
    private static BoardAction lastBoardAction;
    private static AtomicBoolean canPlay;
    private static AtomicInteger numberOfCouncilPrivilegeToChoose;

    public static void initClass() {
        canPlay = new AtomicBoolean(false);
        numberOfCouncilPrivilegeToChoose = new AtomicInteger(0);
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

    public static BoardAction getLastBoardAction() {
        return lastBoardAction;
    }

    public static void setLastBoardAction(BoardAction lastBoardAction) {
        ClientInformation.lastBoardAction = lastBoardAction;
    }

    public static AtomicBoolean getCanPlay() {
        return canPlay;
    }

    public static void setCanPlay(AtomicBoolean canPlay) {
        ClientInformation.canPlay = canPlay;
    }


    public static AtomicInteger getNumberOfCouncilPrivilegeToChoose() {
        return numberOfCouncilPrivilegeToChoose;
    }

    public static void setNumberOfCouncilPrivilegeToChoose(AtomicInteger numberOfCouncilPrivilegeToChoose) {
        ClientInformation.numberOfCouncilPrivilegeToChoose = numberOfCouncilPrivilegeToChoose;
    }
}
