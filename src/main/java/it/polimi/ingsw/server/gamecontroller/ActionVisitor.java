package it.polimi.ingsw.server.gamecontroller;

import it.polimi.ingsw.server.gamelogic.actionsdescription.CardAction;
import it.polimi.ingsw.shared.model.actionsdescription.BoardAction;
import it.polimi.ingsw.shared.model.actionsdescription.LeaderAction;

/**
 * Interface that defines methods that are implemented by the classes that want to call the Visitors
 */
public interface ActionVisitor {
    void visitActionDescription(BoardAction boardAction);
    void visitActionDescription(CardAction cardAction);
    void visitActionDescription(LeaderAction leaderAction);
}
