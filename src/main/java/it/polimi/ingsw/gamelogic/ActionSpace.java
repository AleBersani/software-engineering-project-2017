
public class ActionSpace {
    protected boolean alreadyTaken;
    protected int requestedValue;
    protected int requiredPlayersNumber;

    public ActionSpace(int requestedValue, int requiredPlayersNumber) {
        this.requestedValue = requestedValue;
        this.requiredPlayersNumber = requiredPlayersNumber;
        alreadyTaken = false;
    }

    public boolean isAlreadyTaken() {
        return this.alreadyTaken;
    }

    public int getRequestedValue() {
        return this.requestedValue;
    }

    public int getRequiredPlayersNumber() {
        return this.requiredPlayersNumber;
    }

    public void setAlreadyTaken(boolean alreadyTaken) {
        this.alreadyTaken = alreadyTaken;
    }

    public void setRequestedValue(int requestedValue) {
        this.requestedValue = requestedValue;
    }

    public void setRequiredPlayersNumber(int requiredPlayersNumber) {
        this.requiredPlayersNumber = requiredPlayersNumber;
    }
}
