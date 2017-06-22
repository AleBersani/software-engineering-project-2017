package it.polimi.ingsw.gamelogic.board;

import it.polimi.ingsw.shared.model.BoardIdentifier;

import java.util.Objects;

/**
 * Class that describes the basic information of a Space on the Board
 */
public class Space {
    private BoardIdentifier boardIdentifier;
    private int requestedValue;

    private PlayerPawn playerPawn;
    private boolean alreadyTaken;

    public Space(BoardIdentifier boardIdentifier, int requestedValue) {
        this.boardIdentifier = boardIdentifier;
        this.requestedValue = requestedValue;
        playerPawn = new PlayerPawn();
        alreadyTaken = false;
    }

    public Space(BoardIdentifier boardIdentifier, PlayerPawn playerPawn, int requestedValue) {
        this.boardIdentifier = boardIdentifier;
        this.playerPawn = playerPawn;
        this.requestedValue = requestedValue;
        alreadyTaken = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Space space = (Space) o;
        return getRequestedValue() == space.getRequestedValue() &&
                isAlreadyTaken() == space.isAlreadyTaken() &&
                getBoardIdentifier() == space.getBoardIdentifier() &&
                Objects.equals(getPlayerPawn(), space.getPlayerPawn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBoardIdentifier(), getRequestedValue(), getPlayerPawn(), isAlreadyTaken());
    }

    public BoardIdentifier getBoardIdentifier() {
        return boardIdentifier;
    }

    public void setBoardIdentifier(BoardIdentifier boardIdentifier) {
        this.boardIdentifier = boardIdentifier;
    }

    public int getRequestedValue() {
        return requestedValue;
    }

    public void setRequestedValue(int requestedValue) {
        this.requestedValue = requestedValue;
    }

    public PlayerPawn getPlayerPawn() {
        return playerPawn;
    }

    public void setPlayerPawn(PlayerPawn playerPawn) {
        this.playerPawn = playerPawn;
    }

    public boolean isAlreadyTaken() {
        return alreadyTaken;
    }

    public void setAlreadyTaken(boolean alreadyTaken) {
        this.alreadyTaken = alreadyTaken;
    }
}
