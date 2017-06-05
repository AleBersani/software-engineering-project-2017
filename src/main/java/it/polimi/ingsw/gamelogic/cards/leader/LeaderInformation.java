package it.polimi.ingsw.gamelogic.cards.leader;

import it.polimi.ingsw.gamelogic.enums.LeaderCategory;

/**
 * Class that describes the basic information of a Leader Card
 */
public class LeaderInformation {
    private String name;
    private LeaderCategory leaderCategory;

    public LeaderInformation(String name, LeaderCategory leaderCategory) {
        this.name = name;
        this.leaderCategory = leaderCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LeaderCategory getLeaderCategory() {
        return leaderCategory;
    }

    public void setLeaderCategory(LeaderCategory leaderCategory) {
        this.leaderCategory = leaderCategory;
    }
}
