package it.polimi.ingsw.gamelogic.cards.leader;

import it.polimi.ingsw.gamelogic.enums.LeaderCategory;

/**
 * TODO: JavaDoc
 */
public class LeaderInformation {
    private String name;
    private String effectDescription;
    private LeaderCategory leaderCategory;

    public LeaderInformation(String name, String effectDescription, LeaderCategory leaderCategory) {
        this.name = name;
        this.effectDescription = effectDescription;
        this.leaderCategory = leaderCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEffectDescription() {
        return effectDescription;
    }

    public void setEffectDescription(String effectDescription) {
        this.effectDescription = effectDescription;
    }

    public LeaderCategory getLeaderCategory() {
        return leaderCategory;
    }

    public void setLeaderCategory(LeaderCategory leaderCategory) {
        this.leaderCategory = leaderCategory;
    }
}
