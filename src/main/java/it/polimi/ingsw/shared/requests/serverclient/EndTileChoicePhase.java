package it.polimi.ingsw.shared.requests.serverclient;

import it.polimi.ingsw.client.middleware.ClientReceiver;

public class EndTileChoicePhase implements ServerClientRequest {
    @Override
    public void acceptServerClientRequestVisitor(ClientReceiver clientReceiver) {
        clientReceiver.visitServerClientRequest(this);
    }
}
