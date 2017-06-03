package it.polimi.ingsw.gamelogic.player;

import it.polimi.ingsw.gamelogic.actions.description.ActionDescription;
import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.enums.PawnColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class that describes the player
 */
public class Player {
    private PlayerDetails playerDetails;
    private PlayerBoard playerBoard;
    private ActionDescription actualAction;
    private List<ActionDescription> possibleActionsForTurn;

    public Player(PlayerDetails playerDetails, PlayerBoard playerBoard) {
        this.playerDetails = playerDetails;
        this.playerBoard = playerBoard;
        actualAction = null;
        possibleActionsForTurn = new ArrayList<>();
    }

    public void setPlayerGoods(Goods goods) {
        playerBoard.setGoods(goods);
    }

    public Goods getPlayerGoods() {
        return playerBoard.getGoods();
    }

    public int getPawnValueGivenColor(PawnColor pawnColor) {
        return playerBoard.getPawnValueGivenColor(pawnColor);
    }

    public Optional<Pawn> getPawnGivenColor(PawnColor pawnColor) {
        return playerBoard.getPawnGivenColor(pawnColor);
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

    /**
     * Optional getter for actualAction
     * @return Optional.empty() if the attribute is null
     */
    public Optional<ActionDescription> getActualAction() {
        if (actualAction == null)
            return Optional.empty();
        return Optional.of(actualAction);
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
