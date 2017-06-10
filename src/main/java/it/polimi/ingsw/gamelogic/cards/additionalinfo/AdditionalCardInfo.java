package it.polimi.ingsw.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.gamelogic.cards.CardVisitor;

import java.util.Objects;

/**
 * Class that is the father of the classes that describe the different permanent "effects" that the cards might have
 */
public abstract class AdditionalCardInfo {
    private String name;

    public AdditionalCardInfo(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AdditionalCardInfo that = (AdditionalCardInfo) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public abstract void acceptCardVisitor(CardVisitor cardVisitor);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
