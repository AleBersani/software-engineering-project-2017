package it.polimi.ingsw.server.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.cards.CardVisitor;
import it.polimi.ingsw.shared.model.ActionType;

import java.util.Objects;

/**
 * Class that describes the immediate action of a card. The Flash Effect takes place as the player picks up the
 * card from the board, in this case it can be the possibility to perform an action that has some Goods as a result
 */
public class CardFlashAction extends AdditionalCardInfo {
    private ActionType actionType;
    private int actionValue;
    private Goods discount;

    public CardFlashAction(String name, ActionType actionType, int actionValue) {
        super(name);
        this.actionType = actionType;
        this.actionValue = actionValue;
        discount = new Goods();
    }

    public CardFlashAction(String name, ActionType actionType, int actionValue, Goods discount) {
        super(name);
        this.actionType = actionType;
        this.actionValue = actionValue;
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        CardFlashAction that = (CardFlashAction) o;
        return getActionValue() == that.getActionValue() &&
                getActionType() == that.getActionType() &&
                Objects.equals(getDiscount(), that.getDiscount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getActionType(), getActionValue(), getDiscount());
    }

    @Override
    public void acceptCardVisitor(CardVisitor cardVisitor) {
        cardVisitor.visitAdditionalCardInfo(this);
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public int getActionValue() {
        return actionValue;
    }

    public void setActionValue(int actionValue) {
        this.actionValue = actionValue;
    }

    public Goods getDiscount() {
        return discount;
    }

    public void setDiscount(Goods discount) {
        this.discount = discount;
    }
}
