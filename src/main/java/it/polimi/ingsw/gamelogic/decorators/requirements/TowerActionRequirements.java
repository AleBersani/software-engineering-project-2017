package it.polimi.ingsw.gamelogic.decorators.requirements;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.enums.BoardIdentifier;

public class TowerActionRequirements implements Requirements {
    private SpaceActionRequirements spaceActionRequirements;
    private Goods requiredGoods;
    private Goods bonusGoods;

    public TowerActionRequirements(SpaceActionRequirements spaceActionRequirements, Goods requiredGoods,
                                   Goods bonusGoods) {
        this.spaceActionRequirements = spaceActionRequirements;
        this.requiredGoods = requiredGoods;
        this.bonusGoods = bonusGoods;
    }

    public BoardIdentifier getBoardIdentifier() {
        return spaceActionRequirements.getBoardIdentifier();
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
}
