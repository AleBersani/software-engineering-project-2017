package it.polimi.ingsw.gamelogic.modifiers.requirements;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.enums.ActionType;
import it.polimi.ingsw.gamelogic.player.Player;

/**
 * TODO: JavaDoc
 */
public class TowerActionRequirements implements Requirements {
    private SpaceActionRequirements spaceActionRequirements;
    private Goods requiredGoods;
    private Goods bonusGoods;
    private Goods occupiedTowerCost;
    private boolean occupiedTower;
    private boolean occupiedByMyColouredPawn;

    private Goods cardCost;
    private Goods discount;

    public TowerActionRequirements(SpaceActionRequirements spaceActionRequirements, Goods requiredGoods,
                                   Goods bonusGoods, Goods occupiedTowerCost,
                                   boolean occupiedTower, boolean occupiedByMyColouredPawn) {
        this.spaceActionRequirements = spaceActionRequirements;
        this.requiredGoods = requiredGoods;
        this.bonusGoods = bonusGoods;
        this.occupiedTowerCost = occupiedTowerCost;
        this.occupiedTower = occupiedTower;
        this.occupiedByMyColouredPawn = occupiedByMyColouredPawn;
        cardCost = requiredGoods;
        discount = new Goods();
    }

    public TowerActionRequirements(SpaceActionRequirements spaceActionRequirements, Goods requiredGoods,
                                   Goods bonusGoods, Goods occupiedTowerCost,
                                   boolean occupiedTower, boolean occupiedByMyColouredPawn,
                                   Goods cardCost, Goods discount) {
        this.spaceActionRequirements = spaceActionRequirements;
        this.requiredGoods = requiredGoods;
        this.bonusGoods = bonusGoods;
        this.occupiedTowerCost = occupiedTowerCost;
        this.occupiedTower = occupiedTower;
        this.occupiedByMyColouredPawn = occupiedByMyColouredPawn;
        this.cardCost = cardCost;
        this.discount = discount;
    }

    @Override
    public boolean hasRequirements(Player player) {
        /*
        TODO
         */
        return false;
    }

    /**
     * TODO: JavaDOc
     * @return
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

    public Goods getCardCost() {
        return cardCost;
    }

    public void setCardCost(Goods cardCost) {
        this.cardCost = cardCost;
    }

    public Goods getDiscount() {
        return discount;
    }

    public void setDiscount(Goods discount) {
        this.discount = discount;
    }
}
