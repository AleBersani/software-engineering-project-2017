package it.polimi.ingsw.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.gamelogic.actions.description.BasicAction;
import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.cards.CardVisitor;

/**
 * Class that describes the immediate action of a card. The Flash Effect takes place as the player picks up the
 * card from the board, in this case it can be the possibility to perform an action that has some Goods as a resul
 */
public class CardFlashAction extends AdditionalCardInfo {
    private BasicAction basicAction;
    private Goods discount;

    public CardFlashAction(String name, BasicAction basicAction, Goods discount) {
        super(name);
        this.basicAction = basicAction;
        this.discount = discount;
    }

    @Override
    public void acceptCardVisitor(CardVisitor cardVisitor) {
        cardVisitor.visitAdditionalCardInfo(this);
    }

    public BasicAction getBasicAction() {
        return basicAction;
    }

    public void setBasicAction(BasicAction basicAction) {
        this.basicAction = basicAction;
    }

    public Goods getDiscount() {
        return discount;
    }

    public void setDiscount(Goods discount) {
        this.discount = discount;
    }
}
