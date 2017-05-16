package it.polimi.ingsw.gamelogic.basics;

import it.polimi.ingsw.gamelogic.enums.GlobalColor;

import java.util.Map;

/**
 * Extension of the Class CardCost, represents the characteristic cost of some Leader Cards requiring a particular
 * number of Cards of a determined color to activate.
 */
public class LeaderCost extends CardCost {
    private Map<Integer, GlobalColor> requiredCardsNumberAndColor;

    public LeaderCost(Goods requiredGoods, Map<Integer, GlobalColor> requiredCardsNumberAndColor) {
        super(requiredGoods);
        this.requiredCardsNumberAndColor = requiredCardsNumberAndColor;
    }

    public Map<Integer, GlobalColor> getRequiredCardsNumberAndColor() {
        return requiredCardsNumberAndColor;
    }

    public void setRequiredCardsNumberAndColor(Map<Integer, GlobalColor> requiredCardsNumberAndColor) {
        this.requiredCardsNumberAndColor = requiredCardsNumberAndColor;
    }
}
