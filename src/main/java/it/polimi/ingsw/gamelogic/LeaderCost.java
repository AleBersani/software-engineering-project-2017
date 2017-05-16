package it.polimi.ingsw.gamelogic;

import java.util.Map;

/**
 * Extension of the Class CardCost, represents the characteristic cost of some Leader Cards requiring a particular
 * number of Cards ofa determined color to activate.
 */
public class LeaderCost extends CardCost {
    private Map<Integer, String> requiredCardsNumberAndColor;

    public LeaderCost(Goods requiredGoods, Map<Integer, String> requiredCardsNumberAndColor) {
        super(requiredGoods);
        this.requiredCardsNumberAndColor = requiredCardsNumberAndColor;
    }

    public Map<Integer, String> getRequiredCardsNumberAndColor() {
        return requiredCardsNumberAndColor;
    }

    public void setRequiredCardsNumberAndColor(Map<Integer, String> requiredCardsNumberAndColor) {
        this.requiredCardsNumberAndColor = requiredCardsNumberAndColor;
    }
}
