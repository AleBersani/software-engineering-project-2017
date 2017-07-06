package it.polimi.ingsw.shared.requests.serverclient;

import it.polimi.ingsw.client.middleware.ClientReceiver;

import java.util.List;

public class TileChoice implements ServerClientRequest {
    private List<String> bonusTiles;

    public TileChoice(List<String> bonusTiles) {
        this.bonusTiles = bonusTiles;
    }

    @Override
    public void acceptServerClientRequestVisitor(ClientReceiver clientReceiver) {
        clientReceiver.visitServerClientRequest(this);
    }

    public List<String> getBonusTiles() {
        return bonusTiles;
    }

    public void setBonusTiles(List<String> bonusTiles) {
        this.bonusTiles = bonusTiles;
    }
}
