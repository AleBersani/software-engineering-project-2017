package it.polimi.ingsw.shared.requests.serverclient;

import it.polimi.ingsw.client.middleware.ClientReceiver;
import it.polimi.ingsw.shared.model.GeneralColor;

public class ChosenGameResponse implements ServerClientRequest {
    private boolean accepted;
    private GeneralColor playerColor;

    public ChosenGameResponse(boolean accepted, GeneralColor playerColor) {
        this.accepted = accepted;
        this.playerColor = playerColor;
    }

    @Override
    public void acceptServerClientRequestVisitor(ClientReceiver clientReceiver) {
        clientReceiver.visitServerClientRequest(this);
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public GeneralColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(GeneralColor playerColor) {
        this.playerColor = playerColor;
    }
}
