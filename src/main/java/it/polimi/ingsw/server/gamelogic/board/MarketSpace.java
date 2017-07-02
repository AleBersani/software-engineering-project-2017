package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;

import java.util.Objects;

/**
 * Class that describes the Market area, where if the player puts his/hers pawns, he/she can get Goods
 */
public class MarketSpace {
    private Space space;
    private ExchangingGoods exchangingGoods;
    private int numberOfRequiredPlayers;

    public MarketSpace(Space space, ExchangingGoods exchangingGoods) {
        this.space = space;
        this.exchangingGoods = exchangingGoods;
        numberOfRequiredPlayers = 0;
    }

    public MarketSpace(Space space, ExchangingGoods exchangingGoods, int numberOfRequiredPlayers) {
        this.space = space;
        this.exchangingGoods = exchangingGoods;
        this.numberOfRequiredPlayers = numberOfRequiredPlayers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MarketSpace that = (MarketSpace) o;
        return numberOfRequiredPlayers == that.numberOfRequiredPlayers &&
                Objects.equals(getSpace(), that.getSpace()) &&
                Objects.equals(getExchangingGoods(), that.getExchangingGoods());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSpace(), getExchangingGoods(), numberOfRequiredPlayers);
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public ExchangingGoods getExchangingGoods() {
        return exchangingGoods;
    }

    public void setExchangingGoods(ExchangingGoods exchangingGoods) {
        this.exchangingGoods = exchangingGoods;
    }

    public int getNumberOfRequiredPlayers() {
        return numberOfRequiredPlayers;
    }

    public void setNumberOfRequiredPlayers(int numberOfRequiredPlayers) {
        this.numberOfRequiredPlayers = numberOfRequiredPlayers;
    }
}
