package it.polimi.ingsw.gamelogic.cards;

import it.polimi.ingsw.gamelogic.actions.ActionDescription;
import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;

public class FlashEffect {
    private ExchangingGoods instantGoods;
    private ActionDescription action;

    public FlashEffect(ExchangingGoods instantGoods) {
        this.instantGoods = instantGoods;
        /*
            TODO: action = new ActionDescription();
         */
    }

    public FlashEffect(ActionDescription action) {
        this.action = action;
        instantGoods = new ExchangingGoods();
    }

    public FlashEffect(ExchangingGoods instantGoods, ActionDescription action) {
        this.instantGoods = instantGoods;
        this.action = action;
    }

    /*
        TODO: auxiliaries methods to check if attributes are empty or not?
     */

    public ExchangingGoods getInstantGoods() {
        return instantGoods;
    }

    public void setInstantGoods(ExchangingGoods instantGoods) {
        this.instantGoods = instantGoods;
    }

    public ActionDescription getAction() {
        return action;
    }

    public void setAction(ActionDescription action) {
        this.action = action;
    }
}
