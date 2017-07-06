package it.polimi.ingsw.server.gamecontroller;

import it.polimi.ingsw.server.gameelements.BoardInformation;
import it.polimi.ingsw.server.gameelements.SetGameElements;
import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.server.gamelogic.player.BonusTiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BonusTileChoiceHandler {
    private int numberOfPlayers;
    private List<String> players;
    private List<BonusTiles> bonusTiles;
    private Map<String, BonusTiles> bonusTileForPlayer;
    private AtomicInteger numberOfPlayersReady;
    private int numberOfSatisfiedPlayers;

    public BonusTileChoiceHandler() {
        numberOfPlayers = 0;
        players = new ArrayList<>();
        bonusTiles = new ArrayList<>();
        bonusTileForPlayer = new HashMap<>();
        numberOfPlayersReady = new AtomicInteger(0);
        numberOfSatisfiedPlayers = 0;
    }

    public void setup(List<String> playerNames) {
        numberOfPlayers = playerNames.size();
        players = playerNames;

        for (String playerName : playerNames) {
            bonusTileForPlayer.put(playerName, new BonusTiles());
        }

        for (Map.Entry<String, List<ExchangingGoods>> entry : BoardInformation.getBonusTiles().entrySet()) {
            bonusTiles.add(new BonusTiles(entry.getKey(),
                    entry.getValue().get(0).getGoods(),
                    entry.getValue().get(1).getGoods()));
        }
    }

    public String getNextPlayer() {
        return players.get(0);
    }

    public List<String> getRemainingBonusTiles() {
        List<String> remainingBonusTiles = new ArrayList<>();
        bonusTiles.forEach(b -> remainingBonusTiles.add(b.getBonusTileIdentifier()));
        return remainingBonusTiles;
    }

    public void addBonusTileToPlayer(String playerName, String bonusTileIdentifier) {
        numberOfSatisfiedPlayers++;
        bonusTiles.forEach(b -> {
            if (b.getBonusTileIdentifier().equals(bonusTileIdentifier)) {
                bonusTileForPlayer.get(playerName).setBonusTile(b);
                bonusTiles.remove(b);
            }
        });
        players.remove(playerName);
    }

    public boolean phaseEnded() {
        return numberOfSatisfiedPlayers == numberOfPlayers;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public Map<String, BonusTiles> getBonusTileForPlayer() {
        return bonusTileForPlayer;
    }

    public void setBonusTileForPlayer(Map<String, BonusTiles> bonusTileForPlayer) {
        this.bonusTileForPlayer = bonusTileForPlayer;
    }

    public AtomicInteger getNumberOfPlayersReady() {
        return numberOfPlayersReady;
    }

    public void setNumberOfPlayersReady(AtomicInteger numberOfPlayersReady) {
        this.numberOfPlayersReady = numberOfPlayersReady;
    }
}
