package it.polimi.ingsw.server.gamelogic.cards.leader;

import it.polimi.ingsw.shared.model.LeaderCategory;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LeaderInformation that = (LeaderInformation) o;
        return Objects.equals(getName(), that.getName()) &&
                getLeaderCategory() == that.getLeaderCategory();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLeaderCategory());
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
