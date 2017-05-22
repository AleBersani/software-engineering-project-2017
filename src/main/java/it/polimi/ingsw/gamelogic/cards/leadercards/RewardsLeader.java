package it.polimi.ingsw.gamelogic.cards.leadercards;

import it.polimi.ingsw.gamelogic.rewards.Rewards;

/**
 * Leader Cards having a decorator of Requirements
 * @see Rewards
 */
public class RewardsLeader implements LeadersBehaviour {
    private LeaderCard leaderCard; // Composite
    private Rewards rewards;

    public RewardsLeader(LeaderCard leaderCard, Rewards rewards) {
        this.leaderCard = leaderCard;
        this.rewards = rewards;
    }

    public LeaderCard getLeaderCard() {
        return leaderCard;
    }

    public void setLeaderCard(LeaderCard leaderCard) {
        this.leaderCard = leaderCard;
    }

    public Rewards getRewards() {
        return rewards;
    }

    public void setRewards(Rewards rewards) {
        this.rewards = rewards;
    }
}
