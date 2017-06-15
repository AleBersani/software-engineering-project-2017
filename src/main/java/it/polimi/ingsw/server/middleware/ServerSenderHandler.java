package it.polimi.ingsw.server.middleware;

import it.polimi.ingsw.shared.requests.serverclient.ServerClientRequest;

import java.util.logging.Logger;

public class ServerSenderHandler implements ServerSender {
    private final static Logger LOGGER = Logger.getLogger(ServerSenderHandler.class.getName());
    private ServerClientRequest serverClientRequest;

    public ServerSenderHandler(ServerClientRequest serverClientRequest) {
        this.serverClientRequest = serverClientRequest;
    }

    @Override
    public void sendToClient(String playerName) {/*
        ConnectionStream connectionStream = GamesConnections.getPlayerConnectionStream(playerName);
        if (connectionStream.getSocket().isPresent()) {

        } else if (connectionStream.getRegistrable().isPresent()) {
            Registrable registrable = connectionStream.getRegistrable().get();
            try {
                registrable.update("ciao");
            } catch (RemoteException e) {
                LOGGER.log(Level.SEVERE,"An exception was thrown: RMI callback", e);
            }
        }*/
    }
}
