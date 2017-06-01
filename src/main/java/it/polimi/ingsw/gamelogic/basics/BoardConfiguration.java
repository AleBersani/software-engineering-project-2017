package it.polimi.ingsw.gamelogic.basics;

/**
 * This class sets up the board with the basic settings (Towers, Dices, Points)
 */
public final class BoardConfiguration {
    private static int numberOfTowers;
    private static int numberOfSlotsForTowers;
    private static int numberOfDices;
    private static int maxMilitaryPoints;
    private static int maxFaithPoints;

    public static int getNumberOfTowers() {
        return numberOfTowers;
    }

    public static void setNumberOfTowers(int numberOfTowers) {
        BoardConfiguration.numberOfTowers = numberOfTowers;
    }

    public static int getNumberOfSlotsForTowers() {
        return numberOfSlotsForTowers;
    }

    public static void setNumberOfSlotsForTowers(int numberOfSlotsForTowers) {
        BoardConfiguration.numberOfSlotsForTowers = numberOfSlotsForTowers;
    }

    public static int getNumberOfDices() {
        return numberOfDices;
    }

    public static void setNumberOfDices(int numberOfDices) {
        BoardConfiguration.numberOfDices = numberOfDices;
    }

    public static int getMaxMilitaryPoints() {
        return maxMilitaryPoints;
    }

    public static void setMaxMilitaryPoints(int maxMilitaryPoints) {
        BoardConfiguration.maxMilitaryPoints = maxMilitaryPoints;
    }

    public static int getMaxFaithPoints() {
        return maxFaithPoints;
    }

    public static void setMaxFaithPoints(int maxFaithPoints) {
        BoardConfiguration.maxFaithPoints = maxFaithPoints;
    }
}
