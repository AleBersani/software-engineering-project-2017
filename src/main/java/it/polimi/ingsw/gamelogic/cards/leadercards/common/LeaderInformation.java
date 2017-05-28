package it.polimi.ingsw.gamelogic.cards.leadercards.common;

import it.polimi.ingsw.gamelogic.enums.LeaderCategory;
import it.polimi.ingsw.gamelogic.enums.LeaderContext;

/**
 * TODO: JavaDoc
 */
public class LeaderInformation {
    private String name;
    private String effectDescription;
    private LeaderContext leaderContext;
    private LeaderCategory leaderCategory;

    public LeaderInformation(String name, String effectDescription,
                             LeaderContext leaderContext, LeaderCategory leaderCategory) {
        this.name = name;
        this.effectDescription = effectDescription;
        this.leaderContext = leaderContext;
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

    public LeaderContext getLeaderContext() {
        return leaderContext;
    }

    public void setLeaderContext(LeaderContext leaderContext) {
        this.leaderContext = leaderContext;
    }

    public LeaderCategory getLeaderCategory() {
        return leaderCategory;
    }

    public void setLeaderCategory(LeaderCategory leaderCategory) {
        this.leaderCategory = leaderCategory;
    }
}
