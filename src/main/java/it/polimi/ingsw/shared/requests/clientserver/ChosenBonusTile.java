package it.polimi.ingsw.shared.requests.clientserver;

import it.polimi.ingsw.server.middleware.ServerReceiver;

public class ChosenBonusTile implements ClientServerRequest {
    private int gameId;
    private String playerName;
    private String bonusTileIdentifier;

    public ChosenBonusTile(int gameId, String playerName, String bonusTileIdentifier) {
        this.gameId = gameId;
        this.playerName = playerName;
        this.bonusTileIdentifier = bonusTileIdentifier;
    }

    @Override
    public void acceptServerReceiver(ServerReceiver serverReceiver) {
        serverReceiver.visitClientServerRequest(this);
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getBonusTileIdentifier() {
        return bonusTileIdentifier;
    }

    public void setBonusTileIdentifier(String bonusTileIdentifier) {
        this.bonusTileIdentifier = bonusTileIdentifier;
    }
}
