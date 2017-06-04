package it.polimi.ingsw.gamelogic.basics;

import java.util.List;

/**
 * Abstraction of the different Goods for which a Council's Privilege can be exchanged.
 */
public class CouncilePrivilege {
    private List<Goods> possibleChoices;

    public CouncilePrivilege(List<Goods> possibleChoices) {
        this.possibleChoices = possibleChoices;
    }

    public List<Goods> getPossibleChoices() {
        return possibleChoices;
    }

    public void setPossibleChoices(List<Goods> possibleChoices) {
        this.possibleChoices = possibleChoices;
    }
}