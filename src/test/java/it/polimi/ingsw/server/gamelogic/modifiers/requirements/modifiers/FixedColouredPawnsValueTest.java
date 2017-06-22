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

class FixedColouredPawnsValueTest {
    private BoardActionRequirements boardActionRequirements;
    private TowerActionRequirements towerActionRequirements;
    private Player player;

    private FixedColouredPawnsValue fixedColouredPawnsValue;

    @BeforeEach
    void setUp() {
        boardActionRequirements = new BoardActionRequirements(new SpaceActionRequirements(
                ActionType.MARKET, PawnColor.BLACK,
                3, 2, 2, false));

        SpaceActionRequirements spaceActionRequirements = new SpaceActionRequirements(
                ActionType.BLUE_TOWER, PawnColor.ORANGE,
                3, 1, 2, false);
        Goods requiredGoods = new Goods(new Resources(5,4,3,2));

        towerActionRequirements = new TowerActionRequirements(spaceActionRequirements, requiredGoods, new Goods(),
                new Goods(), false, false);

        player = new Player(new PlayerDetails(), new PlayerBoard(new BonusTiles(new Goods(), new Goods()),
                new Goods(new Resources(5,5,5,5))));

        List<Pawn> pawns = new ArrayList<>();
        pawns.add(new Pawn(2, PawnColor.BLACK));
        pawns.add(new Pawn(1, PawnColor.ORANGE));
        player.getPlayerBoard().setPawns(pawns);

        List<PawnColor> pawnColors = new ArrayList<>();
        pawnColors.add(PawnColor.BLACK);
        pawnColors.add(PawnColor.ORANGE);

        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(ActionType.MARKET);
        actionTypes.add(ActionType.BLUE_TOWER);

        fixedColouredPawnsValue = new FixedColouredPawnsValue(
                new AvailableActions(actionTypes), pawnColors, 5);
    }

    @Test
    void testModifyRequirementsOfBoardAction() {
        boardActionRequirements = fixedColouredPawnsValue.modifyRequirements(boardActionRequirements);
        assertTrue(boardActionRequirements.hasRequirements(player));
    }

    @Test
    void testModifyRequirementsOfTowerAction() {
        towerActionRequirements = fixedColouredPawnsValue.modifyRequirements(towerActionRequirements);
        assertTrue(towerActionRequirements.hasRequirements(player));
    }
}