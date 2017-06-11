package it.polimi.ingsw.gamelogic.modifiers.requirements;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.basics.Resources;
import it.polimi.ingsw.gamelogic.player.*;
import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.PawnColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TowerActionRequirementsTest {
    private TowerActionRequirements towerActionRequirements;
    private Player player;

    @BeforeEach
    void setUp() {
        SpaceActionRequirements spaceActionRequirements = new SpaceActionRequirements(
                ActionType.BLUE_TOWER, PawnColor.BLACK,
                4, 3, 1, false);
        Goods requiredGoods = new Goods(new Resources(5,4,3,2));
        Goods bonusGoods = new Goods();
        Goods occupiedTowerCost = new Goods();

        towerActionRequirements = new TowerActionRequirements(spaceActionRequirements, requiredGoods, bonusGoods,
                occupiedTowerCost, false, false);

        player = new Player(new PlayerDetails(), new PlayerBoard(new BonusTiles(new Goods(), new Goods()),
                new Goods(new Resources(5,5,5,5))));

        List<Pawn> pawns = new ArrayList<>();
        pawns.add(new Pawn(3, PawnColor.BLACK));
        pawns.add(new Pawn(1, PawnColor.NEUTRAL));
        player.getPlayerBoard().setPawns(pawns);
    }

    @Test
    void testHasRequirements() {
        assertTrue(towerActionRequirements.hasRequirements(player));
    }

    @Test
    void testHasRequirementsWhenPlayerCantPay() {
        player.setPlayerGoods(new Goods());
        assertFalse(towerActionRequirements.hasRequirements(player));
    }

    @Test
    void testHasRequirementsWithTowerOccupiedByPlayerColouredPawn() {
        towerActionRequirements.setOccupiedByMyColouredPawn(true);
        assertFalse(towerActionRequirements.hasRequirements(player));
    }

    @Test
    void testHasRequirementsWithTowerOccupiedByPlayerColouredPawnAndPlayerUsingNeutralPawn() {
        towerActionRequirements.getSpaceActionRequirements().setPawnColor(PawnColor.NEUTRAL);
        towerActionRequirements.getSpaceActionRequirements().setActionValue(1);
        towerActionRequirements.getSpaceActionRequirements().setInitialActionValue(1);
        towerActionRequirements.getSpaceActionRequirements().setNumberOfServants(3);
        towerActionRequirements.setOccupiedByMyColouredPawn(true);
        assertTrue(towerActionRequirements.hasRequirements(player));
    }
}