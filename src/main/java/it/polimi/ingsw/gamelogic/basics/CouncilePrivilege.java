package it.polimi.ingsw.gamelogic.basics;

import java.util.List;

/**
 * Abstraction of the different Goods for which a Council's Privilege can be exchanged.
 */
public final class CouncilePrivilege {
    private static List<Goods> possibleChoices;

    public static List<Goods> getPossibleChoices() {
        return possibleChoices;
    }

    public static void setPossibleChoices(List<Goods> possibleChoices) {
        CouncilePrivilege.possibleChoices = possibleChoices;
    }
}