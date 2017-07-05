package it.polimi.ingsw.server.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.cards.CardVisitor;

public class ChurchSustainBonus extends AdditionalCardInfo {
    private Goods bonus;

    public ChurchSustainBonus(String name, Goods bonus) {
        super(name);
        this.bonus = bonus;
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
