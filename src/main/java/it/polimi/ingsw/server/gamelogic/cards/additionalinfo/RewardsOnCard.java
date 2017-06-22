package it.polimi.ingsw.server.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.server.gamelogic.cards.CardVisitor;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.modifiers.RewardsModifier;

import java.util.Objects;

/**
 * Class that describes the rewards modifiers of a card
 */
public class RewardsOnCard extends AdditionalCardInfo {
    private RewardsModifier rewardsModifier;

    public RewardsOnCard(String name, RewardsModifier rewardsModifier) {
        super(name);
        this.rewardsModifier = rewardsModifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        RewardsOnCard that = (RewardsOnCard) o;
        return Objects.equals(getRewardsModifier(), that.getRewardsModifier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRewardsModifier());
    }

    @Override
    public void acceptCardVisitor(CardVisitor cardVisitor) {
        cardVisitor.visitAdditionalCardInfo(this);
    }

    public RewardsModifier getRewardsModifier() {
        return rewardsModifier;
    }

    public void setRewardsModifier(RewardsModifier rewardsModifier) {
        this.rewardsModifier = rewardsModifier;
    }
}
