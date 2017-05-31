package it.polimi.ingsw.gamelogic.actions;

import it.polimi.ingsw.gamelogic.actions.description.*;
import it.polimi.ingsw.gamelogic.enums.ActionType;
import it.polimi.ingsw.gamelogic.modifiers.rewards.BasicRewards;
import it.polimi.ingsw.gamelogic.modifiers.requirements.Requirements;

import java.util.List;

/**
 * TODO: JavaDoc
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
        //TODO
        System.out.println("Board Action");
    }

    @Override
    public void visitActionDescription(CardAction cardAction) {
        //TODO
        System.out.println("Card Action");
    }

    @Override
    public void visitActionDescription(CardActionInstantPoints cardActionInstantPoints) {
        //TODO
        System.out.println("Card Action Insta Points");
    }

    @Override
    public void visitActionDescription(LeaderAction leaderAction) {
        //TODO
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
