package it.polimi.ingsw.server.gamecontroller;

import it.polimi.ingsw.server.gamelogic.basics.PlayerConfiguration;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderCard;
import org.apache.commons.lang.mutable.MutableInt;

import java.util.*;

public class LeaderCardChoiceHandler {
    private Map<String, List<LeaderCard>> initialLeaderCardsForPlayer;
    private Map<String, List<LeaderCard>> leaderCardsForPlayer;
    private Map<String, MutableInt> counterForPlayer;

    public LeaderCardChoiceHandler() {
        initialLeaderCardsForPlayer = new HashMap<>();
        leaderCardsForPlayer = new HashMap<>();
        counterForPlayer = new HashMap<>();
    }

    public Map<String, List<LeaderCard>> setup(List<LeaderCard> leaderCards, List<String> playerNames) {
        int y = 0;
        for (String playerName : playerNames) {
            initialLeaderCardsForPlayer.put(playerName, new ArrayList<>());
            for (int i = 0; i < PlayerConfiguration.getNumberOfLeaders(); i++) {
                initialLeaderCardsForPlayer.get(playerName).add(leaderCards.get(i + y));
            }
            y += PlayerConfiguration.getNumberOfLeaders();
            leaderCardsForPlayer.put(playerName, new ArrayList<>());
            counterForPlayer.put(playerName, new MutableInt(0));
        }
        return initialLeaderCardsForPlayer;
    }

    public Optional<Map<String, List<LeaderCard>>> addChosenLeaderToPlayer(String playerName, LeaderCard leaderCard) {
        leaderCardsForPlayer.get(playerName).add(leaderCard);
        counterForPlayer.get(playerName).increment();
        leaderCardsForPlayer.get(playerName).remove(leaderCard);
        if (checkStatus(counterForPlayer.get(playerName).intValue())) {
            shiftRemainingCards();
            return Optional.of(initialLeaderCardsForPlayer);
        }
        return Optional.empty();
    }

    private void shiftRemainingCards() {
        int numberOfPlayers = initialLeaderCardsForPlayer.size();
        int i = 1;
        List<List<LeaderCard>> leaderCardsLists = new ArrayList<>(initialLeaderCardsForPlayer.values());
        for (Map.Entry<String, List<LeaderCard>> entry : initialLeaderCardsForPlayer.entrySet()) {
            entry.setValue(leaderCardsLists.get(i));
            i++;
            if (i >= numberOfPlayers) {
                i = 0;
            }
        }
    }

    public boolean checkFinalStatus() {
        return checkStatus(PlayerConfiguration.getNumberOfLeaders());
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
}
