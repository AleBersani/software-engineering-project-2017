package it.polimi.ingsw.gamelogic.cards.leadercards;

import it.polimi.ingsw.gamelogic.cards.leadercards.common.LeaderCard;
import it.polimi.ingsw.gamelogic.decorators.rewards.RewardsModifier;

/**
 * Leader Cards having a decorator of Requirements
 * @see RewardsModifier
 */
public class RewardsLeader {
    private LeaderCard leaderCard; // Composite
    private RewardsModifier rewardsModifier;

    public RewardsLeader(LeaderCard leaderCard, RewardsModifier rewardsModifier) {
        this.leaderCard = leaderCard;
        this.rewardsModifier = rewardsModifier;
    }

    public LeaderCard getLeaderCard() {
        return leaderCard;
    }

    public void setLeaderCard(LeaderCard leaderCard) {
        this.leaderCard = leaderCard;
    }

    public RewardsModifier getRewardsModifier() {
        return rewardsModifier;
    }

    public void setRewardsModifier(RewardsModifier rewardsModifier) {
        this.rewardsModifier = rewardsModifier;
    }
}
