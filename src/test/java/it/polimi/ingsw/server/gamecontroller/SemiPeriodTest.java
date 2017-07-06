package it.polimi.ingsw.server.gamecontroller;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.board.Board;
import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.server.gamelogic.player.PlayerBoard;
import it.polimi.ingsw.server.gamelogic.player.PlayerDetails;
import it.polimi.ingsw.shared.model.GeneralColor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

class SemiPeriodTest {
    private SemiPeriod semiPeriod;
    private List<PlayerDetails> playerDetails;

    @BeforeEach
    void setUp() {
        List<Player> players = new ArrayList<>();
        playerDetails = new ArrayList<>();
        playerDetails.add(new PlayerDetails("A", GeneralColor.BLUE));
        playerDetails.add(new PlayerDetails("B", GeneralColor.GREEN));
        playerDetails.add(new PlayerDetails("C", GeneralColor.YELLOW));
        playerDetails.add(new PlayerDetails("D", GeneralColor.PURPLE));
        for (PlayerDetails playerDetailsToAdd : playerDetails) {
            players.add(new Player(playerDetailsToAdd, new PlayerBoard(new Goods())));
        }
        players.get(3).getPlayerCardsEffects().setPlayerOrderWeight(20);
        semiPeriod = new SemiPeriod(new ArrayList<>(), players, new Board());
        semiPeriod.setBasePlayersOrder(playerDetails);
    }

    @Test
    void testCalculateTotalPlayer() {/*
        List<PlayerDetails> playerDetailsExpected = new ArrayList<>();
        playerDetailsExpected.add(playerDetails.get(0));
        playerDetailsExpected.add(playerDetails.get(1));
        playerDetailsExpected.add(playerDetails.get(2));
        playerDetailsExpected.add(playerDetails.get(0));
        playerDetailsExpected.add(playerDetails.get(1));
        playerDetailsExpected.add(playerDetails.get(2));
        playerDetailsExpected.add(playerDetails.get(3));
        playerDetailsExpected.add(playerDetails.get(0));
        playerDetailsExpected.add(playerDetails.get(1));
        playerDetailsExpected.add(playerDetails.get(2));
        playerDetailsExpected.add(playerDetails.get(3));
        playerDetailsExpected.add(playerDetails.get(0));
        playerDetailsExpected.add(playerDetails.get(1));
        playerDetailsExpected.add(playerDetails.get(2));
        playerDetailsExpected.add(playerDetails.get(3));
        playerDetailsExpected.add(playerDetails.get(3));
        List<PlayerDetails> playerDetailsResult = semiPeriod.calculateTotalPlayer();
        assertEquals(playerDetailsExpected, playerDetailsResult);*/
    }
}