package it.polimi.ingsw.server.gamelogic.player;

import it.polimi.ingsw.server.gamelogic.actions.description.ActionDescription;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderCard;
import it.polimi.ingsw.shared.model.PawnColor;
import it.polimi.ingsw.shared.model.PlayerDetails;

import java.util.*;
import java.util.function.IntSupplier;

/**
 * Class that describes the player
 */
public class Player {
    private PlayerDetails playerDetails;
    private PlayerBoard playerBoard;

    private List<LeaderCard> leaderCards;
    private ActionDescription actualAction;
    private List<ActionDescription> possibleActionsForTurn;

    public Player(PlayerDetails playerDetails, PlayerBoard playerBoard) {
        this.playerDetails = playerDetails;
        this.playerBoard = playerBoard;
        leaderCards = new ArrayList<>();
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

    /**
     * Checks if the Player has a certain Leader Card
     * @param leaderName Leader Card that needs to be checked
     * @return true if the Player has the Leader Card
     */
    public boolean hasLeader(String leaderName) {
        return leaderCards.stream()
                .anyMatch(leaderCard -> leaderCard.getLeaderName().equals(leaderName));
    }

    public Optional<Pawn> getPawnGivenColor(PawnColor pawnColor) {
        return playerBoard.getPawnGivenColor(pawnColor);
    }

    /**
     * Counts the item linked to the identifier
     * @param identifier type of object
     * @return the count
     */
    public int countGivenIdentifier(String identifier) {
        return getCounters().get(identifier).getAsInt();
    }

    private Map<String, IntSupplier> getCounters() {
        Map<String, IntSupplier> counters = new HashMap<>();
        counters.put("MILITARY", () -> getPlayerGoods().getPoints().getMilitary());
        counters.put("GREEN", () -> playerBoard.getNumberOfTerritories());
        counters.put("YELLOW", () -> playerBoard.getNumberOfBuildings());
        counters.put("BLUE", () -> playerBoard.getNumberOfCharacters());
        counters.put("PURPLE", () -> playerBoard.getNumberOfVentures());
        counters.put("GOODS", () -> sumOfAllResources());
        return counters;
    }

    private int sumOfAllResources() {
        Resources resources = getPlayerGoods().getResources();
        return resources.getWoods() +
                resources.getStones() +
                resources.getCoins() +
                resources.getServants();
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

    public List<LeaderCard> getLeaderCards() {
        return leaderCards;
    }

    public void setLeaderCards(List<LeaderCard> leaderCards) {
        this.leaderCards = leaderCards;
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
