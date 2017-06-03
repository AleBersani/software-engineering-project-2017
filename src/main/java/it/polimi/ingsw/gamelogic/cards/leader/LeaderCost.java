package it.polimi.ingsw.gamelogic.cards.leader;

import it.polimi.ingsw.gamelogic.basics.CardsRequired;
import it.polimi.ingsw.gamelogic.basics.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that describes the cost of a Leader Card
 */
public class LeaderCost {
    private Goods requiredGoods;
    private List<CardsRequired> cardsRequiredList;

    public LeaderCost(Goods requiredGoods) {
        this.requiredGoods = requiredGoods;
        cardsRequiredList = new ArrayList<>();
    }

    public LeaderCost(List<CardsRequired> cardsRequiredList) {
        this.cardsRequiredList = cardsRequiredList;
        requiredGoods = new Goods();
    }

    public LeaderCost(Goods requiredGoods, List<CardsRequired> cardsRequiredList) {
        this.requiredGoods = requiredGoods;
        this.cardsRequiredList = cardsRequiredList;
    }

    public Goods getRequiredGoods() {
        return requiredGoods;
    }

    public void setRequiredGoods(Goods requiredGoods) {
        this.requiredGoods = requiredGoods;
    }

    public List<CardsRequired> getCardsRequiredList() {
        return cardsRequiredList;
    }

    public void setCardsRequiredList(List<CardsRequired> cardsRequiredList) {
        this.cardsRequiredList = cardsRequiredList;
    }
}
