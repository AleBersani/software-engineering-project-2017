package it.polimi.ingsw.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.cards.CardVisitor;

import java.util.List;
import java.util.Objects;

/**
 * Class that describes the situation where it's possible to have a multiple production
 */
public class MultipleProduction extends AdditionalCardInfo {
    private List<Goods> costs;
    private List<ExchangingGoods> result;

    public MultipleProduction(String name, List<Goods> costs, List<ExchangingGoods> result) {
        super(name);
        this.costs = costs;
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        MultipleProduction that = (MultipleProduction) o;
        return Objects.equals(getCosts(), that.getCosts()) &&
                Objects.equals(getResult(), that.getResult());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCosts(), getResult());
    }

    @Override
    public void acceptCardVisitor(CardVisitor cardVisitor) {
        cardVisitor.visitAdditionalCardInfo(this);
    }

    public List<Goods> getCosts() {
        return costs;
    }

    public void setCosts(List<Goods> costs) {
        this.costs = costs;
    }

    public List<ExchangingGoods> getResult() {
        return result;
    }

    public void setResult(List<ExchangingGoods> result) {
        this.result = result;
    }
}
