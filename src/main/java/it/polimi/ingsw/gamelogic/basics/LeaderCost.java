package it.polimi.ingsw.gamelogic.basics;

import it.polimi.ingsw.gamelogic.enums.GeneralColor;

import java.util.Map;

/**
 * Extension of the Class CardCost, represents the characteristic cost of some Leader Cards requiring a particular
 * number of Cards of a determined color to activate.
 */
public class LeaderCost extends CardCost {
    private Map<Integer, GeneralColor> requiredCardsNumberAndColor;

    public LeaderCost(Goods requiredGoods, Map<Integer, GeneralColor> requiredCardsNumberAndColor) {
        super(requiredGoods);
        this.requiredCardsNumberAndColor = requiredCardsNumberAndColor;
    }

    public Map<Integer, GeneralColor> getRequiredCardsNumberAndColor() {
        return requiredCardsNumberAndColor;
    }

    public void setRequiredCardsNumberAndColor(Map<Integer, GeneralColor> requiredCardsNumberAndColor) {
        this.requiredCardsNumberAndColor = requiredCardsNumberAndColor;
    }
}
