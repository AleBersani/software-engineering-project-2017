package it.polimi.ingsw.gamelogic.actions;

import it.polimi.ingsw.gamelogic.actions.description.*;
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

    /*
     TODO: ALL
     */

    @Override
    public void visitBoardAction(BoardAction boardAction) {
        /*
        TODO
         */
        System.out.println("Board Action");
    }

    @Override
    public void visitCardAction(CardAction cardAction) {
        /*
        TODO
         */
        System.out.println("Card Action");
    }

    @Override
    public void visitCardActionInstantPoints(CardActionInstantPoints cardActionInstantPoints) {
        /*
        TODO
         */
        System.out.println("Card Action Insta Points");
    }

    @Override
    public void visitLeaderAction(LeaderAction leaderAction) {
        /*
        TODO
         */
        System.out.println("Leader Action");
    }
}
