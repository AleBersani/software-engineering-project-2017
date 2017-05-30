package it.polimi.ingsw.gamelogic.actions;

import it.polimi.ingsw.gamelogic.actions.description.BoardAction;
import it.polimi.ingsw.gamelogic.actions.description.CardAction;
import it.polimi.ingsw.gamelogic.actions.description.CardActionInstantPoints;
import it.polimi.ingsw.gamelogic.actions.description.LeaderAction;

/**
 * TODO: JavaDoc
 */
public interface ActionVisitor {
    void visitBoardAction(BoardAction boardAction);
    void visitCardAction(CardAction cardAction);
    void visitCardActionInstantPoints(CardActionInstantPoints cardActionInstantPoints);
    void visitLeaderAction(LeaderAction leaderAction);
}
