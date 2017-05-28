package it.polimi.ingsw.gamelogic.cards.leadercards;

import it.polimi.ingsw.gamelogic.cards.leadercards.common.LeaderCard;
import it.polimi.ingsw.gamelogic.decorators.requirements.Requirements;

/**
 * Leader Cards having a decorator of Requirements
 * @see Requirements
 */
public class RequirementsLeader {
    private LeaderCard leaderCard;
    private Requirements requirements;

    public RequirementsLeader(LeaderCard leaderCard, Requirements requirements) {
        this.leaderCard = leaderCard;
        this.requirements = requirements;
    }

    public LeaderCard getLeaderCard() {
        return leaderCard;
    }

    public void setLeaderCard(LeaderCard leaderCard) {
        this.leaderCard = leaderCard;
    }

    public Requirements getRequirements() {
        return requirements;
    }

    public void setRequirements(Requirements requirements) {
        this.requirements = requirements;
    }
}
