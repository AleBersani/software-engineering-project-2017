package it.polimi.ingsw.gamecontroller;

import it.polimi.ingsw.gamelogic.actions.*;
import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.enums.AvailableActions;
import it.polimi.ingsw.gamelogic.enums.BoardIdentifiers;
import it.polimi.ingsw.gamelogic.requirements.ActualRequirements;
import it.polimi.ingsw.gamelogic.requirements.Requirements;
import it.polimi.ingsw.gamelogic.rewards.ActualRewards;
import it.polimi.ingsw.gamelogic.rewards.Rewards;

public class Action {
    private ActionDescription actionDescription;
    private Requirements requiredRequirements;
    private Rewards rewards;

    public static void main(String argv[]) {
        ActionDescription actionDescription = new BoardAction(
                new BasicAction(AvailableActions.ACTIONSPACES_A, BoardIdentifiers.COUNCIL_PALACE, 0));
        Requirements requirements = new ActualRequirements();
        Rewards rewards = new ActualRewards(new Goods());

        Action action = new Action(actionDescription, requirements, rewards);

        action.getActionDescription().callAction(action);

        action.setActionDescription(new LeaderAction(AvailableActions.LEADER_ACTIVATION,""));

        action.getActionDescription().callAction(action);
    }

    public Action(ActionDescription actionDescription, Requirements requiredRequirements, Rewards rewards) {
        this.actionDescription = actionDescription;
        this.requiredRequirements = requiredRequirements;
        this.rewards = rewards;
    }

    public void readAction(BoardAction boardAction) {
        System.out.println("BoardAction");
    }

    public void readAction(CardAction cardAction) {
        System.out.println("CardAction");
    }

    public void readAction(CardActionInstantPoints cardActionInstantPoints) {
        System.out.println("CardActionInstantPoints");
    }

    public void readAction(LeaderAction leaderAction) {
        System.out.println("LeaderAction");
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

    public Rewards getRewards() {
        return rewards;
    }

    public void setRewards(Rewards rewards) {
        this.rewards = rewards;
    }
}
