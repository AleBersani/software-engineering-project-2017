package it.polimi.ingsw.gamelogic.board;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.cards.development.Collectible;

/**
 * Class that describes a single Tower Slot, it has a space identifier, it can have an instant Good and has a
 * Development card of the same color as the tower
 */
public class TowerSlot<T extends Collectible> {
    private Space space;
    private Goods instantGoods;
    private T developmentCard;

    public TowerSlot(Space space, T developmentCard) {
        this.space = space;
        this.developmentCard = developmentCard;
        instantGoods = new Goods();
    }

    public TowerSlot(Space space, Goods instantGoods, T developmentCard) {
        this.space = space;
        this.instantGoods = instantGoods;
        this.developmentCard = developmentCard;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public Goods getInstantGoods() {
        return instantGoods;
    }

    public void setInstantGoods(Goods instantGoods) {
        this.instantGoods = instantGoods;
    }

    public T getDevelopmentCard() {
        return developmentCard;
    }

    public void setDevelopmentCard(T developmentCard) {
        this.developmentCard = developmentCard;
    }
}
