package it.polimi.ingsw.gamelogic.actions;

import it.polimi.ingsw.gamelogic.actions.description.*;
import it.polimi.ingsw.gamelogic.enums.ActionType;
import it.polimi.ingsw.gamelogic.modifiers.requirements.Requirements;
import it.polimi.ingsw.gamelogic.modifiers.rewards.BasicRewards;

import java.util.List;

/**
 * Class that implements the Visitor interface (ActionVisitor). Given the type of Action, set by the Action
 * Description, one of the "visit-" methods is implemented. Then it checks the if the Requirements for that
 * Action are satisfied and if they are, the Rewards are given to the player.
 */
public class Action implements ActionVisitor {
    private ActionDescription actionDescription;
    private Requirements requiredRequirements;
    private List<BasicRewards> basicRewardsList;

    public static void main(String argv[]) {
        Action action = new Action();

        action.setActionDescription(new BoardAction(new BasicAction()));
        action.getActionDescription().acceptActionVisitor(action);

        action.setActionDescription(new LeaderAction(ActionType.LEADER_ACTIVATION, "Leader name"));
        action.getActionDescription().acceptActionVisitor(action);
    }

    @Override
    public void visitActionDescription(BoardAction boardAction) {
        System.out.println("Board Action");
    }

    @Override
    public void visitActionDescription(CardAction cardAction) {
        System.out.println("Card Action");
    }

    @Override
    public void visitActionDescription(LeaderAction leaderAction) {
        System.out.println("Leader Action");
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
