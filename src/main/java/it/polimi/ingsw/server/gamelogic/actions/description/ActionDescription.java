package it.polimi.ingsw.server.gamelogic.actions.description;

import it.polimi.ingsw.server.gamelogic.actions.ActionVisitor;

/**
 * Interface with the method that accepts all the visitors of the various ActionDescriptions
 */
@FunctionalInterface
public interface ActionDescription {
    void acceptActionVisitor(ActionVisitor actionVisitor);
}
