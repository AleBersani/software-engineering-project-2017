package it.polimi.ingsw.shared.requests.clientserver;

import it.polimi.ingsw.server.middleware.ServerReceiver;

import java.io.Serializable;

public class ChosenLorenzo implements ClientServerRequest, Serializable{
    private BaseInformation baseInformation;
    private String leaderName;

    public ChosenLorenzo(BaseInformation baseInformation, String leaderName) {
        this.baseInformation = baseInformation;
        this.leaderName = leaderName;
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

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }
}
