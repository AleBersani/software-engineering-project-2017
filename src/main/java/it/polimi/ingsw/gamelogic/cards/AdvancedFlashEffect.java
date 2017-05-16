package it.polimi.ingsw.gamelogic.cards;

import it.polimi.ingsw.gamelogic.actions.ActionDescription;
import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;

public class AdvancedFlashEffect extends FlashEffect {
    private ActionDescription action;

    public AdvancedFlashEffect(ExchangingGoods instantGoods, ActionDescription action) {
        super(instantGoods);
        this.action = action;
    }

    public ActionDescription getAction() {
        return action;
    }

    public void setAction(ActionDescription action) {
        this.action = action;
    }
}
