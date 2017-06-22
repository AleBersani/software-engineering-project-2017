package it.polimi.ingsw.server.gamelogic.basics;

import java.util.List;

/**
 * Abstraction of the different Goods for which a Council's Privilege can be exchanged.
 */
public final class CouncilPrivilege {
    private static List<Goods> possibleChoices;

    private CouncilPrivilege() {
        throw new IllegalAccessError("Utility class");
    }

    public static List<Goods> getPossibleChoices() {
        return possibleChoices;
    }

    public static void setPossibleChoices(List<Goods> possibleChoices) {
        CouncilPrivilege.possibleChoices = possibleChoices;
    }
}