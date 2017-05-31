package it.polimi.ingsw.gamelogic.actions.description;

import it.polimi.ingsw.gamelogic.actions.ActionVisitor;
import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.enums.ActionType;

/**
 * This class describes the flash action of some Development Cards requiring a certain number of "things"
 * (e.g.: Military Points or other Development Cards) necessary to obtain a reward.
 * In the current version of the game this reward is always a defined number of Victory Points,
 * it's been decided to use the class Goods to represent it for maintainability and compatibility.
 */
public class CardActionInstantPoints implements ActionDescription {
    private ActionType actionType;
    /**
     * Always Victory Points in the current version of the game
     */
    private Goods rewardForExchange;
    private String typeOfObjectRequired;
    private int numberOfObjectRequired;

    /*
        TODO
     */

    @Override
    public void acceptActionVisitor(ActionVisitor actionVisitor) {
        actionVisitor.visitActionDescription(this);
    }
}
