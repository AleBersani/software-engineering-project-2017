package it.polimi.ingsw.server.gamelogic.actions;

import it.polimi.ingsw.server.gamelogic.actions.description.ActionDescription;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.Requirements;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.BasicRewards;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Action action = (Action) o;
        return Objects.equals(getActionDescription(), action.getActionDescription()) &&
                Objects.equals(getRequiredRequirements(), action.getRequiredRequirements()) &&
                Objects.equals(getBasicRewardsList(), action.getBasicRewardsList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActionDescription(), getRequiredRequirements(), getBasicRewardsList());
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
