package it.polimi.ingsw.server.gamelogic.player;

import it.polimi.ingsw.server.gamelogic.actionsdescription.ActionDescription;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.cards.additionalinfo.CardFlashAction;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderCard;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers.RequirementsModifier;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.modifiers.RewardsModifier;
import it.polimi.ingsw.shared.model.PawnColor;

import java.util.*;
import java.util.function.IntSupplier;

/**
 * Class that describes the player
 */
public class Player {
    private PlayerDetails playerDetails;
    private PlayerBoard playerBoard;

    private List<LeaderCard> leaderCards;
    private List<ActionDescription> possibleActionsForTurn;
    private PlayerCardsEffects playerCardsEffects;

    public Player(PlayerDetails playerDetails, PlayerBoard playerBoard) {
        this.playerDetails = playerDetails;
        this.playerBoard = playerBoard;
        leaderCards = new ArrayList<>();
        possibleActionsForTurn = new ArrayList<>();
        playerCardsEffects = new PlayerCardsEffects();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Player player = (Player) o;
        return Objects.equals(getPlayerDetails(), player.getPlayerDetails()) &&
                Objects.equals(getPlayerBoard(), player.getPlayerBoard()) &&
                Objects.equals(getLeaderCards(), player.getLeaderCards()) &&
                Objects.equals(getPossibleActionsForTurn(), player.getPossibleActionsForTurn()) &&
                Objects.equals(getPlayerCardsEffects(), player.getPlayerCardsEffects());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerDetails(), getPlayerBoard(), getLeaderCards(), getPossibleActionsForTurn(), getPlayerCardsEffects());
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
        counters.put("GOODS", this::sumOfAllResources);
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

    public void addRequirementsModifier(RequirementsModifier requirementsModifier) {
        playerCardsEffects.addRequirementsModifier(requirementsModifier);
    }

    public void addRewardsModifier(RewardsModifier rewardsModifier) {
        playerCardsEffects.addRewardsModifier(rewardsModifier);
    }

    public Optional<CardFlashAction> getCardFlashActions() {
        return playerCardsEffects.getCardFlashAction();
    }

    public int getPlayerOrderWeight() {
        return playerCardsEffects.getPlayerOrderWeight();
    }

    public List<RequirementsModifier> getRequirementsModifiers() {
        return playerCardsEffects.getRequirementsModifiers();
    }

    public List<RewardsModifier> getRewardsModifiers() {
        return playerCardsEffects.getRewardsModifiers();
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

    public List<ActionDescription> getPossibleActionsForTurn() {
        return possibleActionsForTurn;
    }

    public void setPossibleActionsForTurn(List<ActionDescription> possibleActionsForTurn) {
        this.possibleActionsForTurn = possibleActionsForTurn;
    }

    public PlayerCardsEffects getPlayerCardsEffects() {
        return playerCardsEffects;
    }

    public void setPlayerCardsEffects(PlayerCardsEffects playerCardsEffects) {
        this.playerCardsEffects = playerCardsEffects;
    }
}
