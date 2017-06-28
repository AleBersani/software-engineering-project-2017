package it.polimi.ingsw.server.gamelogic.player;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.shared.model.GeneralColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        PlayerDetails playerDetails = new PlayerDetails("", GeneralColor.BLUE);
        BonusTiles bonusTiles = new BonusTiles(new Goods(new Resources(1,2,3,0),
                new Points(1,2,3)),
                new Goods(new Resources(1,3,4,0)));
        PlayerBoard playerBoard = new PlayerBoard(bonusTiles, new Goods());
        player = new Player(playerDetails, playerBoard);
    }

    @Test
    void testEquals() {
        PlayerDetails playerDetails = new PlayerDetails("", GeneralColor.BLUE);
        BonusTiles bonusTiles = new BonusTiles(new Goods(new Resources(1,2,3,0),
                new Points(1,2,3)),
                new Goods(new Resources(1,3,4,0)));
        PlayerBoard playerBoard = new PlayerBoard(bonusTiles, new Goods());
        Player playerToConfront = new Player(playerDetails, playerBoard);
        assertTrue(playerToConfront.equals(player));
    }

}