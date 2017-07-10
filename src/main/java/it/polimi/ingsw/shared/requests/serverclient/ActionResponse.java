package it.polimi.ingsw.shared.requests.serverclient;

import it.polimi.ingsw.client.middleware.ClientReceiver;

import java.io.Serializable;

public class ActionResponse implements ServerClientRequest, Serializable {
    private boolean successful;

    public ActionResponse(boolean successful) {
        this.successful = successful;
    }

    @Override
    public void acceptServerClientRequestVisitor(ClientReceiver clientReceiver) {
        clientReceiver.visitServerClientRequest(this);
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
