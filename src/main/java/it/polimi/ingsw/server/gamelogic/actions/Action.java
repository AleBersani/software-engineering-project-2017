package it.polimi.ingsw.server.gamelogic.actions;

import it.polimi.ingsw.server.gamelogic.actions.description.ActionDescription;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.Requirements;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.BasicRewards;

import java.util.List;

/**
 * Class that implements the Visitor interface (ActionVisitor). Given the type of Action, set by the Action
 * Description, one of the "visit-" methods is implemented. Then it checks the if the Requirements for that
 * Action are satisfied and if they are, the Rewards are given to the player.
 */
public class Action {
    private ActionDescription actionDescription;
    private Requirements requiredRequirements;
    private List<BasicRewards> basicRewardsList;

    public Action(ActionDescription actionDescription, Requirements requiredRequirements, List<BasicRewards> basicRewardsList) {
        this.actionDescription = actionDescription;
        this.requiredRequirements = requiredRequirements;
        this.basicRewardsList = basicRewardsList;
    }

    public ActionDescription getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(ActionDescription actionDescription) {
        this.actionDescription = actionDescription;
    }

    public Requirements getRequiredRequirements() {
        return requiredRequirements;
    }

    public void setRequiredRequirements(Requirements requiredRequirements) {
        this.requiredRequirements = requiredRequirements;
    }

    public List<BasicRewards> getBasicRewardsList() {
        return basicRewardsList;
    }

    public void setBasicRewardsList(List<BasicRewards> basicRewardsList) {
        this.basicRewardsList = basicRewardsList;
    }
}
