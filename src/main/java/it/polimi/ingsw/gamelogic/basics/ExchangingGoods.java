package it.polimi.ingsw.gamelogic.basics;

import java.util.List;

/**
 * Composition with Class Goods, it contains the number of Council's privileges.
 */
public class ExchangingGoods {
    private Goods goods;
    private int numberOfCouncilPrivilege;

    public ExchangingGoods() {
        goods = new Goods();
        numberOfCouncilPrivilege = 0;
    }

    /**
     * Constructor useful when we have only Council's Privileges
     * @param numberOfCouncilPrivilege number of Council's Privileges
     */
    public ExchangingGoods(int numberOfCouncilPrivilege) {
        this.numberOfCouncilPrivilege = numberOfCouncilPrivilege;
        goods = new Goods();
    }

    /**
     * Constructor useful when we haven't Council's Privileges
     * @param goods number of Council's Privileges
     */
    public ExchangingGoods(Goods goods) {
        this.goods = goods;
        numberOfCouncilPrivilege = 0;
    }

    /**
     * Constructor that calls the constructor of Goods with only Resources
     * @param resources instance of Resources
     * @param numberOfCouncilPrivilege number of Council's Privileges
     * @see Goods
     */
    public ExchangingGoods(Resources resources, int numberOfCouncilPrivilege) {
        this.goods = new Goods(resources);
        this.numberOfCouncilPrivilege = numberOfCouncilPrivilege;
    }

    /**
     * Constructor that calls the constructor of Goods with only Points
     * @param points instance of Points
     * @param numberOfCouncilPrivilege number of Council's Privileges
     * @see Goods
     */
    public ExchangingGoods(Points points, int numberOfCouncilPrivilege) {
        this.goods = new Goods(points);
        this.numberOfCouncilPrivilege = numberOfCouncilPrivilege;
    }

    public ExchangingGoods(Goods goods, int numberOfCouncilPrivilege) {
        this.goods = goods;
        this.numberOfCouncilPrivilege = numberOfCouncilPrivilege;
    }

    public ExchangingGoods(Resources resources, Points points, int numberOfCouncilPrivilege) {
        this.goods = new Goods(resources, points);
        this.numberOfCouncilPrivilege = numberOfCouncilPrivilege;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ExchangingGoods that = (ExchangingGoods) o;

        if (getNumberOfCouncilPrivilege() != that.getNumberOfCouncilPrivilege())
            return false;
        return getGoods().equals(that.getGoods());
    }

    @Override
    public int hashCode() {
        int result = getGoods().hashCode();
        result = 31 * result + getNumberOfCouncilPrivilege();
        return result;
    }

    /**
     * Convert ExchangingGoods to Goods taking as parameter the list of converted Goods chosen by the player
     * @param chosenGoods list of Goods converted, length must be equals to numberOfCouncilPrivileges
     * @return converted Goods object
     */
    public Goods exchangeCouncilsPrivileges(List<Goods> chosenGoods) throws IllegalArgumentException {
        if (chosenGoods.size() != numberOfCouncilPrivilege)
            throw new IllegalArgumentException("List size different from number of Council's Privileges");
        Goods convertedGoods = new Goods(goods.getResources(), goods.getPoints());
        chosenGoods.forEach(g -> goods.addAll(g));
        return convertedGoods;
    }

    /**
     * Auxiliary method for the composite object
     * @return Resources
     */
    public Resources getResources() {
        return goods.getResources();
    }

    /**
     * Auxiliary method for the composite object
     * @return Points
     */
    public Points getPoints() {
        return goods.getPoints();
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getNumberOfCouncilPrivilege() {
        return numberOfCouncilPrivilege;
    }

    public void setNumberOfCouncilPrivilege(int numberOfCouncilPrivilege) {
        this.numberOfCouncilPrivilege = numberOfCouncilPrivilege;
    }
}