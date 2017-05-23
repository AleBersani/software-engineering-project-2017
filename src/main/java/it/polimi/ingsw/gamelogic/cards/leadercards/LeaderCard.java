package it.polimi.ingsw.gamelogic.cards.leadercards;

import it.polimi.ingsw.gamelogic.basics.LeaderCost;

public class LeaderCard {
    private String name;
    private LeaderCost leaderCost;

    public LeaderCard(String name, LeaderCost leaderCost) {
        this.name = name;
        this.leaderCost = leaderCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LeaderCost getLeaderCost() {
        return leaderCost;
    }

    public void setLeaderCost(LeaderCost leaderCost) {
        this.leaderCost = leaderCost;
    }
}
