package it.polimi.ingsw.gamelogic.cards.development;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.basics.Goods;

import java.util.List;

/**
 * This Interface allows to declare all the different kinds of Development Cards as the same type
 * whenever we need to use and represent them as a set of objects with the same behaviour
 * @see it.polimi.ingsw.gamelogic.board.TowerSlot
 */
public interface Collectible {
    CardInformation getCardInformation();
    List<Goods> getCosts();
    ExchangingGoods getInstantEffect();
}
