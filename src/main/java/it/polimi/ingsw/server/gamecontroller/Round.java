package it.polimi.ingsw.server.gamecontroller;

import it.polimi.ingsw.server.gamelogic.actions.description.ActionDescription;
import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.shared.model.PlayerDetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * //
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
        cose
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
