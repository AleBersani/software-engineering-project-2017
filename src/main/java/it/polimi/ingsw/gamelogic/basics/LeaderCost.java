package it.polimi.ingsw.gamelogic.basics;

import it.polimi.ingsw.gamelogic.enums.GeneralColor;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents the cost of a LeaderCard
 */
public class LeaderCost {
    private Goods requiredGoods;
    private Map<Integer, GeneralColor> requiredCardsNumberAndColor;

    /**
     * Constructor in case that Card doesn't have any cost
     */
    public LeaderCost() {
        requiredGoods = new Goods();
        requiredCardsNumberAndColor = new HashMap<>();
    }

    public LeaderCost(Goods requiredGoods) {
        this.requiredGoods = requiredGoods;
        requiredCardsNumberAndColor = new HashMap<>();
    }

    public LeaderCost(Map<Integer, GeneralColor> requiredCardsNumberAndColor) {
        this.requiredCardsNumberAndColor = requiredCardsNumberAndColor;
        requiredGoods = new Goods();
    }

    public LeaderCost(Goods requiredGoods, Map<Integer, GeneralColor> requiredCardsNumberAndColor) {
        this.requiredGoods = requiredGoods;
        this.requiredCardsNumberAndColor = requiredCardsNumberAndColor;
    }

    public Goods getRequiredGoods() {
        return requiredGoods;
    }

    public void setRequiredGoods(Goods requiredGoods) {
        this.requiredGoods = requiredGoods;
    }

    public Map<Integer, GeneralColor> getRequiredCardsNumberAndColor() {
        return requiredCardsNumberAndColor;
    }

    public void setRequiredCardsNumberAndColor(Map<Integer, GeneralColor> requiredCardsNumberAndColor) {
        this.requiredCardsNumberAndColor = requiredCardsNumberAndColor;
    }
}
