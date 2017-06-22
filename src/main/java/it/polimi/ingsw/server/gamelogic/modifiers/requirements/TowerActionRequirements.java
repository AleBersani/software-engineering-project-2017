package it.polimi.ingsw.server.gamelogic.modifiers.requirements;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.PawnColor;

import java.util.Objects;

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

    /**
     * Constructor with all the attributes except discount and if the player has enough military points that
     * are set as default
     * @param spaceActionRequirements requirements of the Action Space
     * @param requiredGoods Goods required to perform the Action
     * @param bonusGoods Goods as bonus from the Tower Slot
     * @param occupiedTowerCost Added cost if the tower is occupied
     * @param occupiedTower if the Tower is occupied
     * @param occupiedByMyColouredPawn if the Player has one of his coloured Pawns in this Tower
     */
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

    /**
     * Constructor with all the attributes except if the player has enough military points that is set as default
     * @param spaceActionRequirements requirements of the Action Space
     * @param requiredGoods Goods required to perform the Action
     * @param bonusGoods Goods as bonus from the Tower Slot
     * @param occupiedTowerCost Added cost if the tower is occupied
     * @param occupiedTower if the Tower is occupied
     * @param occupiedByMyColouredPawn if the Player has one of his coloured Pawns in this Tower
     * @param discount discount as the effect of some card
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TowerActionRequirements that = (TowerActionRequirements) o;
        return isOccupiedTower() == that.isOccupiedTower() &&
                isOccupiedByMyColouredPawn() == that.isOccupiedByMyColouredPawn() &&
                isPlayerHasEnoughMilitaryPoints() == that.isPlayerHasEnoughMilitaryPoints() &&
                Objects.equals(getSpaceActionRequirements(), that.getSpaceActionRequirements()) &&
                Objects.equals(getRequiredGoods(), that.getRequiredGoods()) &&
                Objects.equals(getBonusGoods(), that.getBonusGoods()) &&
                Objects.equals(getOccupiedTowerCost(), that.getOccupiedTowerCost()) &&
                Objects.equals(getDiscount(), that.getDiscount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSpaceActionRequirements(), getRequiredGoods(), getBonusGoods(), getOccupiedTowerCost(),
                isOccupiedTower(), isOccupiedByMyColouredPawn(), getDiscount(), isPlayerHasEnoughMilitaryPoints());
    }

    /**
     * Checks if the player has the requirements to place a pawn on a tower slot
     * @param player player whose turn it is
     * @return true if all the checks are passed
     */
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

        if (occupiedByMyColouredPawn && spaceActionRequirements.getPawnColor() != PawnColor.NEUTRAL)
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
