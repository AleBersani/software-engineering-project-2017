package it.polimi.ingsw.gamelogic.basics;

import java.util.List;

/**
 * Abstraction of the different Goods for which a Councile Privilege can be exchanged.
 */
public class CouncilePrivilege {
    private List<Goods> possibleChoises;

    public CouncilePrivilege(List<Goods> possibleChoises) {
        this.possibleChoises = possibleChoises;
    }

    public List<Goods> getPossibleChoises() {
        return possibleChoises;
    }

    public void setPossibleChoises(List<Goods> possibleChoises) {
        this.possibleChoises = possibleChoises;
    }
}
