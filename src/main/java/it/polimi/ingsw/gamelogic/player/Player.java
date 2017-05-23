package it.polimi.ingsw.gamelogic.player;

import it.polimi.ingsw.gamelogic.actions.ActionDescription;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
public class Player {
    private PlayerDetails playerDetails;
    private PlayerBoard playerBoard;

    private Deck deck;
    private ActionDescription actualAction;
    private List<ActionDescription> possibleActionsForTurn;

    public Player(PlayerDetails playerDetails, PlayerBoard playerBoard) {
        this.playerDetails = playerDetails;
        this.playerBoard = playerBoard;
        deck = new Deck();
        // TODO: initialization of actualAction
        possibleActionsForTurn = new ArrayList<>();
    }

    /**
     * Clean the list of possible action at the and of the turn (inside a round)
     */
    public void clearPossibleActionForTurn() {
        possibleActionsForTurn.clear();
    }

    public PlayerDetails getPlayerDetails() {
        return playerDetails;
    }

    public void setPlayerDetails(PlayerDetails playerDetails) {
        this.playerDetails = playerDetails;
    }

    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }

    public void setPlayerBoard(PlayerBoard playerBoard) {
        this.playerBoard = playerBoard;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public ActionDescription getActualAction() {
        return actualAction;
    }

    public void setActualAction(ActionDescription actualAction) {
        this.actualAction = actualAction;
    }

    public List<ActionDescription> getPossibleActionsForTurn() {
        return possibleActionsForTurn;
    }

    public void setPossibleActionsForTurn(List<ActionDescription> possibleActionsForTurn) {
        this.possibleActionsForTurn = possibleActionsForTurn;
    }
}
