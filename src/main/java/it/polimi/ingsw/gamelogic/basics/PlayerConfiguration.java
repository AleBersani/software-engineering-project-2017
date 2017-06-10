package it.polimi.ingsw.gamelogic.basics;

import java.util.List;
import java.util.Map;

/**
 * Class that sets up the player with the basic settings to start the game
 */
public final class PlayerConfiguration {
    private static int numberOfLeaders;
    private static int maxNumberOfDevCardsForCategory;
    private static List<Goods> startingGoods;
    private static int numberOfPawns;
    private static Map<Integer, Points> requiredPointsForTerritories;

    private PlayerConfiguration() {
        throw new IllegalAccessError("Utility class");
    }

    public static int getNumberOfLeaders() {
        return numberOfLeaders;
    }

    public static void setNumberOfLeaders(int numberOfLeaders) {
        PlayerConfiguration.numberOfLeaders = numberOfLeaders;
    }

    public static int getMaxNumberOfDevCardsForCategory() {
        return maxNumberOfDevCardsForCategory;
    }

    public static void setMaxNumberOfDevCardsForCategory(int maxNumberOfDevCardsForCategory) {
        PlayerConfiguration.maxNumberOfDevCardsForCategory = maxNumberOfDevCardsForCategory;
    }

    public static List<Goods> getStartingGoods() {
        return startingGoods;
    }

    public static void setStartingGoods(List<Goods> startingGoods) {
        PlayerConfiguration.startingGoods = startingGoods;
    }

    public static int getNumberOfPawns() {
        return numberOfPawns;
    }

    public static void setNumberOfPawns(int numberOfPawns) {
        PlayerConfiguration.numberOfPawns = numberOfPawns;
    }

    public static Map<Integer, Points> getRequiredPointsForTerritories() {
        return requiredPointsForTerritories;
    }

    public static void setRequiredPointsForTerritories(Map<Integer, Points> requiredPointsForTerritories) {
        PlayerConfiguration.requiredPointsForTerritories = requiredPointsForTerritories;
    }
}
