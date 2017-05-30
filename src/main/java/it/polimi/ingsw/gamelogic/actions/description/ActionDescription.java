package it.polimi.ingsw.gamelogic.actions.description;

import it.polimi.ingsw.gamelogic.actions.ActionVisitor;

/**
 * TODO: JavaDoc
 */
public interface ActionDescription {
    void acceptActionVisitor(ActionVisitor actionVisitor);
}
