package it.polimi.ingsw.gamelogic.player;

import it.polimi.ingsw.gamelogic.actions.description.ActionDescription;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: JavaDoc
 */
public class Player {
    private PlayerDetails playerDetails;
    private PlayerBoard playerBoard;
    private ActionDescription actualAction;
    private List<ActionDescription> possibleActionsForTurn;

    public Player(PlayerDetails playerDetails, PlayerBoard playerBoard) {
        this.playerDetails = playerDetails;
        this.playerBoard = playerBoard;
        // TODO: initialization of actualAction
        possibleActionsForTurn = new ArrayList<>();
    }

    /*
    TODO: aux methods
     */

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
