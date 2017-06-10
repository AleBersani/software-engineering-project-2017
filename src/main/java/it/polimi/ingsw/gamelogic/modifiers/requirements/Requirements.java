package it.polimi.ingsw.gamelogic.modifiers.requirements;

import it.polimi.ingsw.gamelogic.player.Player;

/**
 * Interface implemented by all the requirements modifiers
 */
@FunctionalInterface
public interface Requirements {
    boolean hasRequirements(Player player);
}
