package it.polimi.ingsw.gamelogic.cards.leadercards;

import it.polimi.ingsw.gamelogic.actions.ActionDescription;
import it.polimi.ingsw.gamelogic.actions.BoardAction;

/**
 * Leader Cards having an ActionDescription
 */
public class InstantActionLeader implements LeadersBehaviour {
    private  LeaderCard leaderCard; // Composite
    private ActionDescription actionDescription;

    public InstantActionLeader(LeaderCard leaderCard, ActionDescription actionDescription) {
        this.leaderCard = leaderCard;
        this.actionDescription = actionDescription;
    }

    public LeaderCard getLeaderCard() {
        return leaderCard;
    }

    public void setLeaderCard(LeaderCard leaderCard) {
        this.leaderCard = leaderCard;
    }

    public ActionDescription getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(ActionDescription actionDescription) {
        this.actionDescription = actionDescription;
    }
}
