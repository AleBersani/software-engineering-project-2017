package it.polimi.ingsw.gamecontroller;

import it.polimi.ingsw.gamelogic.actions.description.ActionDescription;
import it.polimi.ingsw.gamelogic.player.Player;
import it.polimi.ingsw.gamelogic.player.PlayerDetails;

import java.util.*;

/**
 * TODO: JavaDoc
 */
public class Round extends Observable {
    private List<Player> orderedPlayers;

    private Map<PlayerDetails, List<ActionDescription>> actionsForPlayer;

    public Round(List<Player> orderedPlayers) {
        this.orderedPlayers = orderedPlayers;
        actionsForPlayer = new HashMap<>();
    }

    public void initRound() {
        /*
        TODO: cose
         */
    }

    public List<Player> getOrderedPlayers() {
        return orderedPlayers;
    }

    public void setOrderedPlayers(List<Player> orderedPlayers) {
        this.orderedPlayers = orderedPlayers;
    }

    public Map<PlayerDetails, List<ActionDescription>> getActionsForPlayer() {
        return actionsForPlayer;
    }

    public void setActionsForPlayer(Map<PlayerDetails, List<ActionDescription>> actionsForPlayer) {
        this.actionsForPlayer = actionsForPlayer;
    }
}
