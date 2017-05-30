package it.polimi.ingsw.gamelogic.actions;

import it.polimi.ingsw.gamelogic.actions.description.*;
import it.polimi.ingsw.gamelogic.decorators.rewards.BasicRewards;
import it.polimi.ingsw.gamelogic.decorators.requirements.Requirements;

/**
 * TODO: JavaDoc
 */
public class Action implements ActionVisitor {
    private ActionDescription actionDescription;
    private Requirements requiredRequirements;
    private BasicRewards basicRewards;

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
