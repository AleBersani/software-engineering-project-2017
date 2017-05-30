package it.polimi.ingsw.gamelogic.board;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.cards.development.Collectible;

/**
 * TODO: JavaDoc
 */
public class TowerSlot {
    private Space space;
    private Goods instantGoods;
    private Collectible developmentCard;

    public TowerSlot(Space space, Collectible developmentCard) {
        this.space = space;
        this.developmentCard = developmentCard;
        instantGoods = new Goods();
    }

    public TowerSlot(Space space, Goods instantGoods, Collectible developmentCard) {
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

    public Collectible getDevelopmentCard() {
        return developmentCard;
    }

    public void setDevelopmentCard(Collectible developmentCard) {
        this.developmentCard = developmentCard;
    }
}
