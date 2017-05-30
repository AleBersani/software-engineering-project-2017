package it.polimi.ingsw.gamelogic.cards.leader;

import it.polimi.ingsw.gamelogic.enums.LeaderCategory;

/**
 * TODO: JavaDoc
 */
public class LeaderCard {
    private LeaderInformation leaderInformation;
    private LeaderCost leaderCost;
    private boolean placedOnBoard;
    private boolean playable;

    public LeaderCard(LeaderInformation leaderInformation, LeaderCost leaderCost) {
        this.leaderInformation = leaderInformation;
        this.leaderCost = leaderCost;
        placedOnBoard = false;
        playable = true;
    }

    /**
     * TODO: JavaDoc
     * @return
     */
    public LeaderCategory getLeaderCategory() {
        return leaderInformation.getLeaderCategory();
    }

    public LeaderInformation getLeaderInformation() {
        return leaderInformation;
    }

    public void setLeaderInformation(LeaderInformation leaderInformation) {
        this.leaderInformation = leaderInformation;
    }

    public LeaderCost getLeaderCost() {
        return leaderCost;
    }

    public void setLeaderCost(LeaderCost leaderCost) {
        this.leaderCost = leaderCost;
    }

    public boolean isPlacedOnBoard() {
        return placedOnBoard;
    }

    public void setPlacedOnBoard(boolean placedOnBoard) {
        this.placedOnBoard = placedOnBoard;
    }

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }
}
