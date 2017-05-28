package it.polimi.ingsw.gamelogic.decorators.requirements;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.enums.BoardIdentifier;

public class CardActionRequirements implements Requirements {
    private SpaceActionRequirements spaceActionRequirements;
    private Goods requiredGoods;
    private Goods discount;

    public CardActionRequirements(SpaceActionRequirements spaceActionRequirements, Goods requiredGoods, Goods discount) {
        this.spaceActionRequirements = spaceActionRequirements;
        this.requiredGoods = requiredGoods;
        this.discount = discount;
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

    public Goods getDiscount() {
        return discount;
    }

    public void setDiscount(Goods discount) {
        this.discount = discount;
    }
}
