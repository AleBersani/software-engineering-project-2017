package it.polimi.ingsw.gamelogic.cards.developmentcards;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.cards.FlashEffect;

import java.util.List;

/**
 * This Interface allows to declare all the different kinds of Development Cards as the same type
 * whenever we need to use and represent them as a set of objects with the same behaviour
 * @see it.polimi.ingsw.gamelogic.board.TowerSlot
 */
public interface Collectible {
    CardInformation getCardInformation();
    List<Goods> getCosts();
    FlashEffect getInstantEffect();
}
