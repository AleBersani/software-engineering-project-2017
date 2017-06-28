package it.polimi.ingsw.server.gamelogic.board;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ActionSpace that = (ActionSpace) o;
        return getRequiredPlayersNumber() == that.getRequiredPlayersNumber() &&
                Objects.equals(getSpace(), that.getSpace());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSpace(), getRequiredPlayersNumber());
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
