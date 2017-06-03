package it.polimi.ingsw.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.cards.CardVisitor;

/**
 * Class that describes the situation where the card requires that the player has a minimum number of a certain
 * Good to be picked up
 */
public class MinRequiredOnCost extends AdditionalCardInfo {
    private Goods min;

    public MinRequiredOnCost(String name, Goods min) {
        super(name);
        this.min = min;
    }

    @Override
    public void acceptCardVisitor(CardVisitor cardVisitor) {
        cardVisitor.visitAdditionalCardInfo(this);
    }

    public Goods getMin() {
        return min;
    }

    public void setMin(Goods min) {
        this.min = min;
    }
}
