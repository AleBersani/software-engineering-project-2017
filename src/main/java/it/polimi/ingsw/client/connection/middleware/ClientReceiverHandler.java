package it.polimi.ingsw.client.connection.middleware;

import it.polimi.ingsw.shared.requests.serverclient.SimpleMessage;
import it.polimi.ingsw.shared.requests.serverclient.UpdateActionSpaces;
import it.polimi.ingsw.shared.requests.serverclient.UpdateTowers;

public class ClientReceiverHandler implements ClientReceiver {
    @Override
    public void visitServerClientRequest(SimpleMessage simpleMessage) {
        System.out.println(simpleMessage.getMessage());
    }

    @Override
    public void visitServerClientRequest(UpdateActionSpaces updateActionSpaces) {

    }

    @Override
    public void visitServerClientRequest(UpdateTowers updateTowers) {

    }
}
