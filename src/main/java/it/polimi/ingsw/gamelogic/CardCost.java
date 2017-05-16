package it.polimi.ingsw.gamelogic;

/**
 * Class that represents the cost of a Card.
 * Wrapper of Goods, it's necessary because we can extend this to represent some peculiar costs of the Leader Cards.
 */
public class CardCost {
    private Goods requiredGoods;

    /**
     * Constructor in case that Card doesn't have any cost
     */
    public CardCost() {
        requiredGoods = new Goods();
    }

    public CardCost(Goods requiredGoods) {
        this.requiredGoods = requiredGoods;
    }

    public Goods getRequiredGoods() {
        return requiredGoods;
    }

    public void setRequiredGoods(Goods requiredGoods) {
        this.requiredGoods = requiredGoods;
    }
}
