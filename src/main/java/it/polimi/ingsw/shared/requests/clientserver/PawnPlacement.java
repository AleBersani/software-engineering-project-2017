package it.polimi.ingsw.shared.requests.clientserver;

import it.polimi.ingsw.server.gamelogic.actionsdescription.ActionDescription;
import it.polimi.ingsw.server.middleware.ServerReceiver;

import java.io.Serializable;

public class PawnPlacement implements Serializable, ClientServerRequest {
    private BaseInformation baseInformation;
    private ActionDescription actionDescription;

    public PawnPlacement(BaseInformation baseInformation, ActionDescription actionDescription) {
        this.baseInformation = baseInformation;
        this.actionDescription = actionDescription;
    }

    @Override
    public void acceptServerReceiver(ServerReceiver serverReceiver) {
        serverReceiver.visitClientServerRequest(this);
    }

    public int getGameId() {
        return baseInformation.getGameId();
    }

    public void setGameId(int gameId) {
        baseInformation.setGameId(gameId);
    }

    public String getPlayerName() {
        return baseInformation.getPlayerName();
    }

    public void setPlayerName(String playerName) {
        baseInformation.setPlayerName(playerName);
    }

    public ActionDescription getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(ActionDescription actionDescription) {
        this.actionDescription = actionDescription;
    }
}
