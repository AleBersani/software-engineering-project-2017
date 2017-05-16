package it.polimi.ingsw.gamelogic.actions;

import it.polimi.ingsw.gamelogic.basics.Goods;

/**
 * This class describes the flash action of some Development Cards requiring a certain number of "things"
 * (e.g.: Military Points or other Development Cards) necessary to obtain a reward.
 * In the current version of the game this reward is always a defined number of Victory Points,
 * it's been decided to use the class Goods to represent it for maintainability and compatibility.
 */
public class CardActionInstantPoints extends ActionDescription {
    /**
     * Always Victory Points in the current version of the game
     */
    private Goods rewardForExchange;
    private String typeOfObjectRequired;
    private int numberOfObjectRequired;

    public CardActionInstantPoints(String actionIdentifier, Goods rewardForExchange,
                                   String typeOfObjectRequired, int numberOfObjectRequired) {
        super(actionIdentifier);
        this.rewardForExchange = rewardForExchange;
        this.typeOfObjectRequired = typeOfObjectRequired;
        this.numberOfObjectRequired = numberOfObjectRequired;
    }

    @Override
    public String getConcatenatedActionIdentifier() {
        return actionIdentifier;
    }

    public Goods getRewardForExchange() {
        return rewardForExchange;
    }

    public void setRewardForExchange(Goods rewardForExchange) {
        this.rewardForExchange = rewardForExchange;
    }

    public String getTypeOfObjectRequired() {
        return typeOfObjectRequired;
    }

    public void setTypeOfObjectRequired(String typeOfObjectRequired) {
        this.typeOfObjectRequired = typeOfObjectRequired;
    }

    public int getNumberOfObjectRequired() {
        return numberOfObjectRequired;
    }

    public void setNumberOfObjectRequired(int numberOfObjectRequired) {
        this.numberOfObjectRequired = numberOfObjectRequired;
    }
}
