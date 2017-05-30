package it.polimi.ingsw.gamelogic.basics;

/**
 * TODO: JavaDoc
 */
public final class GameConfiguration {
    private static int maxNumberOfPlayer;
    private static int startingGameTimeout;
    private static int moveTimeout;
    private static int numberOfPeriods;

    public static int getMaxNumberOfPlayer() {
        return maxNumberOfPlayer;
    }

    public static void setMaxNumberOfPlayer(int maxNumberOfPlayer) {
        GameConfiguration.maxNumberOfPlayer = maxNumberOfPlayer;
    }

    public static int getStartingGameTimeout() {
        return startingGameTimeout;
    }

    public static void setStartingGameTimeout(int startingGameTimeout) {
        GameConfiguration.startingGameTimeout = startingGameTimeout;
    }

    public static int getMoveTimeout() {
        return moveTimeout;
    }

    public static void setMoveTimeout(int moveTimeout) {
        GameConfiguration.moveTimeout = moveTimeout;
    }

    public static int getNumberOfPeriods() {
        return numberOfPeriods;
    }

    public static void setNumberOfPeriods(int numberOfPeriods) {
        GameConfiguration.numberOfPeriods = numberOfPeriods;
    }
}
