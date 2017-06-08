package it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.basics.Resources;
import it.polimi.ingsw.gamelogic.enums.ActionType;
import it.polimi.ingsw.gamelogic.enums.PawnColor;
import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.SpaceActionRequirements;
import it.polimi.ingsw.gamelogic.modifiers.requirements.TowerActionRequirements;
import it.polimi.ingsw.gamelogic.player.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NoOccupiedTowerCostTest {
    private TowerActionRequirements towerActionRequirements;
    private Player player;

    private NoOccupiedTowerCost noOccupiedTowerCost;

    @BeforeEach
    void setUp() {
        SpaceActionRequirements spaceActionRequirements = new SpaceActionRequirements(
                ActionType.BLUE_TOWER, PawnColor.BLACK,
                3, 3, 1, false);
        Goods requiredGoods = new Goods(new Resources(5,5,4,3));

        towerActionRequirements = new TowerActionRequirements(spaceActionRequirements, requiredGoods,
                new Goods(),
                new Goods(new Resources(1,1,1,1)),
                true, false);

        player = new Player(new PlayerDetails(), new PlayerBoard(new BonusTiles(new Goods(), new Goods()),
                new Goods(new Resources(5,5,5,5))));

        List<Pawn> pawns = new ArrayList<>();
        pawns.add(new Pawn(3, PawnColor.BLACK));
        player.getPlayerBoard().setPawns(pawns);
    }

    @Test
    void testModifyRequirements() {
        noOccupiedTowerCost = new NoOccupiedTowerCost(new AvailableActions(ActionType.BLUE_TOWER));
        towerActionRequirements = noOccupiedTowerCost.modifyRequirements(towerActionRequirements);

        assertTrue(towerActionRequirements.hasRequirements(player));
    }

    @Test
    void testModifyRequirementsWithoutModifierActivation() {
        noOccupiedTowerCost = new NoOccupiedTowerCost(new AvailableActions(ActionType.YELLOW_TOWER));
        towerActionRequirements = noOccupiedTowerCost.modifyRequirements(towerActionRequirements);

        assertFalse(towerActionRequirements.hasRequirements(player));
    }
}