package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.cards.development.DevelopmentCard;

import java.util.Objects;

/**
 * Class that describes a single Tower Slot, it has a space identifier, it can have an instant Good and has a
 * Development card of the same color as the tower
 */
public class TowerSlot {
    private Space space;
    private Goods instantGoods;
    private DevelopmentCard developmentCard;

    public TowerSlot(Space space, Goods instantGoods) {
        this.space = space;
        this.instantGoods = instantGoods;
        developmentCard = null;
    }

    public TowerSlot(Space space, DevelopmentCard developmentCard) {
        this.space = space;
        this.developmentCard = developmentCard;
        instantGoods = new Goods();
    }

    public TowerSlot(Space space, Goods instantGoods, DevelopmentCard developmentCard) {
        this.space = space;
        this.instantGoods = instantGoods;
        this.developmentCard = developmentCard;
    }

    @Override
    public String toString() {
        return "TowerSlot{" +
                "space=" + space.toString() +
                ", instantGoods=" + instantGoods +
                ", developmentCard=" + developmentCard.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TowerSlot towerSlot = (TowerSlot) o;
        return Objects.equals(getSpace(), towerSlot.getSpace()) &&
                Objects.equals(getInstantGoods(), towerSlot.getInstantGoods()) &&
                Objects.equals(getDevelopmentCard(), towerSlot.getDevelopmentCard());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSpace(), getInstantGoods(), getDevelopmentCard());
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

    public DevelopmentCard getDevelopmentCard() {
        return developmentCard;
    }

    public void setDevelopmentCard(DevelopmentCard developmentCard) {
        this.developmentCard = developmentCard;
    }
}
