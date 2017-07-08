package it.polimi.ingsw.server.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.server.gamelogic.cards.CardVisitor;

import java.util.Objects;

/**
 * Class that calculate the weight of each player in order to take trace of the players' order
 */
public class PlayerOrderWeight extends AdditionalCardInfo {
    private int weight;

    public PlayerOrderWeight(String name, int weight) {
        super(name);
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        PlayerOrderWeight that = (PlayerOrderWeight) o;
        return weight == that.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), weight);
    }

    @Override
    public void acceptCardVisitor(CardVisitor cardVisitor) {
        cardVisitor.visitAdditionalCardInfo(this);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
