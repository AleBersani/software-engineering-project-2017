package it.polimi.ingsw.client.model;

import it.polimi.ingsw.shared.model.LeaderCategory;

import java.io.Serializable;

public class LeaderLight implements Serializable {
    private Card card;
    private LeaderCategory leaderCategory;
    private boolean isActive;

    public LeaderLight(Card card, LeaderCategory leaderCategory, boolean isActive) {
        this.card = card;
        this.leaderCategory = leaderCategory;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "LeaderLight{" +
                "card=" + card +
                ", leaderCategory=" + leaderCategory +
                ", isActive=" + isActive +
                '}';
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public LeaderCategory getLeaderCategory() {
        return leaderCategory;
    }

    public void setLeaderCategory(LeaderCategory leaderCategory) {
        this.leaderCategory = leaderCategory;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
