package it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.BoardActionRequirements;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.SpaceActionRequirements;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.TowerActionRequirements;
import it.polimi.ingsw.server.gamelogic.player.BonusTiles;
import it.polimi.ingsw.server.gamelogic.player.Pawn;
import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.server.gamelogic.player.PlayerBoard;
import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.PawnColor;
import it.polimi.ingsw.server.gamelogic.player.PlayerDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CanPlaceOnOccupiedSpaceTest {
    private BoardActionRequirements boardActionRequirements;
    private TowerActionRequirements towerActionRequirements;
    private Player player;

    private CanPlaceOnOccupiedSpace canPlaceOnOccupiedSpace;

    @BeforeEach
    void setUp() {
        boardActionRequirements = new BoardActionRequirements(new SpaceActionRequirements(
                ActionType.MARKET, PawnColor.BLACK,
                3, 3, 0, true));

        SpaceActionRequirements spaceActionRequirements = new SpaceActionRequirements(
                ActionType.BLUE_TOWER, PawnColor.ORANGE,
                3, 3, 0, true);
        Goods requiredGoods = new Goods(new Resources(5,4,3,2));

        towerActionRequirements = new TowerActionRequirements(spaceActionRequirements, requiredGoods, new Goods(),
                new Goods(), false, false);

        player = new Player(new PlayerDetails(), new PlayerBoard(new BonusTiles(new Goods(), new Goods()),
                new Goods(new Resources(5,5,5,5))));

        List<Pawn> pawns = new ArrayList<>();
        pawns.add(new Pawn(3, PawnColor.BLACK));
        pawns.add(new Pawn(3, PawnColor.ORANGE));
        player.getPlayerBoard().setPawns(pawns);

        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.MARKET);
        actionTypes.add(ActionType.BLUE_TOWER);
        canPlaceOnOccupiedSpace = new CanPlaceOnOccupiedSpace(new AvailableActions(actionTypes));
    }

    @Test
    void testModifyRequirementsOfBoardAction() {
        boardActionRequirements = canPlaceOnOccupiedSpace.modifyRequirements(boardActionRequirements);
        assertTrue(boardActionRequirements.hasRequirements(player));
    }

    @Test
    void testModifyRequirementsOfTowerAction() {
        towerActionRequirements = canPlaceOnOccupiedSpace.modifyRequirements(towerActionRequirements);
        assertTrue(towerActionRequirements.hasRequirements(player));
    }
}