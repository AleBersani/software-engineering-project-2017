package it.polimi.ingsw.server.gamecontroller.helpers;

import it.polimi.ingsw.server.connection.ConnectedClient;
import it.polimi.ingsw.server.middleware.ServerSender;
import it.polimi.ingsw.server.middleware.ServerSenderHandler;
import it.polimi.ingsw.shared.requests.serverclient.ServerClientRequest;

import java.util.List;

public class Sender {
    private List<ConnectedClient> connectedClients;

    public Sender(List<ConnectedClient> connectedClients) {
        this.connectedClients = connectedClients;
    }

    public void sendToAll(ServerClientRequest serverClientRequest) {
        for (ConnectedClient connectedClient : connectedClients) {
            ServerSender serverSender = new ServerSenderHandler();
            serverSender.sendToClient(connectedClient.getConnectionStream(), serverClientRequest);
        }
    }

    public void sendTo(String playerName, ServerClientRequest serverClientRequest) {
        for (ConnectedClient connectedClient : connectedClients) {
            if (connectedClient.getPlayerName().equals(playerName)) {
                ServerSender serverSender = new ServerSenderHandler();
                serverSender.sendToClient(connectedClient.getConnectionStream(), serverClientRequest);
            }
        }
    }
}
