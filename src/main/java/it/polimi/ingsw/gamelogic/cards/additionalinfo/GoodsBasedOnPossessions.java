package it.polimi.ingsw.gamelogic.cards.additionalinfo;


import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.cards.CardVisitor;

import java.util.Objects;

/**
 * Class that represents when the player can get some Goods based on the number of cards of a certain color
 */
public class GoodsBasedOnPossessions extends AdditionalCardInfo {
    private Goods rewardForPossession;
    private String typeOfObjectRequired;
    private int numberOfObjectRequired;

    public GoodsBasedOnPossessions(String name, Goods rewardForPossession,
                                   String typeOfObjectRequired, int numberOfObjectRequired) {
        super(name);
        this.rewardForPossession = rewardForPossession;
        this.typeOfObjectRequired = typeOfObjectRequired;
        this.numberOfObjectRequired = numberOfObjectRequired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        GoodsBasedOnPossessions that = (GoodsBasedOnPossessions) o;
        return getNumberOfObjectRequired() == that.getNumberOfObjectRequired() &&
                Objects.equals(getRewardForPossession(), that.getRewardForPossession()) &&
                Objects.equals(getTypeOfObjectRequired(), that.getTypeOfObjectRequired());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRewardForPossession(), getTypeOfObjectRequired(), getNumberOfObjectRequired());
    }

    @Override
    public void acceptCardVisitor(CardVisitor cardVisitor) {
        cardVisitor.visitAdditionalCardInfo(this);
    }

    public Goods getRewardForPossession() {
        return rewardForPossession;
    }

    public void setRewardForPossession(Goods rewardForPossession) {
        this.rewardForPossession = rewardForPossession;
    }

    public String getTypeOfObjectRequired() {
        return typeOfObjectRequired;
    }

    public void setTypeOfObjectRequired(String typeOfObjectRequired) {
        this.typeOfObjectRequired = typeOfObjectRequired;
    }

    public int getNumberOfObjectRequired() {
        return numberOfObjectRequired;
    }

    public void setNumberOfObjectRequired(int numberOfObjectRequired) {
        this.numberOfObjectRequired = numberOfObjectRequired;
    }
}
