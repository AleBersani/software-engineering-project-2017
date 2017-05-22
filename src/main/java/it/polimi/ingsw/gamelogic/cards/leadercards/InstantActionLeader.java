package it.polimi.ingsw.gamelogic.cards.leadercards;

import it.polimi.ingsw.gamelogic.actions.ActionDescription;
import it.polimi.ingsw.gamelogic.actions.BoardAction;

/**
 * Leader Cards having an ActionDescription
 */
public class InstantActionLeader implements LeadersBehaviour {
    private  LeaderCard leaderCard; // Composite
    private ActionDescription<BoardAction> actionActionDescription;

    public InstantActionLeader(LeaderCard leaderCard, ActionDescription<BoardAction> actionActionDescription) {
        this.leaderCard = leaderCard;
        this.actionActionDescription = actionActionDescription;
    }

    public LeaderCard getLeaderCard() {
        return leaderCard;
    }

    public void setLeaderCard(LeaderCard leaderCard) {
        this.leaderCard = leaderCard;
    }

    public ActionDescription<BoardAction> getActionActionDescription() {
        return actionActionDescription;
    }

    public void setActionActionDescription(ActionDescription<BoardAction> actionActionDescription) {
        this.actionActionDescription = actionActionDescription;
    }
}
