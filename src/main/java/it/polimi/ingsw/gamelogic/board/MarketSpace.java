package it.polimi.ingsw.gamelogic.board;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;

/**
 * Class that describes the Market area, where if the player puts his/hers pawns, he/she can get Goods
 */
public class MarketSpace {
    private Space space;
    private ExchangingGoods exchangingGoods;

    public MarketSpace(Space space, ExchangingGoods exchangingGoods) {
        this.space = space;
        this.exchangingGoods = exchangingGoods;
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
}
