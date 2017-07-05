package it.polimi.ingsw.server.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.cards.CardVisitor;

import java.util.Objects;

public class ChurchSustainBonus extends AdditionalCardInfo {
    private Goods bonus;

    public ChurchSustainBonus(String name, Goods bonus) {
        super(name);
        this.bonus = bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        ChurchSustainBonus that = (ChurchSustainBonus) o;
        return Objects.equals(getBonus(), that.getBonus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBonus());
    }

    @Override
    public void acceptCardVisitor(CardVisitor cardVisitor) {
        cardVisitor.visitAdditionalCardInfo(this);
    }

    public Goods getBonus() {
        return bonus;
    }

    public void setBonus(Goods bonus) {
        this.bonus = bonus;
    }
}
