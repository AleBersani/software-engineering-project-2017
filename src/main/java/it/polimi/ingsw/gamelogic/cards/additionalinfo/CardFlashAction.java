package it.polimi.ingsw.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.gamelogic.actions.description.BasicAction;
import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.cards.CardVisitor;

/**
 * TODO: JavaDoc
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
        /*
        TODO
         */
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
