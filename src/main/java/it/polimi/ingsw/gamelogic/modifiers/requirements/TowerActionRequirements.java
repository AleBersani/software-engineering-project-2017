package it.polimi.ingsw.gamelogic.modifiers.requirements;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.enums.ActionType;
import it.polimi.ingsw.gamelogic.enums.PawnColor;
import it.polimi.ingsw.gamelogic.player.Player;

/**
 * Class that describes the requirements of a Tower Action
 */
public class TowerActionRequirements implements Requirements {
    private SpaceActionRequirements spaceActionRequirements;
    private Goods requiredGoods;
    private Goods bonusGoods; // Tower bonus
    private Goods occupiedTowerCost;
    private boolean occupiedTower;
    private boolean occupiedByMyColouredPawn;

    private Goods discount;
    private boolean playerHasEnoughMilitaryPoints;

    public TowerActionRequirements(SpaceActionRequirements spaceActionRequirements, Goods requiredGoods,
                                   Goods bonusGoods, Goods occupiedTowerCost,
                                   boolean occupiedTower, boolean occupiedByMyColouredPawn) {
        this.spaceActionRequirements = spaceActionRequirements;
        this.requiredGoods = requiredGoods;
        this.bonusGoods = bonusGoods;
        this.occupiedTowerCost = occupiedTowerCost;
        this.occupiedTower = occupiedTower;
        this.occupiedByMyColouredPawn = occupiedByMyColouredPawn;
        discount = new Goods();
        playerHasEnoughMilitaryPoints = true;
    }

    public TowerActionRequirements(SpaceActionRequirements spaceActionRequirements, Goods requiredGoods,
                                   Goods bonusGoods, Goods occupiedTowerCost,
                                   boolean occupiedTower, boolean occupiedByMyColouredPawn, Goods discount) {
        this.spaceActionRequirements = spaceActionRequirements;
        this.requiredGoods = requiredGoods;
        this.bonusGoods = bonusGoods;
        this.occupiedTowerCost = occupiedTowerCost;
        this.occupiedTower = occupiedTower;
        this.occupiedByMyColouredPawn = occupiedByMyColouredPawn;
        this.discount = discount;
        playerHasEnoughMilitaryPoints = true;
    }

    public TowerActionRequirements(SpaceActionRequirements spaceActionRequirements, Goods requiredGoods,
                                   Goods bonusGoods, Goods occupiedTowerCost,
                                   boolean occupiedTower, boolean occupiedByMyColouredPawn,
                                   Goods discount, boolean playerHasEnoughMilitaryPoints) {
        this.spaceActionRequirements = spaceActionRequirements;
        this.requiredGoods = requiredGoods;
        this.bonusGoods = bonusGoods;
        this.occupiedTowerCost = occupiedTowerCost;
        this.occupiedTower = occupiedTower;
        this.occupiedByMyColouredPawn = occupiedByMyColouredPawn;
        this.discount = discount;
        this.playerHasEnoughMilitaryPoints = playerHasEnoughMilitaryPoints;
    }

    @Override
    public boolean hasRequirements(Player player) {
        if (!spaceActionRequirements.hasRequirements(player))
            return false;

        if (!playerHasEnoughMilitaryPoints)
            return false;

        Goods actualRequiredGoods = requiredGoods;
        actualRequiredGoods.subtractAll(bonusGoods);
        actualRequiredGoods.subtractAll(discount);

        if (occupiedTower)
            actualRequiredGoods.addAll(occupiedTowerCost);

        if (!actualRequiredGoods.isLessThan(player.getPlayerGoods()))
            return false;

        if (occupiedByMyColouredPawn)
            if (spaceActionRequirements.getPawnColor() != PawnColor.NEUTRAL)
                return false;

        return true;
    }

    /**
     * Gets the Action Type
     * @return Action Type
     */
    public ActionType getActionType() {
        return spaceActionRequirements.getActionType();
    }

    public SpaceActionRequirements getSpaceActionRequirements() {
        return spaceActionRequirements;
    }

    public void setSpaceActionRequirements(SpaceActionRequirements spaceActionRequirements) {
        this.spaceActionRequirements = spaceActionRequirements;
    }

    public Goods getRequiredGoods() {
        return requiredGoods;
    }

    public void setRequiredGoods(Goods requiredGoods) {
        this.requiredGoods = requiredGoods;
    }

    public Goods getBonusGoods() {
        return bonusGoods;
    }

    public void setBonusGoods(Goods bonusGoods) {
        this.bonusGoods = bonusGoods;
    }

    public Goods getOccupiedTowerCost() {
        return occupiedTowerCost;
    }

    public void setOccupiedTowerCost(Goods occupiedTowerCost) {
        this.occupiedTowerCost = occupiedTowerCost;
    }

    public boolean isOccupiedTower() {
        return occupiedTower;
    }

    public void setOccupiedTower(boolean occupiedTower) {
        this.occupiedTower = occupiedTower;
    }

    public boolean isOccupiedByMyColouredPawn() {
        return occupiedByMyColouredPawn;
    }

    public void setOccupiedByMyColouredPawn(boolean occupiedByMyColouredPawn) {
        this.occupiedByMyColouredPawn = occupiedByMyColouredPawn;
    }

    public Goods getDiscount() {
        return discount;
    }

    public void setDiscount(Goods discount) {
        this.discount = discount;
    }

    public boolean isPlayerHasEnoughMilitaryPoints() {
        return playerHasEnoughMilitaryPoints;
    }

    public void setPlayerHasEnoughMilitaryPoints(boolean playerHasEnoughMilitaryPoints) {
        this.playerHasEnoughMilitaryPoints = playerHasEnoughMilitaryPoints;
    }
}
