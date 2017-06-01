package it.polimi.ingsw.gamelogic.actions;

import it.polimi.ingsw.gamelogic.actions.description.BoardAction;
import it.polimi.ingsw.gamelogic.actions.description.CardAction;
import it.polimi.ingsw.gamelogic.actions.description.CardActionInstantPoints;
import it.polimi.ingsw.gamelogic.actions.description.LeaderAction;

/**
 * Interface that defines methods that are implemented by the classes that want to call the Visitors
 */
public interface ActionVisitor {
    void visitActionDescription(BoardAction boardAction);
    void visitActionDescription(CardAction cardAction);
    void visitActionDescription(CardActionInstantPoints cardActionInstantPoints);
    void visitActionDescription(LeaderAction leaderAction);
}
