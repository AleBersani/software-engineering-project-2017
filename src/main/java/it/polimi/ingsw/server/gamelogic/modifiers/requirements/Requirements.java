package it.polimi.ingsw.server.gamelogic.modifiers.requirements;

import it.polimi.ingsw.server.gamelogic.player.Player;

/**
 * Interface implemented by all the requirements modifiers
 */
@FunctionalInterface
public interface Requirements {
    boolean hasRequirements(Player player);
}
