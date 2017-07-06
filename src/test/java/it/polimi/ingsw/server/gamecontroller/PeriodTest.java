package it.polimi.ingsw.server.gamecontroller;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.board.Board;
import it.polimi.ingsw.server.gamelogic.cards.excommunicationtiles.ExcommunicationTile;
import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;
import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.server.gamelogic.player.PlayerBoard;
import it.polimi.ingsw.server.gamelogic.player.PlayerDetails;
import it.polimi.ingsw.shared.model.GeneralColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PeriodTest {
    private Period period;
    private List<PlayerDetails> playerDetails;
    private List<PlayerDetails> councilPalacePlayersOrder;

    @BeforeEach
    void setUp() {
        period = new Period(new ExcommunicationTile("1.2", PeriodNumber.FIRST), new ArrayList<>(),
                PeriodNumber.FIRST);
        councilPalacePlayersOrder = new ArrayList<>();
        playerDetails = new ArrayList<>();
        playerDetails.add(new PlayerDetails("A", GeneralColor.BLUE));
        playerDetails.add(new PlayerDetails("B", GeneralColor.GREEN));
        playerDetails.add(new PlayerDetails("C", GeneralColor.YELLOW));
        playerDetails.add(new PlayerDetails("D", GeneralColor.PURPLE));
        councilPalacePlayersOrder.add(new PlayerDetails("C", GeneralColor.YELLOW));
        councilPalacePlayersOrder.add(new PlayerDetails("B", GeneralColor.GREEN));
    }

    @Test
    void testCalculateNewPlayerOrder() {
        List<PlayerDetails> playerDetailsExpected = new ArrayList<>();
        playerDetailsExpected.add(new PlayerDetails("C", GeneralColor.YELLOW));
        playerDetailsExpected.add(new PlayerDetails("B", GeneralColor.GREEN));
        playerDetailsExpected.add(new PlayerDetails("A", GeneralColor.BLUE));
        playerDetailsExpected.add(new PlayerDetails("D", GeneralColor.PURPLE));
        assertEquals(playerDetailsExpected, period.calculateNewPlayerOrder(playerDetails, councilPalacePlayersOrder));
    }
}