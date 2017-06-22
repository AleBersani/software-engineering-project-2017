package it.polimi.ingsw.server.gamelogic.cards.leader;

import it.polimi.ingsw.server.gamelogic.basics.CardsRequired;
import it.polimi.ingsw.server.gamelogic.basics.Goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LeaderCost that = (LeaderCost) o;
        return Objects.equals(getRequiredGoods(), that.getRequiredGoods()) &&
                Objects.equals(getCardsRequiredList(), that.getCardsRequiredList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRequiredGoods(), getCardsRequiredList());
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
