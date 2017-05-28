package it.polimi.ingsw.gamecontroller;

import it.polimi.ingsw.gamelogic.actions.*;
import it.polimi.ingsw.gamelogic.decorators.rewards.RewardsModifier;
import it.polimi.ingsw.gamelogic.decorators.requirements.Requirements;

public class Action {
    private ActionDescription actionDescription;
    private Requirements requiredRequirements;
    private RewardsModifier rewardsModifier;

    public Action(ActionDescription actionDescription, Requirements requiredRequirements, RewardsModifier rewardsModifier) {
        this.actionDescription = actionDescription;
        this.requiredRequirements = requiredRequirements;
        this.rewardsModifier = rewardsModifier;
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

    public RewardsModifier getRewardsModifier() {
        return rewardsModifier;
    }

    public void setRewardsModifier(RewardsModifier rewardsModifier) {
        this.rewardsModifier = rewardsModifier;
    }
}
