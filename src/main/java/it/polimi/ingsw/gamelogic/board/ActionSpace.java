package it.polimi.ingsw.gamelogic.board;

public class ActionSpace {
    protected int requestedValue;
    protected int requiredPlayersNumber;

    protected boolean alreadyTaken;

    public ActionSpace(int requestedValue, int requiredPlayersNumber) {
        this.requestedValue = requestedValue;
        this.requiredPlayersNumber = requiredPlayersNumber;
        alreadyTaken = false;
    }

    public int getRequestedValue() {
        return requestedValue;
    }

    public void setRequestedValue(int requestedValue) {
        this.requestedValue = requestedValue;
    }

    public int getRequiredPlayersNumber() {
        return requiredPlayersNumber;
    }

    public void setRequiredPlayersNumber(int requiredPlayersNumber) {
        this.requiredPlayersNumber = requiredPlayersNumber;
    }

    public boolean isAlreadyTaken() {
        return alreadyTaken;
    }

    public void setAlreadyTaken(boolean alreadyTaken) {
        this.alreadyTaken = alreadyTaken;
    }
}
