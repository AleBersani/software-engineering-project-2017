package it.polimi.ingsw.server.gamecontroller.helpers.choices;

import it.polimi.ingsw.server.gamelogic.basics.PlayerConfiguration;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderCard;
import org.apache.commons.lang.mutable.MutableInt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class LeaderCardChoiceHandler {
    private int numberOfPlayers;
    private Map<String, List<LeaderCard>> initialLeaderCardsForPlayers;
    private Map<String, List<LeaderCard>> leaderCardsForPlayers;
    private Map<String, MutableInt> counterForPlayer;
    private AtomicInteger numberOfPlayersReady;

    public LeaderCardChoiceHandler() {
        numberOfPlayers = 0;
        initialLeaderCardsForPlayers = new HashMap<>();
        leaderCardsForPlayers = new HashMap<>();
        counterForPlayer = new HashMap<>();
        numberOfPlayersReady = new AtomicInteger(0);
    }

    public void setup(List<LeaderCard> leaderCards, List<String> playerNames) {
        numberOfPlayers = playerNames.size();
        int offset = 0;
        for (String playerName : playerNames) {
            initialLeaderCardsForPlayers.put(playerName, new ArrayList<>());
            leaderCardsForPlayers.put(playerName, new ArrayList<>());
            counterForPlayer.put(playerName, new MutableInt(0));
            for (int i = offset; i < (PlayerConfiguration.getNumberOfLeaders() + offset); i++) {
                initialLeaderCardsForPlayers.get(playerName).add(leaderCards.get(i));
            }
            offset = offset + PlayerConfiguration.getNumberOfLeaders();
        }
    }

    public boolean addChosenLeaderToPlayer(String playerName, String leaderName) {
        List<LeaderCard> leaderCardsOfPlayer = initialLeaderCardsForPlayers.get(playerName);
        for (int i = 0; i < leaderCardsOfPlayer.size(); i++) {
            if (leaderCardsOfPlayer.get(i).getLeaderName().equals(leaderName)) {
                leaderCardsForPlayers.get(playerName).add(leaderCardsOfPlayer.get(i));
                counterForPlayer.get(playerName).increment();
                leaderCardsOfPlayer.remove(i);
                break;
            }
        }

        if (checkStatus(counterForPlayer.get(playerName).intValue())) {
            shiftRemainingCards();
            return true;
        }
        else return false;
    }

    private boolean checkStatus(int valueToCheck) {
        boolean status = true;
        for (MutableInt mutableInt : counterForPlayer.values()) {
            if (mutableInt.toInteger() < valueToCheck) {
                status = false;
            }
        }
        return status;
    }

    private void shiftRemainingCards() {
        int i = 1;
        List<List<LeaderCard>> leaderCardsLists = new ArrayList<>(initialLeaderCardsForPlayers.values());
        for (Map.Entry<String, List<LeaderCard>> entry : initialLeaderCardsForPlayers.entrySet()) {
            entry.setValue(leaderCardsLists.get(i));
            i++;
            if (i >= numberOfPlayers) {
                i = 0;
            }
        }
    }

    public boolean phaseEnded() {
        return checkStatus(PlayerConfiguration.getNumberOfLeaders());
    }

    public boolean checkFinalStatus() {
        return checkStatus(PlayerConfiguration.getNumberOfLeaders());
    }

    public Map<String, List<LeaderCard>> getInitialLeaderCardsForPlayers() {
        return initialLeaderCardsForPlayers;
    }

    public void setInitialLeaderCardsForPlayers(Map<String, List<LeaderCard>> initialLeaderCardsForPlayers) {
        this.initialLeaderCardsForPlayers = initialLeaderCardsForPlayers;
    }

    public Map<String, List<LeaderCard>> getLeaderCardsForPlayers() {
        return leaderCardsForPlayers;
    }

    public void setLeaderCardsForPlayers(Map<String, List<LeaderCard>> leaderCardsForPlayers) {
        this.leaderCardsForPlayers = leaderCardsForPlayers;
    }

    public AtomicInteger getNumberOfPlayersReady() {
        return numberOfPlayersReady;
    }

    public void setNumberOfPlayersReady(AtomicInteger numberOfPlayersReady) {
        this.numberOfPlayersReady = numberOfPlayersReady;
    }
}
