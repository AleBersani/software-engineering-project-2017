package it.polimi.ingsw.server.gamelogic.board;

/**
 * Class that describes the single space where the player can put one of his/her pawn in order to perform
 * an action
 */
public class ActionSpace {
    private Space space;
    private int requiredPlayersNumber;

    public ActionSpace(Space space, int requiredPlayersNumber) {
        this.space = space;
        this.requiredPlayersNumber = requiredPlayersNumber;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public int getRequiredPlayersNumber() {
        return requiredPlayersNumber;
    }

    public void setRequiredPlayersNumber(int requiredPlayersNumber) {
        this.requiredPlayersNumber = requiredPlayersNumber;
    }
}
