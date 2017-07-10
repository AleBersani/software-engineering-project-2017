package it.polimi.ingsw.client;

import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.GeneralColor;
import it.polimi.ingsw.shared.model.actionsdescription.BoardAction;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientInformation {
    private static String playerName;
    private static GeneralColor playerColor;
    private static int currentGameId;
    private static BoardAction lastBoardAction;
    private static AtomicInteger numberOfCouncilPrivilegeToChoose;
    private static List<ActionType> actionTypesForConsumableAction;

    public static void initClass() {
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

    public static AtomicInteger getNumberOfCouncilPrivilegeToChoose() {
        return numberOfCouncilPrivilegeToChoose;
    }

    public static void setNumberOfCouncilPrivilegeToChoose(AtomicInteger numberOfCouncilPrivilegeToChoose) {
        ClientInformation.numberOfCouncilPrivilegeToChoose = numberOfCouncilPrivilegeToChoose;
    }

    public static List<ActionType> getActionTypesForConsumableAction() {
        return actionTypesForConsumableAction;
    }

    public static void setActionTypesForConsumableAction(List<ActionType> actionTypesForConsumableAction) {
        ClientInformation.actionTypesForConsumableAction = actionTypesForConsumableAction;
    }
}
