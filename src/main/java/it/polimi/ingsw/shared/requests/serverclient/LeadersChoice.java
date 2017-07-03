package it.polimi.ingsw.shared.requests.serverclient;

import it.polimi.ingsw.client.middleware.ClientReceiver;

import java.util.List;

public class LeadersChoice implements ServerClientRequest {
    private List<String> leaders;

    public LeadersChoice(List<String> leaders) {
        this.leaders = leaders;
    }

    @Override
    public void acceptServerClientRequestVisitor(ClientReceiver clientReceiver) {
        clientReceiver.visitServerClientRequest(this);
    }

    public List<String> getLeaders() {
        return leaders;
    }

    public void setLeaders(List<String> leaders) {
        this.leaders = leaders;
    }
}
