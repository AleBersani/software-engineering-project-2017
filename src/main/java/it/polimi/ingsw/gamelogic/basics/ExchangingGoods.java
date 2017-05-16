package it.polimi.ingsw.gamelogic.basics;

import java.util.Objects;

/**
 * Extension of the Class Goods, it contains the number of Councile's privileges.
 */
public class ExchangingGoods extends Goods {
    private int numberOfCouncilPrivilege;

    /**
     * Constructor useful when we have only Councile's Privileges
     * @param numberOfCouncilPrivilege number of Council's Privileges
     */
    public ExchangingGoods(int numberOfCouncilPrivilege) {
        super();
        this.numberOfCouncilPrivilege = numberOfCouncilPrivilege;
    }

    public ExchangingGoods(Resources resources, int numberOfCouncilPrivilege) {
        super(resources);
        this.numberOfCouncilPrivilege = numberOfCouncilPrivilege;
    }

    public ExchangingGoods(Points points, int numberOfCouncilPrivilege) {
        super(points);
        this.numberOfCouncilPrivilege = numberOfCouncilPrivilege;
    }

    public ExchangingGoods(Resources resources, Points points, int numberOfCouncilPrivilege) {
        super(resources, points);
        this.numberOfCouncilPrivilege = numberOfCouncilPrivilege;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExchangingGoods that = (ExchangingGoods) o;
        return getNumberOfCouncilPrivilege() == that.getNumberOfCouncilPrivilege();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNumberOfCouncilPrivilege());
    }

    public int getNumberOfCouncilPrivilege() {
        return numberOfCouncilPrivilege;
    }

    public void setNumberOfCouncilPrivilege(int numberOfCouncilPrivilege) {
        this.numberOfCouncilPrivilege = numberOfCouncilPrivilege;
    }
}

