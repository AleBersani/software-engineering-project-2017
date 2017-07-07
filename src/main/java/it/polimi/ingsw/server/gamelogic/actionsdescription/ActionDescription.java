package it.polimi.ingsw.server.gamelogic.actionsdescription;

import it.polimi.ingsw.server.gamecontroller.ActionVisitor;

import java.io.Serializable;

/**
 * Interface with the method that accepts all the visitors of the various ActionDescriptions
 */
@FunctionalInterface
public interface ActionDescription extends Serializable {
    void acceptActionVisitor(ActionVisitor actionVisitor);
}
