package it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.BoardActionRequirements;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.SpaceActionRequirements;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.TowerActionRequirements;
import it.polimi.ingsw.server.gamelogic.player.*;
import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.PawnColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BonusActionValueTest {
    private BoardActionRequirements boardActionRequirements;
    private TowerActionRequirements towerActionRequirements;
    private Player player;

    private BonusActionValue bonusActionValue;

    @BeforeEach
    void setUp() {
        boardActionRequirements = new BoardActionRequirements(new SpaceActionRequirements(
                ActionType.MARKET, PawnColor.BLACK,
                4, 3, 1, false));

        SpaceActionRequirements spaceActionRequirements = new SpaceActionRequirements(
                ActionType.BLUE_TOWER, PawnColor.BLACK,
                4, 3, 0, false);
        Goods requiredGoods = new Goods(new Resources(5,4,3,2));

        towerActionRequirements = new TowerActionRequirements(spaceActionRequirements, requiredGoods, new Goods(),
                new Goods(), false, false);

        player = new Player(new PlayerDetails(), new PlayerBoard(new BonusTiles(new Goods(), new Goods()),
                new Goods(new Resources(5,5,5,5))));

        List<Pawn> pawns = new ArrayList<>();
        pawns.add(new Pawn(3, PawnColor.BLACK));
        player.getPlayerBoard().setPawns(pawns);
    }

    @Test
    void testModifyRequirementsOfBoardAction() {
        bonusActionValue = new BonusActionValue(new AvailableActions(ActionType.BLUE_TOWER), 1);
        boardActionRequirements = bonusActionValue.modifyRequirements(boardActionRequirements);
        assertTrue(boardActionRequirements.hasRequirements(player));
    }

    @Test
    void testModifyRequirementsOfBoardActionWithBonus() {
        bonusActionValue = new BonusActionValue(new AvailableActions(ActionType.MARKET), 1);
        boardActionRequirements.getSpaceActionRequirements().setNumberOfServants(0);

        boardActionRequirements = bonusActionValue.modifyRequirements(boardActionRequirements);
        assertTrue(boardActionRequirements.hasRequirements(player));
    }

    @Test
    void testModifyRequirementsOfTowerAction() {
        bonusActionValue = new BonusActionValue(new AvailableActions(ActionType.BLUE_TOWER), 1);
        towerActionRequirements = bonusActionValue.modifyRequirements(towerActionRequirements);
        assertTrue(towerActionRequirements.hasRequirements(player));
    }

    @Test
    void testModifyRequirementsOfTowerActionWithoutBonus() {
        bonusActionValue = new BonusActionValue(new AvailableActions(ActionType.MARKET), 1);
        towerActionRequirements = bonusActionValue.modifyRequirements(towerActionRequirements);
        assertFalse(towerActionRequirements.hasRequirements(player));
    }
}